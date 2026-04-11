package by.timeslowly.duacan_delight.common.item;

import by.timeslowly.duacan_delight.registry.DDBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class YinerbaoziItem extends Item {
	public YinerbaoziItem() {
		super(new Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.tremella_spore.description_0"));
	}

	@Override
	public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockPos above = pos.above();

		if (!level.getBlockState(pos).is(BlockTags.LOGS)) {
			if (context.getPlayer() != null && !level.isClientSide())
				context.getPlayer().displayClientMessage(Component.translatable("message.duacan_delight.invalid_plant_block"), true);
			return InteractionResult.FAIL;
		}

		if (level.getBrightness(LightLayer.BLOCK, above) > 8 || level.getBrightness(LightLayer.SKY, above) >= 8) {
			if (context.getPlayer() != null && !level.isClientSide())
				context.getPlayer().displayClientMessage(Component.translatable("message.duacan_delight.light_too_strong"), true);
			return InteractionResult.FAIL;
		}

		if (!level.getBlockState(above).is(Blocks.AIR)) {
			return InteractionResult.FAIL;
		}

		if (!level.isClientSide()) {
			level.setBlock(above, DDBlocks.TREMELLA_CROP.get().defaultBlockState(), 3);
            if (context.getPlayer() != null) {
                context.getPlayer().getInventory().clearOrCountMatchingItems(
                        p -> p.getItem() == this, 1, context.getPlayer().inventoryMenu.getCraftSlots());
            }
        }
		return InteractionResult.SUCCESS;
	}
}
