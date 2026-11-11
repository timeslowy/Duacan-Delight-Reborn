package by.timeslowly.duacan_delight.client.event;

import by.timeslowly.duacan_delight.DuacanDelight;
import by.timeslowly.duacan_delight.client.gui.DeepFryingPanScreen;
import by.timeslowly.duacan_delight.registry.DDMenuTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

/**
 * 客户端界面注册。
 * <p>
 * 与 {@code FluidClientSetupEvents} 一样放在 client 包下并用 {@link Dist#CLIENT} 限制，
 * 保证专用服务器不会加载任何客户端类（{@code AbstractContainerScreen} 等）。
 */
@EventBusSubscriber(modid = DuacanDelight.MOD_ID, value = Dist.CLIENT)
public class MenuScreenEvents {

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(DDMenuTypes.DEEP_FRYING_PAN.get(), DeepFryingPanScreen::new);
    }
}
