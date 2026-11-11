package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.DuacanDelight;
import by.timeslowly.duacan_delight.common.crafting.DeepFryingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * 配方序列化器注册。
 * <p>
 * 油炸配方使用自定义序列化器，字段与原版烹饪配方一致，
 * 额外支持可选的 {@code container}（成品容器，例如 {@code minecraft:bowl}）。
 */
public class DDRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> REGISTRY =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, DuacanDelight.MOD_ID);

    public static final Supplier<RecipeSerializer<DeepFryingRecipe>> DEEP_FRYING =
            REGISTRY.register("deep_frying", DeepFryingRecipe.Serializer::new);
}
