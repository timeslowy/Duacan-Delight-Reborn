package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class ZhujiaoganfanItem extends TooltipFoodItem {
	public ZhujiaoganfanItem() {
		super(new Properties()
				.stacksTo(16)
				.rarity(Rarity.UNCOMMON)
				.food(new FoodProperties.Builder()
						.nutrition(20)
						.saturationModifier(0.5f)
						.usingConvertsTo(Items.BOWL)
						.effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600, 0), 1.0f)
						.build()),
				96,
				"item.duacan_delight.zhujioaganfan.description_1");
	}
}