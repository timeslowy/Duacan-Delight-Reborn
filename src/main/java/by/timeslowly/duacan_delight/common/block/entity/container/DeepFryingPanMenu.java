package by.timeslowly.duacan_delight.common.block.entity.container;

import by.timeslowly.duacan_delight.common.block.entity.DeepFryingPanBlockEntity;
import by.timeslowly.duacan_delight.registry.DDBlocks;
import by.timeslowly.duacan_delight.registry.DDMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 炸锅的容器菜单。
 * <p>
 * 布局参照农夫乐事厨锅（{@code CookingPotMenu}），按炸锅的实际需要精简为：
 * 原料槽 1 个（厨锅是 6 个）、容具槽 1 个、成品输出槽 1 个，加玩家背包 27 + 快捷栏 9。
 * <p>
 * 槽位索引约定为「原料(0) → 容具(1) → 输出(2) → 玩家背包(3..38)」，
 * 与方块实体库存索引、数据包 {@code Slot} 索引保持一致。
 * <p>
 * 与厨锅的差异：厨锅有 6 格原料和一个可视的「食物展示槽」；
 * 炸锅只有单原料，成品直接进输出槽，因此不需要展示槽。
 */
public class DeepFryingPanMenu extends AbstractContainerMenu {
    /** 库存索引：原料输入。 */
    public static final int SLOT_INPUT = DeepFryingPanBlockEntity.SLOT_INPUT;
    /** 库存索引：容具输入。 */
    public static final int SLOT_CONTAINER = DeepFryingPanBlockEntity.SLOT_CONTAINER;
    /** 库存索引：成品输出。 */
    public static final int SLOT_OUTPUT = DeepFryingPanBlockEntity.SLOT_OUTPUT;
    /** 库存总槽位数。 */
    public static final int INVENTORY_SIZE = DeepFryingPanBlockEntity.INVENTORY_SIZE;

    private static final int PLAYER_INV_START = INVENTORY_SIZE;
    private static final int PLAYER_INV_END = PLAYER_INV_START + 36;

    public final DeepFryingPanBlockEntity blockEntity;
    public final ItemStackHandler inventory;
    private final ContainerData data;

    public DeepFryingPanMenu(int windowId, Inventory playerInventory, FriendlyByteBuf data) {
        this(windowId, playerInventory, getBlockEntity(playerInventory, data), new SimpleContainerData(2));
    }

    public DeepFryingPanMenu(int windowId, Inventory playerInventory, DeepFryingPanBlockEntity blockEntity, ContainerData data) {
        super(DDMenuTypes.DEEP_FRYING_PAN.get(), windowId);
        this.blockEntity = blockEntity;
        this.inventory = blockEntity.getInventory();
        this.data = data;

        // 原料槽：只接受能匹配油炸配方的物品，一次 1 个
        this.addSlot(new DeepFryingPanInputSlot(blockEntity, this.inventory, SLOT_INPUT, 54, 24));
        // 容具槽：配方声明 container 时，成品会自动消耗这里的一个容器
        // 贴图里该槽的内容区在 (140,48)，Slot 的 x/y 指的就是 16×16 内容区左上角
        this.addSlot(new SlotItemHandler(this.inventory, SLOT_CONTAINER, 140, 48) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return blockEntity.isValidContainer(stack);
            }
        });
        // 成品输出槽：只能取出；贴图内容区在 (140,24)
        this.addSlot(new DeepFryingPanResultSlot(playerInventory.player, blockEntity, this.inventory, SLOT_OUTPUT, 140, 24));

        // 玩家背包
        int invStartY = 84;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, 9 + row * 9 + column, 8 + column * 18, invStartY + row * 18));
            }
        }
        // 快捷栏
        for (int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, 8 + column * 18, 142));
        }

        this.addDataSlots(data);
    }

    private static DeepFryingPanBlockEntity getBlockEntity(Inventory playerInventory, FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        BlockEntity blockEntity = playerInventory.player.level().getBlockEntity(data.readBlockPos());
        if (blockEntity instanceof DeepFryingPanBlockEntity deepFryingPan) {
            return deepFryingPan;
        }
        throw new IllegalStateException("Block entity is not correct! " + blockEntity);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()),
                player, DDBlocks.DEEP_FRYING_PAN.get());
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack slotStackCopy = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack slotStack = slot.getItem();
        slotStackCopy = slotStack.copy();

        if (index == SLOT_OUTPUT) {
            // 成品 → 玩家背包
            if (!this.moveItemStackTo(slotStack, PLAYER_INV_START, PLAYER_INV_END, true)) {
                return ItemStack.EMPTY;
            }
            slot.onQuickCraft(slotStack, slotStackCopy);
        } else if (index == SLOT_INPUT || index == SLOT_CONTAINER) {
            // 锅内槽位 → 玩家背包
            if (!this.moveItemStackTo(slotStack, PLAYER_INV_START, PLAYER_INV_END, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            // 玩家背包 → 优先容具槽，其次原料槽，最后在背包内互转
            if (this.slots.get(SLOT_CONTAINER).mayPlace(slotStack)) {
                if (!this.moveItemStackTo(slotStack, SLOT_CONTAINER, SLOT_CONTAINER + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.slots.get(SLOT_INPUT).mayPlace(slotStack)) {
                if (!this.moveItemStackTo(slotStack, SLOT_INPUT, SLOT_INPUT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < PLAYER_INV_START + 27) {
                if (!this.moveItemStackTo(slotStack, PLAYER_INV_START + 27, PLAYER_INV_END, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(slotStack, PLAYER_INV_START, PLAYER_INV_START + 27, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (slotStack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        if (slotStack.getCount() == slotStackCopy.getCount()) {
            return ItemStack.EMPTY;
        }

        slot.onTake(player, slotStack);
        return slotStackCopy;
    }

    /** 进度条宽度：0..24，与界面箭头长度一致。 */
    public int getCookProgressionScaled() {
        int current = this.data.get(0);
        int total = this.data.get(1);
        return total != 0 && current != 0 ? current * 24 / total : 0;
    }

    public boolean isHeated() {
        return this.blockEntity.isHeated();
    }

    public boolean isCooking() {
        return this.blockEntity.isCooking();
    }

    public DeepFryingPanBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}
