
package by.timeslowly.duacan_delight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ChineseWolfberryTreesaplingBlock extends SaplingBlock {
	public static final TreeGrower TREE_GROWER = new TreeGrower("chinese_wolfberry_treesapling", Optional.empty(), Optional.of(getFeatureKey()), Optional.empty());

	public ChineseWolfberryTreesaplingBlock() {
		super(TREE_GROWER, Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.GRASS).instabreak().noCollission().offsetType(OffsetType.NONE).pushReaction(PushReaction.DESTROY));
	}

	@Override
	public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull Direction face) {
		return 100;
	}

	@Override
	public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull Direction face) {
		return 60;
	}

	private static @NotNull ResourceKey<ConfiguredFeature<?, ?>> getFeatureKey() {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.parse("duacan_delight:chinese_wolfberry_tree"));
	}
}
