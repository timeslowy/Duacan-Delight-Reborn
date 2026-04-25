package by.timeslowly.duacan_delight.client.event;

import by.timeslowly.duacan_delight.Duacan_delight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Duacan_delight.MOD_ID, value = Dist.CLIENT)
public class FoodEffectTooltipHandler {

    // 原版用于高级提示信息的已知翻译键（仅当高级提示框开启时出现）
    private static final Set<String> ADVANCED_TOOLTIP_KEYS = Set.of(
            "item.durability",
            "item.unbreakable",
            "item.can_break",
            "item.can_place",
            "item.dyed",
            "item.trim",
            "item.nbt_tags",
            "item.disabled",
            "item.remaining_durability"
    );

    @SubscribeEvent
    public static void onItemTooltip(@NotNull ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.isEmpty()) return;

        // 仅处理本模组的物品
        if (!BuiltInRegistries.ITEM.getKey(stack.getItem()).getNamespace().equals(Duacan_delight.MOD_ID)) {
            return;
        }

        Player player = event.getEntity();
        FoodProperties foodProperties = stack.getFoodProperties(player);
        if (foodProperties == null) return;

        List<FoodProperties.PossibleEffect> effects = foodProperties.effects();
        if (effects.isEmpty()) return;

        List<Component> tooltip = event.getToolTip();
        float tickRate = player == null ? 20.0f : player.level().tickRateManager().tickrate();

        // 收集所有要插入的效果文本
        List<Component> effectLines = new java.util.ArrayList<>();
        for (FoodProperties.PossibleEffect possibleEffect : effects) {
            MobEffectInstance effectInstance = possibleEffect.effect();
            MutableComponent effectText = Component.translatable(effectInstance.getDescriptionId());
            if (effectInstance.getDuration() > 20) {
                effectText = Component.translatable("potion.withDuration",
                        effectText,
                        MobEffectUtil.formatDuration(effectInstance, 1.0f, tickRate));
            }
            effectLines.add(effectText.withStyle(
                    effectInstance.getEffect().value().getCategory().getTooltipFormatting()
            ));
        }

        // 寻找插入点：在第一个高级提示信息行之前（若没有则追加到末尾）
        int insertIndex = tooltip.size(); // 默认末尾
        for (int i = 1; i < tooltip.size(); i++) {
            Component line = tooltip.get(i);
            if (line.getContents() instanceof TranslatableContents translatable) {
                String key = translatable.getKey();
                // 检查是否为已知高级提示键，或修饰符类（item.modifiers.0 等）
                if (ADVANCED_TOOLTIP_KEYS.contains(key) || key.startsWith("item.modifiers.")) {
                    insertIndex = i;
                    break;
                }
            }
        }

        tooltip.addAll(insertIndex, effectLines);
    }
}