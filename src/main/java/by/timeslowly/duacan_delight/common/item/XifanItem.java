package by.timeslowly.duacan_delight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class XifanItem extends Item {
	public XifanItem() {
		super(new Properties()
				.stacksTo(16)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(4)
						.saturationModifier(1f)
						.usingConvertsTo(Items.BOWL)
						.build()));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.xifan.description_0"));
	}
}
