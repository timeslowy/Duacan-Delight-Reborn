
package by.timeslowly.duacan_delight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ChineseWolfberryLeavesBlock extends LeavesBlock {
	public ChineseWolfberryLeavesBlock() {
		super(Properties.of().ignitedByLava().sound(SoundType.AZALEA_LEAVES).strength(1f, 10f).noOcclusion().randomTicks());
	}

	@Override
	public int getLightBlock(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos) {
		return 1;
	}

	@Override
	public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull Direction face) {
		return 10;
	}

	@Override
	public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull Direction face) {
		return 20;
	}
}
