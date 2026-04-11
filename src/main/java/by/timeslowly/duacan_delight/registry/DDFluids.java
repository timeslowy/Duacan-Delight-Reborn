package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.Duacan_delight;
import by.timeslowly.duacan_delight.common.fluid.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DDFluids {
    public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(BuiltInRegistries.FLUID, Duacan_delight.MOD_ID);
    public static final DeferredHolder<Fluid, FlowingFluid> SOYBEAN_MILK_FLUID = REGISTRY.register("soybean_milk_fluid", SoybeanMilkFluidFluid.Source::new);
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_SOYBEAN_MILK_FLUID = REGISTRY.register("flowing_soybean_milk_fluid", SoybeanMilkFluidFluid.Flowing::new);
    public static final DeferredHolder<Fluid, FlowingFluid> TEAOIL_FLUID = REGISTRY.register("teaoil_fluid", TeaoilFluidFluid.Source::new);
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_TEAOIL_FLUID = REGISTRY.register("flowing_teaoil_fluid", TeaoilFluidFluid.Flowing::new);

    @EventBusSubscriber(modid = Duacan_delight.MOD_ID, value = Dist.CLIENT)
    public static class FluidsClientSideHandler {
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(SOYBEAN_MILK_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FLOWING_SOYBEAN_MILK_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(TEAOIL_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FLOWING_TEAOIL_FLUID.get(), RenderType.translucent());
        }
    }
}
