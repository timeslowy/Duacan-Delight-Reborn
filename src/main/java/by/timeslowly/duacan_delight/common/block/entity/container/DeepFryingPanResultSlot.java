package by.timeslowly.duacan_delight.common.block.entity.container;

import by.timeslowly.duacan_delight.common.block.entity.DeepFryingPanBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

/**
 * 炸锅的成品输出槽：只能取出，不能放入，取出时结算经验与配方统计。
 * <p>
 * 结构参照农夫乐事厨锅的 {@code CookingPotResultSlot}。
 */
public class DeepFryingPanResultSlot extends SlotItemHandler {
    public final DeepFryingPanBlockEntity deepFryingPan;
    private final Player player;
    private int removeCount;

    public DeepFryingPanResultSlot(Player player, DeepFryingPanBlockEntity blockEntity, IItemHandler inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.deepFryingPan = blockEntity;
        this.player = player;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public @NotNull ItemStack remove(int amount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(amount, this.getItem().getCount());
        }
        return super.remove(amount);
    }

    @Override
    public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
        this.checkTakeAchievements(stack);
        super.onTake(player, stack);
    }

    @Override
    protected void onQuickCraft(@NotNull ItemStack stack, int amount) {
        this.removeCount += amount;
        this.checkTakeAchievements(stack);
    }

    @Override
    protected void checkTakeAchievements(@NotNull ItemStack stack) {
        stack.onCraftedBy(this.player.level(), this.player, this.removeCount);
        if (!this.player.level().isClientSide) {
            this.deepFryingPan.awardUsedRecipes(this.player);
        }
        this.removeCount = 0;
    }
}
