package by.timeslowly.duacan_delight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * 参数化作物基类。
 * <p>
 * 统一四类作物重复的「年龄属性 / 形状数组 / 种子返回 / 基底判定 / 默认作物音效」实现。
 * <p>
 * <b>注意</b>：{@link CropBlock} 的构造器会在 {@code super(...)} 执行期间回调
 * {@link #getAgeProperty()}（用于建立 defaultState），因此年龄属性<b>不能</b>依赖
 * {@code super(...)} 之后才赋值的实例字段。这里改用由子类实现的抽象方法即时返回，
 * 保持与重构前「每个类用自己的 static AGE 常量」完全一致的加载时序。
 */
public abstract class AgeCropBlock extends CropBlock {
    private final int maxAge;
    private final VoxelShape[] shapeByAge;
    private final Supplier<? extends ItemLike> seedSupplier;
    private final boolean dirtBase;

    /**
     * @param maxAge       成熟年龄
     * @param shapeByAge   按年龄索引的形状数组，长度必须为 maxAge + 1
     * @param seedSupplier 种子/种植物品，用于 {@code getBaseSeedId}
     * @param dirtBase     true = 只能种在 {@code #minecraft:dirt} 上；false = 沿用原版耕地判定
     */
    protected AgeCropBlock(int maxAge, VoxelShape[] shapeByAge,
                           Supplier<? extends ItemLike> seedSupplier, boolean dirtBase) {
        this(maxAge, shapeByAge, seedSupplier, dirtBase, SoundType.CROP);
    }

    protected AgeCropBlock(int maxAge, VoxelShape[] shapeByAge,
                           Supplier<? extends ItemLike> seedSupplier, boolean dirtBase, SoundType soundType) {
        super(Properties.of()
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(soundType));
        if (shapeByAge.length != maxAge + 1) {
            throw new IllegalArgumentException(
                    "shapeByAge length must be maxAge + 1, got " + shapeByAge.length + " for maxAge " + maxAge);
        }
        this.maxAge = maxAge;
        this.shapeByAge = shapeByAge;
        this.seedSupplier = seedSupplier;
        this.dirtBase = dirtBase;
    }

    /**
     * 子类通过它提供年龄属性。
     * <p>
     * 必须是「即时返回常量」的实现（典型写法：{@code return AGE;} 指向自己的
     * {@code public static final IntegerProperty}），因为父类构造器会调用它。
     */
    @Override
    protected abstract @NotNull IntegerProperty getAgeProperty();

    @Override
    public int getMaxAge() {
        return this.maxAge;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        builder.add(this.getAgeProperty());
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return this.shapeByAge[this.getAge(state)];
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return this.seedSupplier.get();
    }

    @Override
    public boolean mayPlaceOn(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return this.dirtBase
                ? state.is(BlockTags.DIRT)
                : super.mayPlaceOn(state, level, pos);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        return this.mayPlaceOn(level.getBlockState(pos.below()), level, pos.below());
    }
}
