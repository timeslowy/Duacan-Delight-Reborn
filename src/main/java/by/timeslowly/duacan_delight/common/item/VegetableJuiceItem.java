package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class VegetableJuiceItem extends DrinkableFoodItem {
	public VegetableJuiceItem() {
		super(new Properties()
				.stacksTo(2)
				.rarity(Rarity.UNCOMMON)
				.food(new FoodProperties.Builder()
						.nutrition(5)
						.saturationModifier(0.5f)
						.alwaysEdible()
						.usingConvertsTo(Items.BUCKET)
						.effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 1.0f)
						.build()),
				"item.duacan_delight.vegetable_juice.description_1");
	}
}
