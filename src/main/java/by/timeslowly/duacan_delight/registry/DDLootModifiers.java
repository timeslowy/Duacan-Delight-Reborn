package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.Duacan_delight;
import by.timeslowly.duacan_delight.common.loot.*;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class DDLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> REGISTRY =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Duacan_delight.MOD_ID);

    // 在这里注册所有修改器
    public static final Supplier<MapCodec<TremellaSporeModifier>> TREMELLA_SPORE_MODIFIER =
            REGISTRY.register("tremella_spore_modifier", () -> TremellaSporeModifier.CODEC);

    // 未来添加新修改器示例：
    // public static final Supplier<MapCodec<AnotherModifier>> ANOTHER_MODIFIER =
    //         REGISTRY.register("another_modifier", () -> AnotherModifier.CODEC);
}