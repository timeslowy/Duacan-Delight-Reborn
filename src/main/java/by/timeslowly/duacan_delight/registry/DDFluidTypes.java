package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.Duacan_delight;
import by.timeslowly.duacan_delight.common.fluid.types.SoybeanMilkFluidFluidType;
import by.timeslowly.duacan_delight.common.fluid.types.TeaoilFluidFluidType;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DDFluidTypes {
    public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Duacan_delight.MOD_ID);
    public static final DeferredHolder<FluidType, FluidType> SOYBEAN_MILK_FLUID_TYPE = REGISTRY.register("soybean_milk_fluid", SoybeanMilkFluidFluidType::new);
    public static final DeferredHolder<FluidType, FluidType> TEAOIL_FLUID_TYPE = REGISTRY.register("teaoil_fluid", TeaoilFluidFluidType::new);
}
