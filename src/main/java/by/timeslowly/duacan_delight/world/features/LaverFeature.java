package by.timeslowly.duacan_delight.world.features;

import by.timeslowly.duacan_delight.registry.DDBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

public class LaverFeature extends Feature<NoneFeatureConfiguration> {
    private static final int SEA_LEVEL = 62;
    private static final int MIN_DEPTH_BELOW_SURFACE = 8;
    private static final int MAX_LAVER_HEIGHT = 5;
    private static final int MAX_GROWTH_AGE = 25;

    public LaverFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        int floorY = level.getHeight(Heightmap.Types.OCEAN_FLOOR, origin.getX(), origin.getZ());
        if (floorY < SEA_LEVEL - MIN_DEPTH_BELOW_SURFACE) {
            return false;
        }
        BlockPos pos = new BlockPos(origin.getX(), floorY, origin.getZ());

        if (!level.getFluidState(pos).isSource() || !level.getFluidState(pos).is(Fluids.WATER)) {
            return false;
        }

        BlockState headState = DDBlocks.LAVER_HEAD.get().defaultBlockState();
        BlockState bodyState = DDBlocks.LAVER.get().defaultBlockState();

        int height = 1 + random.nextInt(MAX_LAVER_HEIGHT);

        for (int i = 0; i <= height; i++) {
            if (level.getFluidState(pos).is(Fluids.WATER)
                    && bodyState.canSurvive(level, pos)) {
                if (i == height) {
                    level.setBlock(pos, headState.setValue(GrowingPlantHeadBlock.AGE, random.nextInt(MAX_GROWTH_AGE)), 2);
                    return true;
                } else {
                    level.setBlock(pos, bodyState, 2);
                }
            } else if (i > 0) {
                BlockPos below = pos.below();
                BlockState belowState = level.getBlockState(below);
                if (headState.canSurvive(level, below)
                        && !belowState.is(DDBlocks.LAVER_HEAD.get())
                        && !belowState.is(DDBlocks.LAVER.get())) {
                    level.setBlock(below, headState.setValue(GrowingPlantHeadBlock.AGE, random.nextInt(MAX_GROWTH_AGE)), 2);
                    return true;
                }
                return false;
            } else {
                return false;
            }
            pos = pos.above();
        }

        return false;
    }
}
