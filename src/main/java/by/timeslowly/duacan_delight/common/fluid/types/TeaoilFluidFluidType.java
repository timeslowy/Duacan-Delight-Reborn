
package by.timeslowly.duacan_delight.common.fluid.types;

import by.timeslowly.duacan_delight.Duacan_delight;
import by.timeslowly.duacan_delight.registry.DDFluidTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = Duacan_delight.MOD_ID)
public final class TeaoilFluidFluidType extends FluidType {
	public TeaoilFluidFluidType() {
		super(Properties.create().canSwim(false).canDrown(false).pathType(PathType.LAVA).adjacentPathType(null).motionScale(0.007D).viscosity(1100).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY).sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH));
	}

	@SubscribeEvent
	public static void registerFluidTypeExtensions(@NotNull RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation STILL_TEXTURE = ResourceLocation.parse("duacan_delight:block/fluid/tea_oil/tea_oil");
			private static final ResourceLocation FLOWING_TEXTURE = ResourceLocation.parse("duacan_delight:block/fluid/tea_oil/flowing_teaoil");

			@Override
			public @NotNull ResourceLocation getStillTexture() {
				return STILL_TEXTURE;
			}

			@Override
			public @NotNull ResourceLocation getFlowingTexture() {
				return FLOWING_TEXTURE;
			}
		}, DDFluidTypes.TEAOIL_FLUID_TYPE.get());
	}
}
