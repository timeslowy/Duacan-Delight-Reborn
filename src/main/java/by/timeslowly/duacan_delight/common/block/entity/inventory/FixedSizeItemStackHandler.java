package by.timeslowly.duacan_delight.common.block.entity.inventory;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

/**
 * 槽位数固定的物品栏。
 * <p>
 * 存在的理由是一个真实崩溃：NeoForge 的 {@link ItemStackHandler#deserializeNBT} 会按 NBT 里的
 * {@code Size} 调用 {@code setSize}。炸锅最初版本只有 1 个槽位（直接右键放料），
 * 存档里写下的就是 {@code Inventory:{Size:1,...}}。改成三槽位后，旧存档载入时库存被缩回 1 格，
 * 而界面要访问 0/1/2 号槽位，于是抛出
 * <code>Slot 1 not in valid range - [0,1)</code>，一开界面就崩。
 * <p>
 * 这里把槽位数钉死为构造时给定的值：无论 NBT 里写的是什么 {@code Size}，
 * 都按固定大小重建数组，并按「旧索引 → 新索引」安全映射恢复物品。
 */
public class FixedSizeItemStackHandler extends ItemStackHandler {
    private final int fixedSize;

    public FixedSizeItemStackHandler(int size) {
        super(size);
        this.fixedSize = size;
    }

    @Override
    public int getSlots() {
        return this.fixedSize;
    }

    /** 固定大小，忽略外部传入的尺寸。 */
    @Override
    public void setSize(int size) {
        // 只有构造阶段（fixedSize 尚未赋值时为 0）才允许建立数组
        super.setSize(this.fixedSize > 0 ? this.fixedSize : size);
    }

    /**
     * 反序列化：始终恢复成固定槽位数。
     * <p>
     * 旧版本的 {@code Size:1} 数据会被映射到 {@code slot 0}（原料槽），
     * 也就是原来「正在炸的那个物品」，不会丢失。
     */
    @Override
    public void deserializeNBT(@NotNull HolderLookup.Provider provider, @NotNull CompoundTag nbt) {
        // 先恢复到固定尺寸，绝不采用 NBT 里的 Size
        super.setSize(this.fixedSize);

        ListTag tagList = nbt.getList("Items", Tag.TAG_COMPOUND);
        NonNullList<ItemStack> restored = NonNullList.withSize(this.fixedSize, ItemStack.EMPTY);
        for (int i = 0; i < tagList.size(); i++) {
            CompoundTag itemTags = tagList.getCompound(i);
            int slot = itemTags.getInt("Slot");
            if (slot >= 0 && slot < this.fixedSize) {
                int target = slot;
                ItemStack.parse(provider, itemTags).ifPresent(stack -> restored.set(target, stack));
            }
        }
        for (int i = 0; i < this.fixedSize; i++) {
            this.stacks.set(i, restored.get(i));
        }
        this.onLoad();
    }

    /** 保存时永远写入真实槽位数，避免再次写出会缩短数组的 Size。 */
    @Override
    public @NotNull CompoundTag serializeNBT(@NotNull HolderLookup.Provider provider) {
        ListTag nbtTagList = new ListTag();
        for (int i = 0; i < this.fixedSize; i++) {
            ItemStack stack = this.stacks.get(i);
            if (!stack.isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("Slot", i);
                nbtTagList.add(stack.save(provider, itemTag));
            }
        }
        CompoundTag nbt = new CompoundTag();
        nbt.put("Items", nbtTagList);
        nbt.putInt("Size", this.fixedSize);
        return nbt;
    }
}
