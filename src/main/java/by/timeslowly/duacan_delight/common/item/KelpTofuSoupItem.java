package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class KelpTofuSoupItem extends DrinkableFoodItem {
	public KelpTofuSoupItem() {
		super(new Properties()
				.stacksTo(16)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(5)
						.saturationModifier(0.6f)
						.alwaysEdible()
						.usingConvertsTo(Items.BOWL)
						.build()),
				"item.duacan_delight.kelp_tofu_soup.description_0");
	}
}
