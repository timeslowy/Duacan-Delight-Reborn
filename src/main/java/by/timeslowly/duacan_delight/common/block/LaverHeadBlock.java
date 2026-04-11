package by.timeslowly.duacan_delight.common.block;

import by.timeslowly.duacan_delight.registry.DDBlocks;
import by.timeslowly.duacan_delight.registry.DDItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LaverHeadBlock extends GrowingPlantHeadBlock implements LiquidBlockContainer {
	public static final MapCodec<LaverHeadBlock> CODEC = simpleCodec(LaverHeadBlock::new);
	private static final double GROW_PER_TICK_PROBABILITY = 0.20;

	@Override
	public @NotNull MapCodec<LaverHeadBlock> codec() {
		return CODEC;
	}

	public LaverHeadBlock(@NotNull Properties properties) {
		super(properties
				.noCollission()
				.instabreak()
				.sound(SoundType.LILY_PAD),
			Direction.UP,
			Shapes.or(
					box(0, 0, 0, 16, 16, 16)
			),
			true,
			GROW_PER_TICK_PROBABILITY);
	}

	@Override
	public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		return Shapes.block();
	}

	@Override
	public @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public @NotNull VoxelShape getInteractionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
		return Shapes.block();
	}

	@Override
	public boolean skipRendering(@NotNull BlockState state, @NotNull BlockState adjacentBlockState, @NotNull Direction side) {
		return adjacentBlockState.is(this) || super.skipRendering(state, adjacentBlockState, side);
	}

	@Override
	protected @NotNull GrowingPlantHeadBlock getHeadBlock() {
		return this;
	}

	@Override
	protected @NotNull Block getBodyBlock() {
		return DDBlocks.LAVER.get();
	}

	@Override
	public @NotNull FluidState getFluidState(@NotNull BlockState state) {
		return Fluids.WATER.getSource(false);
	}

	@Override
	public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
		FluidState fluidState = level.getFluidState(pos);
		if (!fluidState.isSource() || !fluidState.is(Fluids.WATER)) {
			return false;
		}
		BlockPos below = pos.below();
		BlockState belowState = level.getBlockState(below);
		return belowState.is(Blocks.SAND) ||
			   belowState.is(Blocks.GRAVEL) ||
			   belowState.is(Blocks.CLAY) ||
			   belowState.is(Blocks.DIRT) ||
			   belowState.is(DDBlocks.LAVER.get()) ||
			   belowState.is(DDBlocks.LAVER_HEAD.get());
	}

	@Override
	public boolean canGrowInto(@NotNull BlockState state) {
		// 只允许替换水
		return state.is(Blocks.WATER);
	}

	@Override
	public int getBlocksToGrowWhenBonemealed(@NotNull RandomSource random) {
		return 1;
	}

	@Override
	public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
		// 使用父类的生长逻辑（将头部替换为身体，并在上方生成新头部）
		super.performBonemeal(level, random, pos, state);
	}
	@Override
	public @NotNull ItemStack getCloneItemStack(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state) {
		return new ItemStack(DDItems.LAVER.get());
	}

	@Override
	public boolean canPlaceLiquid(@Nullable Player player, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull Fluid fluid) {
		return false;
	}

	@Override
	public boolean placeLiquid(@NotNull LevelAccessor levelAccessor, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull FluidState fluidState) {
		return false;
	}
}
