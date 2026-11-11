package by.timeslowly.duacan_delight.client.gui;

import by.timeslowly.duacan_delight.DuacanDelight;
import by.timeslowly.duacan_delight.common.block.entity.container.DeepFryingPanMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.awt.Rectangle;

/**
 * 炸锅的容器界面。
 * <p>
 * 布局参照农夫乐事厨锅（{@code CookingPotScreen}），但去掉了配方书，
 * 并把「食物展示槽」换成炸锅自己的成品输出槽。
 * <p>
 * 贴图 {@code textures/gui/deep_frying_pan.png} 为 256×256：
 * 左上 176×166 是主面板，右侧 x=176 起放两帧——
 * 「已加热」图标 (176,0) 18×18 与进度箭头填充帧 (176,18) 24×18。
 * <p>
 * 坐标约定与 {@code AbstractContainerScreen} 一致：{@link Rectangle} 的 x/y 就是
 * 贴图中该元素左上角的像素位置，因此可以逐个与菜单里的槽位坐标对齐。
 * <p>
 * <b>务必使用带 {@code textureWidth}/{@code textureHeight} 的 blit 重载。</b>
 * 不带尺寸的重载（7 参数版）一律按 <b>256×256</b> 换算 UV，
 * 贴图尺寸一旦不是 256×256 就会被错位采样并拉伸——此前界面「放大异常」正是这个原因。
 * <p>
 * 面板里的箭头与火焰画的是<b>暗色底</b>，亮色由右侧两帧按进度/加热状态覆盖，
 * 与农夫乐事厨锅的处理方式一致。
 */
public class DeepFryingPanScreen extends AbstractContainerScreen<DeepFryingPanMenu> {
    private static final ResourceLocation BACKGROUND_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(DuacanDelight.MOD_ID, "textures/gui/deep_frying_pan.png");

    /** 贴图整体尺寸，blit 换算 UV 时必须与之相符。 */
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;

    /** 热源图标：贴图里 18×18 的火焰框（Slot 坐标为 28,48）。 */
    private static final Rectangle HEAT_ICON = new Rectangle(53, 47, 18, 18);
    /** 进度箭头：贴图里 24×18 的箭头框，填充帧按进度裁剪宽度。 */
    private static final Rectangle PROGRESS_ARROW = new Rectangle(93, 23, 24, 18);
    /** 箭头「已填充」帧在贴图中的位置。 */
    private static final int ARROW_U = 176;
    private static final int ARROW_V = 18;
    /** 「已加热」帧在贴图中的位置。 */
    private static final int HEAT_U = 176;
    private static final int HEAT_V = 0;

    public DeepFryingPanScreen(DeepFryingPanMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.titleLabelX = 8;
        this.titleLabelY = 6;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 73;
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics gui, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.minecraft == null) {
            return;
        }

        // 主面板：显式传入贴图尺寸，避免按 256×256 图集换算 UV 导致缩放错位
        gui.blit(BACKGROUND_TEXTURE,
                this.leftPos, this.topPos, 0, 0,
                this.imageWidth, this.imageHeight,
                TEXTURE_WIDTH, TEXTURE_HEIGHT);

        // 已加热图标：用亮色火焰帧盖住面板里的暗色底框
        if (this.menu.isHeated()) {
            gui.blit(BACKGROUND_TEXTURE,
                    this.leftPos + HEAT_ICON.x, this.topPos + HEAT_ICON.y,
                    HEAT_U, HEAT_V, HEAT_ICON.width, HEAT_ICON.height,
                    TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }

        // 进度箭头：按进度从左往右裁剪填充帧，宽度为 0 时不绘制
        int progress = this.menu.getCookProgressionScaled();
        if (progress > 0) {
            gui.blit(BACKGROUND_TEXTURE,
                    this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y,
                    ARROW_U, ARROW_V, progress, PROGRESS_ARROW.height,
                    TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
    }

    @Override
    public void render(@NotNull GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        super.render(gui, mouseX, mouseY, partialTick);
        this.renderHeatIndicatorTooltip(gui, mouseX, mouseY);
    }

    private void renderHeatIndicatorTooltip(GuiGraphics gui, int mouseX, int mouseY) {
        if (this.isHovering(HEAT_ICON.x, HEAT_ICON.y, HEAT_ICON.width, HEAT_ICON.height, mouseX, mouseY)) {
            String key = this.menu.isHeated()
                    ? "block.duacan_delight.deep_frying_pan.heated"
                    : "block.duacan_delight.deep_frying_pan.not_heated";
            gui.renderTooltip(this.font, Component.translatable(key), mouseX, mouseY);
        }
    }
}
