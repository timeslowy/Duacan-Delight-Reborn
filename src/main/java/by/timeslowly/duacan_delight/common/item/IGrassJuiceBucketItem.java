package by.timeslowly.duacan_delight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IGrassJuiceBucketItem extends Item {
	public IGrassJuiceBucketItem() {
		super(new Properties()
				.stacksTo(1)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(1)
						.saturationModifier(0f)
						.usingConvertsTo(Items.BUCKET)
						.alwaysEdible()
						.build())
				.craftRemainder(Items.BUCKET));
	}

	@Override
	public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.i_grass_juice_bucket.description_0"));
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull LivingEntity entity) {
		super.finishUsingItem(itemstack, world, entity);
		// Apply effect: Nausea for 30 seconds if not already has Absorption
		if (!(entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(MobEffects.ABSORPTION))) {
			if (entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide()) {
				livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 600, 0));
			}
		}
        return itemstack;
    }
}
