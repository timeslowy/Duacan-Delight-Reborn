
package by.timeslowly.duacan_delight.common.fluid;

import by.timeslowly.duacan_delight.registry.DDBlocks;
import by.timeslowly.duacan_delight.registry.DDFluidTypes;
import by.timeslowly.duacan_delight.registry.DDFluids;
import by.timeslowly.duacan_delight.registry.DDItems;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import org.jetbrains.annotations.NotNull;

public abstract class SoybeanMilkFluidFluid extends BaseFlowingFluid {
	public static final Properties PROPERTIES = new Properties(DDFluidTypes.SOYBEAN_MILK_FLUID_TYPE, DDFluids.SOYBEAN_MILK_FLUID,
            DDFluids.FLOWING_SOYBEAN_MILK_FLUID).explosionResistance(100f).bucket(DDItems.SOYBEAN_MILK_FLUID_BUCKET).block(() -> (LiquidBlock) DDBlocks.SOYBEAN_MILK_FLUID.get());

	private SoybeanMilkFluidFluid() {
		super(PROPERTIES);
	}

	@Override
	public ParticleOptions getDripParticle() {
		return ParticleTypes.ASH;
	}

	public static class Source extends SoybeanMilkFluidFluid {
		public int getAmount(@NotNull FluidState state) {
			return 8;
		}

		public boolean isSource(@NotNull FluidState state) {
			return true;
		}
	}

	public static class Flowing extends SoybeanMilkFluidFluid {
		protected void createFluidStateDefinition(StateDefinition.@NotNull Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(@NotNull FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(@NotNull FluidState state) {
			return false;
		}
	}
}
