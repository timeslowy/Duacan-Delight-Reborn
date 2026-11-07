package by.timeslowly.duacan_delight.common.fluid.types;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;

public class TeaoilFluidFluidType extends FluidType {
	public TeaoilFluidFluidType() {
		super(Properties.create().canSwim(false).canDrown(false).pathType(PathType.LAVA).adjacentPathType(null).motionScale(0.007D).viscosity(1100).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY).sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH));
	}
}
