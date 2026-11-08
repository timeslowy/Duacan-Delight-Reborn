package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class RousongxifanItem extends TooltipFoodItem {
	public RousongxifanItem() {
		super(new Properties()
				.stacksTo(16)
				.rarity(Rarity.UNCOMMON)
				.food(new FoodProperties.Builder()
						.nutrition(8)
						.saturationModifier(0.8f)
						.usingConvertsTo(Items.BOWL)
						.build()),
				22,
				"item.duacan_delight.rousongxifan.description_0");
	}
}
