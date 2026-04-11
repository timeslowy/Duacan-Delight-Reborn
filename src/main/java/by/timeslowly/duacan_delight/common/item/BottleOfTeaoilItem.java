
package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class BottleOfTeaoilItem extends Item {
	public BottleOfTeaoilItem() {
		super(new Properties()
				.stacksTo(16)
				.rarity(Rarity.COMMON)
				.craftRemainder(Items.GLASS_BOTTLE));
	}
}
