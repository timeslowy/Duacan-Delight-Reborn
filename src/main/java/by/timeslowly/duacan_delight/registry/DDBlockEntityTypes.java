package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.DuacanDelight;
import by.timeslowly.duacan_delight.common.block.entity.DeepFryingPanBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * 方块实体类型注册。炸锅是本模组第一个方块实体。
 */
public class DDBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, DuacanDelight.MOD_ID);

    public static final Supplier<BlockEntityType<DeepFryingPanBlockEntity>> DEEP_FRYING_PAN = REGISTRY.register("deep_frying_pan",
            () -> BlockEntityType.Builder.of(DeepFryingPanBlockEntity::new, DDBlocks.DEEP_FRYING_PAN.get()).build(null));
}
