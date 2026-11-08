package by.timeslowly.duacan_delight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 带 tooltip 的食物物品。
 * <p>
 * 把「附加翻译键描述」和「自定义使用时长」合并进一个可参数化基类，
 * 消除每种食物各建一个 Item 子类造成的复制粘贴。
 * <p>
 * 使用 {@link #useDuration} 为 0 表示沿用原版默认时长（32 tick）。
 */
public class TooltipFoodItem extends Item {
    private final int useDuration;
    private final String[] descriptionKeys;

    /**
     * @param properties       物品属性，应已包含 food(...)
     * @param useDuration      使用时长（tick）；传 0 表示使用原版默认值
     * @param descriptionKeys  一个或多个翻译键，按顺序附加到 tooltip
     */
    public TooltipFoodItem(Properties properties, int useDuration, String... descriptionKeys) {
        super(properties);
        this.useDuration = useDuration;
        this.descriptionKeys = descriptionKeys;
    }

    /**
     * 不带自定义使用时长的便捷构造。
     */
    public TooltipFoodItem(Properties properties, String... descriptionKeys) {
        this(properties, 0, descriptionKeys);
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
        return useDuration > 0 ? useDuration : super.getUseDuration(stack, entity);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, context, list, flag);
        for (String key : descriptionKeys) {
            list.add(Component.translatable(key));
        }
    }
}
