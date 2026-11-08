package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.DuacanDelight;
import by.timeslowly.duacan_delight.common.fluid.types.SoybeanMilkFluidType;
import by.timeslowly.duacan_delight.common.fluid.types.TeaoilFluidType;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DDFluidTypes {
    public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, DuacanDelight.MOD_ID);
    public static final DeferredHolder<FluidType, FluidType> SOYBEAN_MILK_FLUID_TYPE = REGISTRY.register("soybean_milk_fluid", SoybeanMilkFluidType::new);
    public static final DeferredHolder<FluidType, FluidType> TEAOIL_FLUID_TYPE = REGISTRY.register("teaoil_fluid", TeaoilFluidType::new);
}
