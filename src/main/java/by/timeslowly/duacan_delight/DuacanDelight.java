package by.timeslowly.duacan_delight;

import by.timeslowly.duacan_delight.registry.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * 模组主入口。
 * <p>
 * 注意：类名已按 Java 规范改为 PascalCase，但 {@link #MOD_ID} 仍为 {@code duacan_delight}，
 * 这是所有注册 ID、资源路径与存档引用的基础，不可改动。
 */
@Mod(DuacanDelight.MOD_ID)
public class DuacanDelight {
    public static final String MOD_ID = "duacan_delight";
    public static final Logger LOGGER = LogManager.getLogger("Duacan Delight");

    public DuacanDelight(@NotNull IEventBus modEventBus) {
        // 所有注册均通过 DeferredRegister，在 mod 事件总线上排队，待适当的注册事件触发时批量处理
        DDItems.REGISTRY.register(modEventBus);
        DDBlocks.REGISTRY.register(modEventBus);
        DDBlockEntityTypes.REGISTRY.register(modEventBus);
        DDCreativeTabs.REGISTRY.register(modEventBus);
        DDFluids.REGISTRY.register(modEventBus);
        DDFluidTypes.REGISTRY.register(modEventBus);
        DDFeatures.REGISTRY.register(modEventBus);
        DDLootModifiers.REGISTRY.register(modEventBus);
        DDLootFunctions.REGISTRY.register(modEventBus);
        DDRecipeTypes.REGISTRY.register(modEventBus);
        DDRecipeSerializers.REGISTRY.register(modEventBus);
        DDMenuTypes.REGISTRY.register(modEventBus);
    }

}