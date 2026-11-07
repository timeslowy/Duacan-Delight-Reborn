package by.timeslowly.duacan_delight.common.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapLike;
import com.mojang.serialization.RecordBuilder;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class TremellaSporeModifier extends LootModifier {

    // 基础字段 codec。单独拆成常量是为了让类型推断稳定（直接在字段上链式调用 mapResult 会把泛型推成 Object）。
    private static final MapCodec<TremellaSporeModifier> BASE_CODEC = RecordCodecBuilder.mapCodec(instance ->
            codecStart(instance)
                    .and(BuiltInRegistries.ITEM.byNameCodec()
                            .fieldOf("item_id")
                            .forGetter(m -> m.item))
                    .and(ExtraCodecs.NON_NEGATIVE_INT
                            .fieldOf("min_count")
                            .forGetter(m -> m.minCount))
                    .and(ExtraCodecs.NON_NEGATIVE_INT
                            .fieldOf("max_count")
                            .forGetter(m -> m.maxCount))
                    .and(Codec.DOUBLE
                            .validate(TremellaSporeModifier::validateProbability)
                            .fieldOf("probability")
                            .forGetter(m -> m.probability))
                    .apply(instance, TremellaSporeModifier::new)
    );

    // 公共的序列化器，供注册使用。
    // codec 层就把范围钉死：probability 必须在 [0,1]，min_count 非负，
    // 且 max_count 不得小于 min_count，避免运行时 nextInt(负数) 抛异常。
    public static final MapCodec<TremellaSporeModifier> CODEC = BASE_CODEC.mapResult(new CountRangeValidator());

    private static DataResult<Double> validateProbability(double probability) {
        return probability >= 0.0D && probability <= 1.0D
                ? DataResult.success(probability)
                : DataResult.error(() -> "probability must be within range [0.0;1.0]: " + probability);
    }

    // 跨字段校验：max_count 必须 >= min_count。用 mapResult 而不是在构造器抛异常，
    // 这样错误 JSON 会在资源加载阶段给出可读报错，而不是把启动/重载直接打断。
    private static final class CountRangeValidator implements MapCodec.ResultFunction<TremellaSporeModifier> {
        @Override
        public <T> DataResult<TremellaSporeModifier> apply(DynamicOps<T> ops, MapLike<T> input, DataResult<TremellaSporeModifier> result) {
            return result.flatMap(modifier -> modifier.maxCount >= modifier.minCount
                    ? DataResult.success(modifier)
                    : DataResult.error(() -> "max_count (" + modifier.maxCount
                            + ") must be greater than or equal to min_count (" + modifier.minCount + ")"));
        }

        @Override
        public <T> RecordBuilder<T> coApply(DynamicOps<T> ops, TremellaSporeModifier modifier, RecordBuilder<T> builder) {
            return builder;
        }
    }

    private final Item item;
    private final int minCount;
    private final int maxCount;
    private final double probability;

    public TremellaSporeModifier(LootItemCondition[] conditionsIn, Item item, int minCount, int maxCount, double probability) {
        super(conditionsIn);
        this.item = item;
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.probability = probability;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> generatedLoot, @NotNull LootContext context) {
        if (context.getRandom().nextDouble() < probability) {
            int count = minCount + context.getRandom().nextInt(maxCount - minCount + 1);
            generatedLoot.add(new ItemStack(item, count));
        }
        return generatedLoot;
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
