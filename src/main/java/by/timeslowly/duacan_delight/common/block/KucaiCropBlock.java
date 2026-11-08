package by.timeslowly.duacan_delight.common.block;

import by.timeslowly.duacan_delight.registry.DDItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class KucaiCropBlock extends AgeCropBlock {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
			Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
			Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
			Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0)};

	public KucaiCropBlock() {
		// 苦菜只能种在泥土/草方块上（dirtBase = true）
		super(2, SHAPE_BY_AGE, DDItems.KUCAI_SEEDS, true);
	}

	@Override
	protected @NotNull IntegerProperty getAgeProperty() {
		return AGE;
	}
}
