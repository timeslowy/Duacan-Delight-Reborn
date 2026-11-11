package by.timeslowly.duacan_delight.integration.jei;

import by.timeslowly.duacan_delight.common.crafting.DeepFryingRecipe;
import by.timeslowly.duacan_delight.registry.DDRecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;

/**
 * JEI 侧的配方类型引用，直接复用原版配方类型。
 */
public final class DDJeiRecipeTypes {
    public static final RecipeType<RecipeHolder<DeepFryingRecipe>> DEEP_FRYING =
            RecipeType.createFromVanilla(DDRecipeTypes.DEEP_FRYING.get());

    private DDJeiRecipeTypes() {
    }
}
