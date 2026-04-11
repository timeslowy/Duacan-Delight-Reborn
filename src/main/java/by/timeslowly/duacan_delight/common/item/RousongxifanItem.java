package by.timeslowly.duacan_delight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RousongxifanItem extends Item {
	public RousongxifanItem() {
		super(new Properties()
				.stacksTo(16)
				.rarity(Rarity.UNCOMMON)
				.food(new FoodProperties.Builder()
						.nutrition(8)
						.saturationModifier(0.8f)
						.usingConvertsTo(Items.BOWL)
						.build()));
	}

	@Override
	public int getUseDuration(@NotNull ItemStack itemstack, @NotNull LivingEntity livingEntity) {
		return 22;
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.rousongxifan.description_0"));
	}
}
