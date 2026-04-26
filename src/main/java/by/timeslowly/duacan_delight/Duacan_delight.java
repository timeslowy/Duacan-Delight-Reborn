package by.timeslowly.duacan_delight;

import by.timeslowly.duacan_delight.common.world.VillageCropModifier;
import by.timeslowly.duacan_delight.registry.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Duacan_delight.MOD_ID)
public class Duacan_delight {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "duacan_delight";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogManager.getLogger("Duacan Delight");

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Duacan_delight(@NotNull IEventBus modEventBus, @NotNull ModContainer modContainer) {
        // Register the commonSetup method for modloading

        DDItems.REGISTRY.register(modEventBus);
        DDBlocks.REGISTRY.register(modEventBus);
        DDCreativeTabs.REGISTRY.register(modEventBus);
        DDFluids.REGISTRY.register(modEventBus);
        DDFluidTypes.REGISTRY.register(modEventBus);
        DDFeatures.REGISTRY.register(modEventBus);
        DDLootModifiers.REGISTRY.register(modEventBus);


        // 注册服务器启动事件，用于向村庄农田注入自定义作物
        NeoForge.EVENT_BUS.addListener(VillageCropModifier::onServerAboutToStart);

        // modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Duacan_delight) to respond directly to events.

    }


}
