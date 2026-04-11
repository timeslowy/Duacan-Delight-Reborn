
package by.timeslowly.duacan_delight.common.block;

import by.timeslowly.duacan_delight.registry.DDFluids;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class SoybeanMilkFluidBlock extends LiquidBlock {
	public SoybeanMilkFluidBlock() {
		super(DDFluids.SOYBEAN_MILK_FLUID.get(), Properties.of().mapColor(MapColor.WATER).strength(100f).noCollission().noLootTable().liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
	}
}
