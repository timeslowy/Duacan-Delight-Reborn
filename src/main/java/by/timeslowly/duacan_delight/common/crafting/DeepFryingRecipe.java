package by.timeslowly.duacan_delight.common.crafting;

import by.timeslowly.duacan_delight.registry.DDBlocks;
import by.timeslowly.duacan_delight.registry.DDRecipeSerializers;
import by.timeslowly.duacan_delight.registry.DDRecipeTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * 油炸配方。
 * <p>
 * 最初移植自随意乐事（Casualness Delight，作者 Ginko）的 {@code DeepFryingRecipe}。
 * 加入容器界面后，配方改为自带序列化器，以便支持可选的 {@code container} 字段：
 * <pre>
 * {
 *   "type": "duacan_delight:deep_frying",
 *   "category": "food",
 *   "ingredient": { "item": "duacan_delight:tofu" },
 *   "result": { "id": "duacan_delight:fried_tofu" },
 *   "container": { "id": "minecraft:bowl" },   // 可选
 *   "experience": 0.35,
 *   "cookingtime": 100
 * }
 * </pre>
 * 除新增的 {@code container} 外，字段与原版烹饪配方保持一致，
 * 现有 4 条配方无需改写即可继续使用。
 */
public class DeepFryingRecipe implements net.minecraft.world.item.crafting.Recipe<SingleRecipeInput> {
    private final String group;
    private final CookingBookCategory category;
    private final Ingredient ingredient;
    private final ItemStack result;
    /** 配方声明的成品容器；为空表示直接产出干食物。 */
    private final ItemStack container;
    private final float experience;
    private final int cookingTime;

    public DeepFryingRecipe(String group, CookingBookCategory category, Ingredient ingredient,
                            ItemStack result, ItemStack container, float experience, int cookingTime) {
        this.group = group;
        this.category = category;
        this.ingredient = ingredient;
        this.result = result;
        this.container = container == null ? ItemStack.EMPTY : container;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    public String getGroup() {
        return this.group;
    }

    public CookingBookCategory category() {
        return this.category;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.ingredient);
        return list;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull HolderLookup.Provider registries) {
        return this.result;
    }

    /** 配方声明的成品容器；供方块实体决定容具槽是否需要消耗容器。 */
    public ItemStack getOutputContainer() {
        return this.container;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SingleRecipeInput input, @NotNull HolderLookup.Provider registries) {
        return this.result.copy();
    }

    public float getExperience() {
        return this.experience;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    @Override
    public boolean matches(@NotNull SingleRecipeInput input, @NotNull Level level) {
        return this.ingredient.test(input.item());
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return DDRecipeSerializers.DEEP_FRYING.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return DDRecipeTypes.DEEP_FRYING.get();
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(DDBlocks.DEEP_FRYING_PAN.get());
    }

    /** 序列化器：字段与原版烹饪配方兼容，额外支持可选的 {@code container}。 */
    public static class Serializer implements RecipeSerializer<DeepFryingRecipe> {
        private static final MapCodec<DeepFryingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(DeepFryingRecipe::getGroup),
                CookingBookCategory.CODEC.optionalFieldOf("category", CookingBookCategory.FOOD).forGetter(DeepFryingRecipe::category),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(r -> r.ingredient),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(r -> r.result),
                ItemStack.STRICT_CODEC.optionalFieldOf("container", ItemStack.EMPTY).forGetter(DeepFryingRecipe::getOutputContainer),
                Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(DeepFryingRecipe::getExperience),
                Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(DeepFryingRecipe::getCookingTime)
        ).apply(inst, DeepFryingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, DeepFryingRecipe> STREAM_CODEC =
                StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        @Override
        public @NotNull MapCodec<DeepFryingRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, DeepFryingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static DeepFryingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            CookingBookCategory category = buffer.readEnum(CookingBookCategory.class);
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            ItemStack container = ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer);
            float experience = buffer.readFloat();
            int cookingTime = buffer.readVarInt();
            return new DeepFryingRecipe(group, category, ingredient, result, container, experience, cookingTime);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, DeepFryingRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.ingredient);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            ItemStack.OPTIONAL_STREAM_CODEC.encode(buffer, recipe.container);
            buffer.writeFloat(recipe.experience);
            buffer.writeVarInt(recipe.cookingTime);
        }
    }
}
