package by.timeslowly.duacan_delight.worldgen.feature;

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
    // 紫菜只长在能采到的浅海：水深 3~8 格。
    // waterDepth = 海平面 - 海床方块 y，海床用 OCEAN_FLOOR 高度图取到的是“海床上方第一格”，
    // 因此海床方块本身是 floorY - 1。
    private static final int MIN_WATER_DEPTH = 3;
    private static final int MAX_WATER_DEPTH = 8;
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

        // 使用维度真实海平面，而不是硬编码 62/63；不同维度或数据包可改此值。
        int seaLevel = context.chunkGenerator().getSeaLevel();

        // OCEAN_FLOOR 返回海床上方第一格（即水柱最底部那一格的 y）。
        int floorY = level.getHeight(Heightmap.Types.OCEAN_FLOOR, origin.getX(), origin.getZ());
        int waterDepth = seaLevel - floorY;
        if (waterDepth < MIN_WATER_DEPTH || waterDepth > MAX_WATER_DEPTH) {
            return false;
        }

        BlockPos pos = new BlockPos(origin.getX(), floorY, origin.getZ());

        if (!level.getFluidState(pos).isSource() || !level.getFluidState(pos).is(Fluids.WATER)) {
            return false;
        }

        BlockState headState = DDBlocks.LAVER_HEAD.get().defaultBlockState();
        BlockState bodyState = DDBlocks.LAVER.get().defaultBlockState();

        // 不要长出海面：可用高度受水深限制
        int height = 1 + random.nextInt(Math.max(1, Math.min(MAX_LAVER_HEIGHT, waterDepth)));

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