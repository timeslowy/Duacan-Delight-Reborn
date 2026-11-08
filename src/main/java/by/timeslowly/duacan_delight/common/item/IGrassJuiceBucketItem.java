package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class IGrassJuiceBucketItem extends DrinkableFoodItem {
	public IGrassJuiceBucketItem() {
		super(new Properties()
				.stacksTo(1)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(1)
						.saturationModifier(0f)
						.usingConvertsTo(Items.BUCKET)
						.effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 1.0f)
						.alwaysEdible()
						.build())
				.craftRemainder(Items.BUCKET),
				"item.duacan_delight.i_grass_juice_bucket.description_0");
	}
}
