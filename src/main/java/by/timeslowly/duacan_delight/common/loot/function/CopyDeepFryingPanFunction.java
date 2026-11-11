package by.timeslowly.duacan_delight.common.loot.function;

import by.timeslowly.duacan_delight.common.block.entity.DeepFryingPanBlockEntity;
import by.timeslowly.duacan_delight.registry.DDLootFunctions;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * 掉落时把方块实体里记录的“炸锅物品”写进掉落物，
 * 从而保留附魔、铁砧改名等 NBT 数据。
 * <p>
 * 移植自随意乐事（Casualness Delight）的 {@code CopyDeepFryingPanFunction}，
 * 结构与农夫乐事的 {@code CopySkilletFunction} 一致。
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CopyDeepFryingPanFunction extends LootItemConditionalFunction {
    public static final MapCodec<CopyDeepFryingPanFunction> CODEC = RecordCodecBuilder.mapCodec(
            instance -> commonFields(instance).apply(instance, CopyDeepFryingPanFunction::new)
    );

    private CopyDeepFryingPanFunction(List<LootItemCondition> conditions) {
        super(conditions);
    }

    public static Builder<?> builder() {
        return simpleBuilder(CopyDeepFryingPanFunction::new);
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        BlockEntity blockEntity = context.getParamOrNull(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof DeepFryingPanBlockEntity deepFryingPan) {
            ItemStack panStack = deepFryingPan.getDeepFryingPanAsItem();
            if (!panStack.isEmpty()) {
                return panStack;
            }
        }
        return stack;
    }

    @Override
    public LootItemFunctionType<CopyDeepFryingPanFunction> getType() {
        return DDLootFunctions.COPY_DEEP_FRYING_PAN.get();
    }
}
