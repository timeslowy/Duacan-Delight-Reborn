package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.DuacanDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DDCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DuacanDelight.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DuaCanDelight = REGISTRY.register("duacan_delight",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.duacan_delight.duacan_delight"))
                    .icon(() -> new ItemStack(DDItems.GODONGGUI.get()))
                    // 条目与顺序全部集中在 DDCreativeTabOrder，这里只负责按清单填充。
                    .displayItems((parameters, tabData) -> DDCreativeTabOrder.acceptAll(tabData::accept))
                    .build());
}
