package by.timeslowly.duacan_delight;

import by.timeslowly.duacan_delight.registry.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod(Duacan_delight.MOD_ID)
public class Duacan_delight {
    public static final String MOD_ID = "duacan_delight";
    public static final Logger LOGGER = LogManager.getLogger("Duacan Delight");

    public Duacan_delight(@NotNull IEventBus modEventBus) {
        // 所有注册均通过 DeferredRegister，在 mod 事件总线上排队，待适当的注册事件触发时批量处理
        DDItems.REGISTRY.register(modEventBus);
        DDBlocks.REGISTRY.register(modEventBus);
        DDCreativeTabs.REGISTRY.register(modEventBus);
        DDFluids.REGISTRY.register(modEventBus);
        DDFluidTypes.REGISTRY.register(modEventBus);
        DDFeatures.REGISTRY.register(modEventBus);
        DDLootModifiers.REGISTRY.register(modEventBus);
    }

}
