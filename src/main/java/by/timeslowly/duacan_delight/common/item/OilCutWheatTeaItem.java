package by.timeslowly.duacan_delight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OilCutWheatTeaItem extends Item {
	public OilCutWheatTeaItem() {
		super(new Properties()
				.stacksTo(1)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(2)
						.saturationModifier(0.5f)
						.alwaysEdible()
						.usingConvertsTo(Items.BUCKET)
						.build()));
	}

	@Override
	public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.oil_cut_wheat_tea.description_1"));
	}
}
