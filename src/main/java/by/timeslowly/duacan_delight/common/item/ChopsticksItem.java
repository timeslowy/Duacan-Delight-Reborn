
package by.timeslowly.duacan_delight.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;

public class ChopsticksItem extends Item {
	public ChopsticksItem() {
		super(new Properties().durability(60).rarity(Rarity.COMMON));
	}

	@Override
	public boolean hasCraftingRemainingItem(@NotNull ItemStack stack) {
		return true;
	}

	@Override
	public @NotNull ItemStack getCraftingRemainingItem(@NotNull ItemStack itemstack) {
		ItemStack retval = new ItemStack(this);
		retval.setDamageValue(itemstack.getDamageValue() + 1);
		if (retval.getDamageValue() >= retval.getMaxDamage()) {
			return ItemStack.EMPTY;
		}
		return retval;
	}

	@Override
	public boolean isRepairable(@NotNull ItemStack itemstack) {
		return false;
	}
}
