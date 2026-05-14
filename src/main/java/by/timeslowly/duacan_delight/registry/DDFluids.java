package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.Duacan_delight;
import by.timeslowly.duacan_delight.common.fluid.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DDFluids {
    public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(BuiltInRegistries.FLUID, Duacan_delight.MOD_ID);
    public static final DeferredHolder<Fluid, FlowingFluid> SOYBEAN_MILK_FLUID = REGISTRY.register("soybean_milk_fluid", SoybeanMilkFluid.Source::new);
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_SOYBEAN_MILK_FLUID = REGISTRY.register("flowing_soybean_milk_fluid", SoybeanMilkFluid.Flowing::new);
    public static final DeferredHolder<Fluid, FlowingFluid> TEAOIL_FLUID = REGISTRY.register("teaoil_fluid", TeaoilFluid.Source::new);
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_TEAOIL_FLUID = REGISTRY.register("flowing_teaoil_fluid", TeaoilFluid.Flowing::new);
}
