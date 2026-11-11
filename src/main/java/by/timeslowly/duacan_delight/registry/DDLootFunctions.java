package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.DuacanDelight;
import by.timeslowly.duacan_delight.common.loot.function.CopyDeepFryingPanFunction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * 战利品函数注册。炸锅用它把方块实体里的物品数据带到掉落物上。
 */
public class DDLootFunctions {
    public static final DeferredRegister<LootItemFunctionType<?>> REGISTRY =
            DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, DuacanDelight.MOD_ID);

    public static final Supplier<LootItemFunctionType<CopyDeepFryingPanFunction>> COPY_DEEP_FRYING_PAN =
            REGISTRY.register("copy_deep_frying_pan", () -> new LootItemFunctionType<>(CopyDeepFryingPanFunction.CODEC));
}
