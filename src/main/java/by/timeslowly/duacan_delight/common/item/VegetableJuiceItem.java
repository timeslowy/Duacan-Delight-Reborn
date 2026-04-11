package by.timeslowly.duacan_delight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VegetableJuiceItem extends Item {
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
						.build()));
	}

	@Override
	public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.vegetable_juice.description_0"));
		list.add(Component.translatable("item.duacan_delight.vegetable_juice.description_1"));
		list.add(Component.translatable("item.duacan_delight.vegetable_juice.description_2"));
	}
}
