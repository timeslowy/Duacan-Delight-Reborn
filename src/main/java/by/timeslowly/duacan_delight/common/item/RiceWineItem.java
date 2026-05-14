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
import java.util.Objects;

public class RiceWineItem extends Item {
	public RiceWineItem() {
		super(new Properties()
				.stacksTo(64)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(1)
						.saturationModifier(1f)
						.alwaysEdible()
						.build())
				.craftRemainder(Items.GLASS_BOTTLE));
	}

	@Override
	public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.rice_wine.description_0"));
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull LivingEntity entity) {
		if (!world.isClientSide()) {
			if (entity.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
				int currentAmplifier = Objects.requireNonNull(entity.getEffect(MobEffects.MOVEMENT_SLOWDOWN)).getAmplifier();
				entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, currentAmplifier + 1));
			} else {
				entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 0));
			}
		}
		return super.finishUsingItem(itemstack, world, entity);
	}
}
