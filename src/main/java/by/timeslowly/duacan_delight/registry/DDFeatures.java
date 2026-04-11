package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.Duacan_delight;
import by.timeslowly.duacan_delight.world.features.LaverFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DDFeatures {
    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(Registries.FEATURE, Duacan_delight.MOD_ID);

    public static final net.neoforged.neoforge.registries.DeferredHolder<Feature<?>, LaverFeature> LAVER_PLANT =
            REGISTRY.register("laver_plant", () -> new LaverFeature(NoneFeatureConfiguration.CODEC));
}
