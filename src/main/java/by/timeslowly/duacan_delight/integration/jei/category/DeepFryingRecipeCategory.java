package by.timeslowly.duacan_delight.integration.jei.category;

import by.timeslowly.duacan_delight.DuacanDelight;
import by.timeslowly.duacan_delight.common.crafting.DeepFryingRecipe;
import by.timeslowly.duacan_delight.integration.jei.DDJeiRecipeTypes;
import by.timeslowly.duacan_delight.registry.DDBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * JEI 的“油炸”配方分类。
 * <p>
 * 外观对齐炸锅本体界面（{@code textures/gui/deep_frying_pan.png}）：
 * 豆沙色面板、灰底槽位，排版为「原料 → 箭头 → 成品 / 容具」。
 * <p>
 * 贴图 {@code textures/gui/jei/deep_frying.png} 为 160×64，布局如下：
 * <pre>
 *   (0,0)   116×61  分类面板（含深棕边框、三个槽位、灰色箭头与彩色火焰）
 *   (116,0) 8×11    沙漏（时间）帧
 *   (116,11) 24×17  箭头填充帧 —— 供流动动画裁剪使用
 * </pre>
 * 沙漏与箭头帧都只存在于这张 JEI 专用贴图里，工作界面贴图不含它们。
 * <p>
 * 箭头按配方 {@code cookingtime} 做「从右往左延伸」的流动动画；
 * 沙漏画在箭头正上方，鼠标悬停时显示烹饪时间。
 */
public class DeepFryingRecipeCategory implements IRecipeCategory<RecipeHolder<DeepFryingRecipe>> {
    /** 分类可见区域 = 面板尺寸。 */
    public static final int WIDTH = 116;
    public static final int HEIGHT = 61;

    /** 槽位内容区（面板贴图里已画好外框，故此处为内容区左上角）。 */
    public static final int INPUT_X = 32;
    public static final int INPUT_Y = 11;
    public static final int OUTPUT_X = 89;
    public static final int OUTPUT_Y = 11;
    public static final int CONTAINER_X = 89;
    public static final int CONTAINER_Y = 37;

    /** 箭头填充帧在贴图中的位置与尺寸。 */
    private static final int ARROW_U = 116;
    private static final int ARROW_V = 11;
    private static final int ARROW_WIDTH = 24;
    private static final int ARROW_HEIGHT = 17;
    /**
     * 箭头填充帧的绘制位置。
     * 面板里箭头的尖端在 (72,12)，而帧内尖端相对帧原点偏移 (16,0)，
     * 故 72-16=56、12-0=12；此时帧内横杆行 (17-11=6) 正好落在面板横杆 y=18 上。
     */
    private static final int ARROW_X = 56;
    private static final int ARROW_Y = 12;

    /** 沙漏（时间）帧在贴图中的位置与尺寸。 */
    private static final int TIME_ICON_U = 116;
    private static final int TIME_ICON_V = 0;
    private static final int TIME_ICON_WIDTH = 8;
    private static final int TIME_ICON_HEIGHT = 11;
    /**
     * 沙漏绘制位置：箭头正上方居中。
     * 箭头图形横向中心为 68.5，沙漏宽 8，故 x=68-4=64；
     * 沙漏底边 y=11 正好贴住箭头尖端 y=12。
     */
    private static final int TIME_ICON_X = 64;
    private static final int TIME_ICON_Y = 1;

    /** 鼠标悬停查询时间的判定区（覆盖沙漏与箭头）。 */
    private static final int TOOLTIP_X = 56;
    private static final int TOOLTIP_Y = 1;
    private static final int TOOLTIP_WIDTH = 24;
    private static final int TOOLTIP_HEIGHT = 28;

    /** 贴图实际尺寸；务必与 {@code setTextureSize} 一起告知 JEI，否则会被按 256×256 拉伸。 */
    private static final int TEXTURE_WIDTH = 160;
    private static final int TEXTURE_HEIGHT = 64;

    private static final ResourceLocation BACKGROUND_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(DuacanDelight.MOD_ID, "textures/gui/jei/deep_frying.png");

    private final IDrawable background;
    private final IDrawable timeIcon;
    private final IDrawableAnimated arrow;
    private final IDrawable icon;
    private final Component title;

    public DeepFryingRecipeCategory(IGuiHelper guiHelper) {
        // 注意：createDrawable(...) 内部按 256×256 图集换算 UV，
        // 贴图不是 256×256 时必须用 drawableBuilder + setTextureSize 指明真实尺寸，
        // 否则只会采样到左上一部分再拉伸铺满。
        this.background = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 0, 0, WIDTH, HEIGHT)
                .setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT)
                .build();
        this.timeIcon = guiHelper.drawableBuilder(BACKGROUND_TEXTURE,
                        TIME_ICON_U, TIME_ICON_V, TIME_ICON_WIDTH, TIME_ICON_HEIGHT)
                .setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT)
                .build();
        // 与农夫乐事厨锅同款：200 tick 一个循环，从右往左逐渐填充
        this.arrow = guiHelper.drawableBuilder(BACKGROUND_TEXTURE,
                        ARROW_U, ARROW_V, ARROW_WIDTH, ARROW_HEIGHT)
                .setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(DDBlocks.DEEP_FRYING_PAN.get()));
        this.title = Component.translatable("gui.duacan_delight.jei.deep_frying");
    }

    @Override
    public @NotNull RecipeType<RecipeHolder<DeepFryingRecipe>> getRecipeType() {
        return DDJeiRecipeTypes.DEEP_FRYING;
    }

    @Override
    public @NotNull Component getTitle() {
        return this.title;
    }

    /** 背景改在 {@link #draw} 里绘制，以便在其上叠加箭头动画与沙漏。 */
    @Override
    public @Nullable IDrawable getBackground() {
        return null;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull RecipeHolder<DeepFryingRecipe> holder, @NotNull IFocusGroup focusGroup) {
        DeepFryingRecipe recipe = holder.value();

        builder.addSlot(RecipeIngredientRole.INPUT, INPUT_X, INPUT_Y)
                .addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, OUTPUT_X, OUTPUT_Y)
                .addItemStack(getResultItem(recipe));

        // 配方声明了容器时，在成品下方显示需要准备的容器（如碗）。
        ItemStack container = recipe.getOutputContainer();
        if (!container.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.CATALYST, CONTAINER_X, CONTAINER_Y)
                    .addItemStack(container);
        }
    }

    @Override
    public void draw(@NotNull RecipeHolder<DeepFryingRecipe> holder, @NotNull IRecipeSlotsView recipeSlotsView,
                     @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics, 0, 0);
        this.arrow.draw(guiGraphics, ARROW_X, ARROW_Y);
        this.timeIcon.draw(guiGraphics, TIME_ICON_X, TIME_ICON_Y);
    }

    @Override
    public void getTooltip(@NotNull ITooltipBuilder tooltip, @NotNull RecipeHolder<DeepFryingRecipe> holder,
                           @NotNull IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if (isCursorInside(TOOLTIP_X, TOOLTIP_Y, TOOLTIP_WIDTH, TOOLTIP_HEIGHT, mouseX, mouseY)) {
            int cookTime = holder.value().getCookingTime();
            if (cookTime > 0) {
                tooltip.add(Component.translatable("gui.jei.category.smelting.time.seconds", cookTime / 20));
            }
        }
    }

    private static boolean isCursorInside(int x, int y, int width, int height, double mouseX, double mouseY) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    /**
     * 优先用客户端世界的注册表解析产物；世界尚未加载时退回空注册表。
     * 油炸配方本身不依赖注册表，这里只是防止 JEI 在极端时机下空指针。
     */
    private static ItemStack getResultItem(DeepFryingRecipe recipe) {
        ClientLevel level = Minecraft.getInstance().level;
        RegistryAccess registryAccess = level == null ? RegistryAccess.EMPTY : level.registryAccess();
        return recipe.getResultItem(registryAccess);
    }
}
