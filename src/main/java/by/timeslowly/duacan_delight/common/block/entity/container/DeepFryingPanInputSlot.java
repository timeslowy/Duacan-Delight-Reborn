package by.timeslowly.duacan_delight.common.block.entity.container;

import by.timeslowly.duacan_delight.common.block.entity.DeepFryingPanBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

/**
 * 炸锅的原料输入槽：只接受当前能匹配到油炸配方的物品。
 * <p>
 * 这是相对厨锅的收紧：厨锅允许随便放 6 种原料，炸锅只有一个槽位，
 * 提前按配方过滤能让玩家立刻知道“这东西炸不了”。
 */
public class DeepFryingPanInputSlot extends SlotItemHandler {
    private final DeepFryingPanBlockEntity deepFryingPan;

    public DeepFryingPanInputSlot(DeepFryingPanBlockEntity blockEntity, IItemHandler inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.deepFryingPan = blockEntity;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return this.deepFryingPan.isValidIngredient(stack);
    }

    @Override
    public int getMaxStackSize() {
        // 一次只炸一个，避免玩家误以为可以整组丢进去。
        return 1;
    }

    @Override
    public int getMaxStackSize(@NotNull ItemStack stack) {
        return 1;
    }
}
