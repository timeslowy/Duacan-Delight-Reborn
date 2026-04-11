
package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class RousongItem extends Item {
	public RousongItem() {
		super(new Properties()
				.stacksTo(64)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(4)
						.saturationModifier(0.5f)
						.build())
				.craftRemainder(Items.BOWL));
	}
}
