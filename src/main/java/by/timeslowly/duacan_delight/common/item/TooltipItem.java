package by.timeslowly.duacan_delight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// 通用物品类：仅附加翻译文本作为物品描述，无需为每种物品单独建类
// 接受可变数量的翻译键，支持多行描述
public class TooltipItem extends Item {
    private final String[] descriptionKeys;

    public TooltipItem(Properties properties, String... descriptionKeys) {
        super(properties);
        this.descriptionKeys = descriptionKeys;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, context, list, flag);
        for (String key : descriptionKeys) {
            list.add(Component.translatable(key));
        }
    }
}
