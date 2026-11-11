package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.DuacanDelight;
import by.timeslowly.duacan_delight.common.block.entity.container.DeepFryingPanMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * 容器菜单类型注册。目前只有炸锅。
 */
public class DDMenuTypes {
    public static final DeferredRegister<MenuType<?>> REGISTRY =
            DeferredRegister.create(Registries.MENU, DuacanDelight.MOD_ID);

    public static final Supplier<MenuType<DeepFryingPanMenu>> DEEP_FRYING_PAN =
            REGISTRY.register("deep_frying_pan", () -> IMenuTypeExtension.create(DeepFryingPanMenu::new));
}
