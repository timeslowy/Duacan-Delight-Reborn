package by.timeslowly.duacan_delight.client.event;

import by.timeslowly.duacan_delight.Duacan_delight;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = Duacan_delight.MOD_ID, value = Dist.CLIENT)
public class CuisineTooltipHandler {

    private static final Map<TagKey<Item>, Style> CUISINE_TAGS = new LinkedHashMap<>();

    static {
        registerCuisine("duacan", ChatFormatting.GREEN);
        registerCuisine("fujian", ChatFormatting.GREEN);
        registerCuisine("fujian_drink", ChatFormatting.AQUA);
        registerCuisine("hakkas", ChatFormatting.GREEN);
        registerCuisine("southern_fujian", ChatFormatting.GREEN);
    }

    private static void registerCuisine(String name, ChatFormatting color) {
        CUISINE_TAGS.put(
                TagKey.create(Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(Duacan_delight.MOD_ID, "foods/cuisine/" + name)),
                Style.EMPTY.withColor(color)
        );
    }

    @SubscribeEvent
    public static void onItemTooltip(@NotNull ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.isEmpty()) return;

        // 查找物品所属的菜系标签
        TagKey<Item> matchedTag = null;
        Style cuisineStyle = null;

        for (Map.Entry<TagKey<Item>, Style> entry : CUISINE_TAGS.entrySet()) {
            if (stack.is(entry.getKey())) {
                matchedTag = entry.getKey();
                cuisineStyle = entry.getValue();
                break;
            }
        }

        if (matchedTag == null) return;

        List<Component> tooltip = event.getToolTip();

        // 从标签路径提取菜系名称：foods/cuisine/xxx -> xxx
        String tagPath = matchedTag.location().getPath();
        String cuisineName = tagPath.substring(tagPath.lastIndexOf('/') + 1);

        // 在物品名称后（index 1）插入菜系提示
        Component cuisineText = Component.translatable(
                "cuisine." + Duacan_delight.MOD_ID + "." + cuisineName
        ).withStyle(cuisineStyle);
        tooltip.add(1, cuisineText);
    }
}
