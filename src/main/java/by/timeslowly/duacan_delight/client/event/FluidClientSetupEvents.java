package by.timeslowly.duacan_delight.client.event;

import by.timeslowly.duacan_delight.Duacan_delight;
import by.timeslowly.duacan_delight.registry.DDFluidTypes;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;

// 客户端流体外观集中在这里注册，并用 Dist.CLIENT 限制，避免 common 包引用客户端类。
// 与 client/event 下其它处理器保持同一模式。
@EventBusSubscriber(modid = Duacan_delight.MOD_ID, value = Dist.CLIENT)
public class FluidClientSetupEvents {

    @SubscribeEvent
    public static void registerFluidTypeExtensions(@NotNull RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final ResourceLocation STILL_TEXTURE =
                    ResourceLocation.fromNamespaceAndPath(Duacan_delight.MOD_ID, "block/fluid/soybean_milk/soybean_milk");
            private static final ResourceLocation FLOWING_TEXTURE =
                    ResourceLocation.fromNamespaceAndPath(Duacan_delight.MOD_ID, "block/fluid/soybean_milk/flowing_soybean_milk");

            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return STILL_TEXTURE;
            }

            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return FLOWING_TEXTURE;
            }
        }, DDFluidTypes.SOYBEAN_MILK_FLUID_TYPE.get());

        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final ResourceLocation STILL_TEXTURE =
                    ResourceLocation.fromNamespaceAndPath(Duacan_delight.MOD_ID, "block/fluid/tea_oil/tea_oil");
            private static final ResourceLocation FLOWING_TEXTURE =
                    ResourceLocation.fromNamespaceAndPath(Duacan_delight.MOD_ID, "block/fluid/tea_oil/flowing_teaoil");

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
