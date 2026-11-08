package by.timeslowly.duacan_delight.common.block;

import by.timeslowly.duacan_delight.registry.DDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.NotNull;

/**
 * 银耳孢子长成的作物。
 * <p>
 * 与常规作物不同，它只附着在原木上、且在弱光下才生长，因此单独覆盖
 * {@link #mayPlaceOn} 与 {@link #randomTick}，其余年龄/形状/种子逻辑复用
 * {@link AgeCropBlock}。
 */
public class TremellaCropBlock extends AgeCropBlock {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_1;
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
			Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0),
			Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};

	public TremellaCropBlock() {
		// dirtBase = false：基底由下方覆盖的 mayPlaceOn 改为 LOGS
		super(1, SHAPE_BY_AGE, DDItems.TREMELLA_SPORE, false, SoundType.GRASS);
	}

	@Override
	protected @NotNull IntegerProperty getAgeProperty() {
		return AGE;
	}

	@Override
	public boolean mayPlaceOn(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
		return state.is(BlockTags.LOGS);
	}

	@Override
	public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
		return mayPlaceOn(level.getBlockState(pos.below()), level, pos.below());
	}

	@Override
	public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		if (!level.isAreaLoaded(pos, 1)) return;
		// 低光才生长：天光与方块光都要低于 8
		if (level.getBrightness(LightLayer.SKY, pos) < 8 && level.getBrightness(LightLayer.BLOCK, pos) < 8) {
			int i = this.getAge(state);
			if (i < this.getMaxAge()) {
				float f = getGrowthSpeed(state, level, pos);
				if (CommonHooks.canCropGrow(level, pos, state, random.nextInt((int)(25.0F / f) + 1) == 0)) {
					level.setBlock(pos, this.getStateForAge(i + 1), 2);
					CommonHooks.fireCropGrowPost(level, pos, state);
				}
			}
		}
	}
}
