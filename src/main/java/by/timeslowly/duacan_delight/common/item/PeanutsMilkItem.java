package by.timeslowly.duacan_delight.common.item;

import by.timeslowly.duacan_delight.registry.DDItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PeanutsMilkItem extends Item {
	public PeanutsMilkItem() {
		super(new Properties()
				.stacksTo(16)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(6)
						.saturationModifier(0.8f)
						.usingConvertsTo(DDItems.OPENED_IRON_CAN.get())
						.effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 600, 0), 1.0f)
						.build()));
	}

	@Override
	public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.peanuts_milk.description_0"));
		list.add(Component.translatable("item.duacan_delight.peanuts_milk.description_1"));
		list.add(Component.translatable("item.duacan_delight.peanuts_milk.description_2"));
	}
}
