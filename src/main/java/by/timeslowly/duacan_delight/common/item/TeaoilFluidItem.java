
package by.timeslowly.duacan_delight.common.item;

import by.timeslowly.duacan_delight.registry.DDFluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class TeaoilFluidItem extends BucketItem {
	public TeaoilFluidItem() {
		super(DDFluids.TEAOIL_FLUID.get(), new Properties().craftRemainder(Items.BUCKET).stacksTo(1).rarity(Rarity.COMMON));
	}
}
