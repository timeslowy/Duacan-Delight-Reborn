package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.NotNull;

/**
 * 可饮用食物物品。
 * <p>
 * 在 {@link TooltipFoodItem} 基础上把使用动画固定为 {@link UseAnim#DRINK}，
 * 用于汤、茶、酒、饮料这类物品。确有独特 {@code finishUsingItem} 或交互行为的类
 * （如 {@code RiceWineItem}）应继续独立实现，不要强行套用。
 */
public class DrinkableFoodItem extends TooltipFoodItem {

    public DrinkableFoodItem(Properties properties, int useDuration, String... descriptionKeys) {
        super(properties, useDuration, descriptionKeys);
    }

    public DrinkableFoodItem(Properties properties, String... descriptionKeys) {
        super(properties, descriptionKeys);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.DRINK;
    }
}
