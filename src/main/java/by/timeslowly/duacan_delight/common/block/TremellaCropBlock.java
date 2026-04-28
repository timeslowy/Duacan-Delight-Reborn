package by.timeslowly.duacan_delight.common.block;

import by.timeslowly.duacan_delight.registry.DDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.NotNull;

public class TremellaCropBlock extends CropBlock {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_1;
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
			Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0),
			Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};

	public TremellaCropBlock() {
		super(Properties.of()
				.noCollission()
				.randomTicks()
				.instabreak()
				.sound(SoundType.GRASS));
	}

	@Override
	protected @NotNull IntegerProperty getAgeProperty() {
		return AGE;
	}

	@Override
	public int getMaxAge() {
		return 1;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	@Override
	public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
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

	@Override
	protected @NotNull ItemLike getBaseSeedId() {
		return DDItems.TREMELLA_SPORE.get();
	}
}
