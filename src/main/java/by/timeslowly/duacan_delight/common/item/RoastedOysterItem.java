package by.timeslowly.duacan_delight.common.item;

import by.timeslowly.duacan_delight.registry.DDItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RoastedOysterItem extends Item {
	public RoastedOysterItem() {
		super(new Properties()
				.stacksTo(16)
				.rarity(Rarity.UNCOMMON)
				.food(new FoodProperties.Builder()
						.nutrition(6)
						.saturationModifier(0.6f)
						.build())
				.craftRemainder(DDItems.OYSTER_SHELL.get()));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.roasted_minced_garlic_oyster.description_0"));
	}
}
