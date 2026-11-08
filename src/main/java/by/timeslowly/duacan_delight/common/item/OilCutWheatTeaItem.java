package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class OilCutWheatTeaItem extends DrinkableFoodItem {
	public OilCutWheatTeaItem() {
		super(new Properties()
				.stacksTo(1)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(2)
						.saturationModifier(0.5f)
						.alwaysEdible()
						.usingConvertsTo(Items.BUCKET)
						.build()),
				"item.duacan_delight.oil_cut_wheat_tea.description_1");
	}
}
