
package by.timeslowly.duacan_delight.common.fluid;

import by.timeslowly.duacan_delight.registry.DDBlocks;
import by.timeslowly.duacan_delight.registry.DDItems;
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

public abstract class TeaoilFluidFluid extends BaseFlowingFluid {
	public static final Properties PROPERTIES = new Properties(() -> DDFluidTypes.TEAOIL_FLUID_TYPE.get(), () -> DDFluids.TEAOIL_FLUID.get(),
			() -> DDFluids.FLOWING_TEAOIL_FLUID.get()).explosionResistance(100f).bucket(() -> DDItems.TEAOIL_FLUID_BUCKET.get()).block(() -> (LiquidBlock) DDBlocks.TEAOIL_FLUID.get());

	private TeaoilFluidFluid() {
		super(PROPERTIES);
	}

	@Override
	public ParticleOptions getDripParticle() {
		return ParticleTypes.ASH;
	}

	public static class Source extends TeaoilFluidFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends TeaoilFluidFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}
}
