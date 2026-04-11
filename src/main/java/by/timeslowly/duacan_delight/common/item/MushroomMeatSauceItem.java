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

public class MushroomMeatSauceItem extends Item {
	public MushroomMeatSauceItem() {
		super(new Properties()
				.stacksTo(16)
				.rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder()
						.nutrition(10)
						.saturationModifier(0.6f)
						.build())
				.craftRemainder(DDItems.OPENED_IRON_CAN.get()));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.mushroom_meat_sauce.description_0"));
		list.add(Component.translatable("item.duacan_delight.mushroom_meat_sauce.description_1"));
	}
}
