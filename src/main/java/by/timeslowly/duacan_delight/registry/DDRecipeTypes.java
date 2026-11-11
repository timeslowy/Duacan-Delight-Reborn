package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.DuacanDelight;
import by.timeslowly.duacan_delight.common.crafting.DeepFryingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * 配方类型注册。目前只有油炸（deep_frying）一种。
 */
public class DDRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> REGISTRY =
            DeferredRegister.create(Registries.RECIPE_TYPE, DuacanDelight.MOD_ID);

    public static final Supplier<RecipeType<DeepFryingRecipe>> DEEP_FRYING =
            REGISTRY.register("deep_frying", () -> registerRecipeType("deep_frying"));

    private static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<>() {
            @Override
            public String toString() {
                return DuacanDelight.MOD_ID + ":" + identifier;
            }
        };
    }
}
