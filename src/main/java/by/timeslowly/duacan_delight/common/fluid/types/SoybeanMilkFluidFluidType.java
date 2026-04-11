
package by.timeslowly.duacan_delight.common.fluid.types;

import by.timeslowly.duacan_delight.Duacan_delight;
import by.timeslowly.duacan_delight.registry.DDFluidTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = Duacan_delight.MOD_ID)
public class SoybeanMilkFluidFluidType extends FluidType {
	public SoybeanMilkFluidFluidType() {
		super(Properties.create().fallDistanceModifier(0F).canExtinguish(true).supportsBoating(true).canHydrate(true).motionScale(0.007D).viscosity(1050).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY).sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH));
	}

	@SubscribeEvent
	public static void registerFluidTypeExtensions(@NotNull RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation STILL_TEXTURE = ResourceLocation.parse("duacan_delight:block/jing_zhi_dou_jiang_");
			private static final ResourceLocation FLOWING_TEXTURE = ResourceLocation.parse("duacan_delight:block/liu_dong_dou_jiang_1");

			@Override
			public @NotNull ResourceLocation getStillTexture() {
				return STILL_TEXTURE;
			}

			@Override
			public @NotNull ResourceLocation getFlowingTexture() {
				return FLOWING_TEXTURE;
			}
		}, DDFluidTypes.SOYBEAN_MILK_FLUID_TYPE.get());
	}
}
