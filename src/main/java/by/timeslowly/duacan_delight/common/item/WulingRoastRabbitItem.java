package by.timeslowly.duacan_delight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WulingRoastRabbitItem extends Item {
	public WulingRoastRabbitItem() {
		super(new Properties()
				.stacksTo(64)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(16)
						.saturationModifier(0.8f)
						.effect(() -> new MobEffectInstance(MobEffects.JUMP, 1200, 1), 1.0f)
						.build()));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.wuling_roast_rabbit.description_0"));
		list.add(Component.translatable("item.duacan_delight.wuling_roast_rabbit.description_1"));
		list.add(Component.translatable("item.duacan_delight.wuling_roast_rabbit.description_2"));
	}
}
