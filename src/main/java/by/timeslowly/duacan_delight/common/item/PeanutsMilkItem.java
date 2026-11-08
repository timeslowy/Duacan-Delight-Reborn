package by.timeslowly.duacan_delight.common.item;

import by.timeslowly.duacan_delight.registry.DDItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Rarity;

public class PeanutsMilkItem extends DrinkableFoodItem {
	public PeanutsMilkItem() {
		super(new Properties()
				.stacksTo(16)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(6)
						.saturationModifier(0.8f)
						.usingConvertsTo(DDItems.OPENED_IRON_CAN.get())
						.effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 600, 0), 1.0f)
						.build()),
				"item.duacan_delight.peanuts_milk.description_1");
	}
}
