package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class XifanWithMushroomMeatSauceItem extends TooltipFoodItem {
	public XifanWithMushroomMeatSauceItem() {
		super(new Properties()
				.stacksTo(16)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(8)
						.saturationModifier(0.8f)
						.usingConvertsTo(Items.BOWL)
						.build()),
				22,
				"item.duacan_delight.xifan_with_mushroom_meat_sauce.description_0");
	}
}
