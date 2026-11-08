package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class EajianTomatoSauceItem extends TooltipFoodItem {
	public EajianTomatoSauceItem() {
		super(new Properties()
				.stacksTo(64)
				.rarity(Rarity.UNCOMMON)
				.food(new FoodProperties.Builder()
						.nutrition(12)
						.saturationModifier(1f)
						.usingConvertsTo(Items.BOWL)
						.build()),
				18,
				"item.duacan_delight.eajian_tomato_sauce.description_1");
	}
}
