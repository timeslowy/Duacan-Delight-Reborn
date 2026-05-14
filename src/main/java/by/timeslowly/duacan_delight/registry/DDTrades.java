package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.Duacan_delight;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = Duacan_delight.MOD_ID)
public class DDTrades {
    @SubscribeEvent
    public static void registerTrades(@NotNull VillagerTradesEvent event) {
        var profession = event.getType();
        if (profession == VillagerProfession.BUTCHER) {
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 10), new ItemStack(DDItems.ZHUJIAO.get(), 3), 20, 5, 0.05f));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 2), new ItemStack(DDItems.PIG_LARGE_INTESTINE.get(), 3), 10, 5, 0.05f));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 3), new ItemStack(DDItems.PIG_SMALL_INTESTINE.get(), 5), 10, 5, 0.05f));
        } else if (profession == VillagerProfession.FARMER) {
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(DDItems.BAMBOO_SHOOT.get(), 5), 20, 5, 0.05f));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 3), new ItemStack(DDItems.YIMI.get(), 9), 10, 5, 0.05f));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 3), new ItemStack(DDItems.LOTUS_SEEDS.get(), 10), 10, 5, 0.05f));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(DDItems.TREMELLA.get(), 3), new ItemStack(Items.EMERALD, 12), 10, 5, 0.05f));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 2), new ItemStack(DDItems.ADAZI.get(), 10), 10, 5, 0.05f));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(DDBlocks.GAICAI_CROP.get(), 5), new ItemStack(Items.EMERALD, 6), 21, 5, 0.05f));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(DDItems.GAICAI.get(), 26), new ItemStack(Items.EMERALD, 3), 16, 5, 0.05f));
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Items.EMERALD, 3), new ItemStack(DDItems.MIFEN.get(), 12), 10, 5, 0.05f));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.EMERALD, 5), new ItemStack(DDItems.CHINESE_ANGELICA.get(), 5), 10, 5, 0.05f));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.EMERALD, 4), new ItemStack(DDItems.CHINESE_WOLFBERRY.get(), 6), 18, 5, 0.05f));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(DDItems.KUCAI.get(), 23), new ItemStack(Items.EMERALD, 3), 16, 5, 0.05f));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 4), new ItemStack(DDItems.CASSIA_SEED.get(), 8), 10, 5, 0.05f));
        } else if (profession == VillagerProfession.FISHERMAN) {
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Items.EMERALD, 3), new ItemStack(DDItems.OYSTER.get(), 7), 15, 5, 0.05f));
        }
    }
}
