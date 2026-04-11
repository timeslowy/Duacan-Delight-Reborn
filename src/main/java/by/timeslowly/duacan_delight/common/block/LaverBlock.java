package by.timeslowly.duacan_delight.common.block;

import by.timeslowly.duacan_delight.registry.DDBlocks;
import by.timeslowly.duacan_delight.registry.DDItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

public class LaverBlock extends GrowingPlantBodyBlock implements LiquidBlockContainer {
	public static final MapCodec<LaverBlock> CODEC = simpleCodec(LaverBlock::new);

	@Override
	public @NotNull MapCodec<LaverBlock> codec() {
		return CODEC;
	}

	public LaverBlock(@NotNull Properties properties) {
		super(properties
				.noCollission()
				.instabreak()
				.sound(SoundType.LILY_PAD),
			Direction.UP,
			Shapes.or(
				box(0, 0, 0, 16, 16, 16)
			),
			true);
	}

	@Override
	public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		// 提供一个完整方块轮廓以供选中
		return Shapes.block();
	}

	@Override
	public @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		// 碰撞箱保持为空，实体可以穿过
		return Shapes.empty();
	}

	// 如果已经定义了 getInteractionShape 且希望保持交互区域为完整方块，可以保留，但并非必须
	@Override
	public @NotNull VoxelShape getInteractionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
		return Shapes.block();
	}

	@Override
	public boolean skipRendering(@NotNull BlockState state, @NotNull BlockState adjacentBlockState, @NotNull net.minecraft.core.Direction side) {
		return adjacentBlockState.is(this) || super.skipRendering(state, adjacentBlockState, side);
	}

	@Override
	protected @NotNull GrowingPlantHeadBlock getHeadBlock() {
		return (GrowingPlantHeadBlock) DDBlocks.LAVER_HEAD.get();
	}

	@Override
	public @NotNull FluidState getFluidState(@NotNull BlockState state) {
		return Fluids.WATER.getSource(false);
	}

	@Override
	public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
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
