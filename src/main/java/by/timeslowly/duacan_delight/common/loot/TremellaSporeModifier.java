package by.timeslowly.duacan_delight.common.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
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

    // 公共的序列化器，供注册使用
    public static final MapCodec<TremellaSporeModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
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
                            .fieldOf("probability")
                            .forGetter(m -> m.probability))
                    .apply(instance, TremellaSporeModifier::new)
    );

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