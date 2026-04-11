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

public class DriedSuancaiItem extends Item {
	public DriedSuancaiItem() {
		super(new Properties()
				.stacksTo(64)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(2)
						.saturationModifier(0.5f)
						.effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 1.0f)
						.build()));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.dried_suancai.description_0"));
	}
}
