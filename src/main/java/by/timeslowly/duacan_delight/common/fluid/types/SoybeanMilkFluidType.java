package by.timeslowly.duacan_delight.common.fluid.types;

import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;

/**
 * 豆浆的流体类型。
 * <p>
 * 类名已从 {@code SoybeanMilkFluidFluidType} 简化为 {@code SoybeanMilkFluidType}，
 * 去掉与 {@code FluidType} 基类重复的 "Fluid"。注册 ID 不变。
 */
public class SoybeanMilkFluidType extends FluidType {
	public SoybeanMilkFluidType() {
		super(Properties.create().fallDistanceModifier(0F).canExtinguish(true).supportsBoating(true).canHydrate(true).motionScale(0.007D).viscosity(1050).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY).sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH));
	}
}
