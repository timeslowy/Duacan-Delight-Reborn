package by.timeslowly.duacan_delight.common.item;

import by.timeslowly.duacan_delight.registry.DDItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IronCanItem extends Item {
	public IronCanItem() {
		super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Item.@NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.duacan_delight.iron_can.description_0"));
	}

	@Override
	public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player player, @NotNull InteractionHand hand) {
		ItemStack heldItem = player.getItemInHand(hand);
		// 只由服务端产生副作用：客户端仅返回成功让动画播放，
		// 否则客户端的预测性 shrink/发放会在服务端同步时造成物品回滚或幽灵物品。
		if (world.isClientSide()) {
			return InteractionResultHolder.success(heldItem);
		}

		// 创造模式不消耗铁罐，但仍返回产物，保持和开罐动画一致的反馈
		if (!player.getAbilities().instabuild) {
			heldItem.shrink(1);
		}

		// giveItemToPlayer 在背包放不下时会自动把剩余部分掉落在玩家脚下，不会丢失物品
		ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(DDItems.OPENED_IRON_CAN.get()));
		ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.IRON_NUGGET));
		return InteractionResultHolder.success(heldItem);
	}
}
