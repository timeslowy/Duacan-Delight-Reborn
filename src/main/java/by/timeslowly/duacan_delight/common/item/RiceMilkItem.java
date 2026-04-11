
package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class RiceMilkItem extends Item {
	public RiceMilkItem() {
		super(new Properties()
				.stacksTo(16)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(4)
						.saturationModifier(0.5f)
						.alwaysEdible()
						.build())
				.craftRemainder(Items.BOWL));
	}
}
