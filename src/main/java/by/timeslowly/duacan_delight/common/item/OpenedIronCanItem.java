
package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class OpenedIronCanItem extends Item {
	public OpenedIronCanItem() {
		super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
