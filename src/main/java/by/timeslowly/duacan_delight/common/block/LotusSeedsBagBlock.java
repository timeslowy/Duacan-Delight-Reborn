
package by.timeslowly.duacan_delight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class LotusSeedsBagBlock extends Block {
	public LotusSeedsBagBlock() {
		super(Properties.of().sound(SoundType.WOOL).strength(1f, 10f));
	}

	@Override
	public int getLightBlock(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos) {
		return 15;
	}
}
