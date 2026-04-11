
package by.timeslowly.duacan_delight.common.block;

import by.timeslowly.duacan_delight.registry.DDFluids;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class TeaoilFluidBlock extends LiquidBlock {
	public TeaoilFluidBlock() {
		super(DDFluids.TEAOIL_FLUID.get(), Properties.of().mapColor(MapColor.FIRE).strength(100f).noCollission().noLootTable().liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
	}
}
