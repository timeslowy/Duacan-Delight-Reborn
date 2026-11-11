package by.timeslowly.duacan_delight.integration.jei;

import by.timeslowly.duacan_delight.DuacanDelight;
import by.timeslowly.duacan_delight.common.crafting.DeepFryingRecipe;
import by.timeslowly.duacan_delight.integration.jei.category.DeepFryingRecipeCategory;
import by.timeslowly.duacan_delight.registry.DDRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import by.timeslowly.duacan_delight.registry.DDBlocks;

import java.util.List;

/**
 * JEI 插件：注册“油炸”分类、配方内容与催化方块（炸锅）。
 * <p>
 * 移植自随意乐事（Casualness Delight）的 {@code CDPlugin}，
 * 另补上 {@link IRecipeCatalystRegistration}：
 * 这样在 JEI 里查询炸锅时能看到它能炸什么，原版随意乐事遗漏了这一步。
 */
@JeiPlugin
public class DDJeiPlugin implements IModPlugin {
    private static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(DuacanDelight.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new DeepFryingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) {
            return;
        }
        RecipeManager recipeManager = level.getRecipeManager();
        List<RecipeHolder<DeepFryingRecipe>> recipes = recipeManager.getAllRecipesFor(DDRecipeTypes.DEEP_FRYING.get());
        registration.addRecipes(DDJeiRecipeTypes.DEEP_FRYING, recipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(DDBlocks.DEEP_FRYING_PAN.get()), DDJeiRecipeTypes.DEEP_FRYING);
    }
}
