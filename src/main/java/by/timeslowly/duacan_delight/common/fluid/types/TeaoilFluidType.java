package by.timeslowly.duacan_delight.common.fluid.types;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;

/**
 * 茶油的流体类型。
 * <p>
 * 类名已从 {@code TeaoilFluidFluidType} 简化为 {@code TeaoilFluidType}，
 * 去掉与 {@code FluidType} 基类重复的 "Fluid"。注册 ID 不变。
 */
public class TeaoilFluidType extends FluidType {
	public TeaoilFluidType() {
		super(Properties.create().canSwim(false).canDrown(false).pathType(PathType.LAVA).adjacentPathType(null).motionScale(0.007D).viscosity(1100).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY).sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH));
	}
}
