package by.timeslowly.duacan_delight.common.block;

import by.timeslowly.duacan_delight.common.block.entity.DeepFryingPanBlockEntity;
import by.timeslowly.duacan_delight.registry.DDBlockEntityTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;

/**
 * 炸锅方块。
 * <p>
 * 最初移植自随意乐事（Casualness Delight，作者 Ginko）的 {@code DeepFryingPanBlock}；
 * 现已改为带容器界面的工作方块：右键打开界面，原料/容具/成品都在界面里操作。
 * <p>
 * 与随意乐事原版的差异：
 * <ul>
 *     <li>右键不再直接放入或取出物品，而是打开 {@code DeepFryingPanMenu}。</li>
 *     <li>实现了 {@link SimpleWaterloggedBlock}，含水状态真正生效（原版声明了属性却没实现接口）。</li>
 *     <li>加热标签直接用农夫乐事 {@code ModTags.Blocks} 的静态字段。</li>
 * </ul>
 */
public class DeepFryingPanBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<DeepFryingPanBlock> CODEC = simpleCodec(DeepFryingPanBlock::new);

    public static final int MINIMUM_COOKING_TIME = 60;

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty SUPPORT = BooleanProperty.create("support");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D);
    protected static final VoxelShape SHAPE_WITH_TRAY = Shapes.or(SHAPE, Block.box(0.0D, -1.0D, 0.0D, 16.0D, 0.0D, 16.0D));

    public DeepFryingPanBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(SUPPORT, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    /**
     * 右键打开炸锅界面。
     * <p>
     * 手持锅铲类物品时不拦截：把它们交给默认逻辑，方便以后接入工具交互。
     */
    @Override
    public @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level,
                                                   @NotNull BlockPos pos, @NotNull Player player,
                                                   @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (!(level.getBlockEntity(pos) instanceof DeepFryingPanBlockEntity deepFryingPan)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        if (!level.isClientSide) {
            player.openMenu(deepFryingPan, pos);
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    /**
     * 方块被破坏时把三格内容（含尚未装容具的成品）全部掉落，物品不会丢。
     */
    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                         @NotNull BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (level.getBlockEntity(pos) instanceof DeepFryingPanBlockEntity deepFryingPan) {
                for (ItemStack stack : deepFryingPan.getDroppableInventory()) {
                    if (!stack.isEmpty()) {
                        net.minecraft.world.Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), stack.copy());
                    }
                }
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return state.getValue(SUPPORT) ? SHAPE_WITH_TRAY : SHAPE;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        Level level = context.getLevel();
        FluidState fluid = level.getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection())
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
                .setValue(SUPPORT, getTrayState(level, context.getClickedPos()));
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull Direction facing, @NotNull BlockState facingState,
                                          @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (facing.getAxis().equals(Direction.Axis.Y)) {
            return state.setValue(SUPPORT, getTrayState(level, currentPos));
        }
        return state;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state) {
        if (level.getBlockEntity(pos) instanceof DeepFryingPanBlockEntity deepFryingPan) {
            ItemStack panStack = deepFryingPan.getDeepFryingPanAsItem();
            if (!panStack.isEmpty()) {
                return panStack;
            }
        }
        return super.getCloneItemStack(level, pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SUPPORT, WATERLOGGED);
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (level.getBlockEntity(pos) instanceof DeepFryingPanBlockEntity deepFryingPan && deepFryingPan.isCooking()) {
            double x = (double) pos.getX() + 0.5D;
            double y = pos.getY();
            double z = (double) pos.getZ() + 0.5D;
            if (random.nextInt(10) == 0) {
                level.playLocalSound(x, y, z, ModSounds.BLOCK_SKILLET_SIZZLE.get(), SoundSource.BLOCKS,
                        0.4F, random.nextFloat() * 0.2F + 0.9F, false);
            }
        }
    }

    @Override
    public @NotNull FluidState getFluidState(@NotNull BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return DDBlockEntityTypes.DEEP_FRYING_PAN.get().create(pos, state);
    }

    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state,
                                                                           @NotNull BlockEntityType<T> blockEntityType) {
        if (level.isClientSide) {
            return createTickerHelper(blockEntityType, DDBlockEntityTypes.DEEP_FRYING_PAN.get(), DeepFryingPanBlockEntity::animationTick);
        }
        return createTickerHelper(blockEntityType, DDBlockEntityTypes.DEEP_FRYING_PAN.get(), DeepFryingPanBlockEntity::cookingTick);
    }

    /** 只有下方是带托盘的加热源（例如灶台）时才显示托盘模型。 */
    private boolean getTrayState(LevelAccessor level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(ModTags.Blocks.TRAY_HEAT_SOURCES);
    }

    /**
     * 计算炸锅实际烹饪时长。
     * <p>
     * 沿用随意乐事原逻辑：以配方里写明的时长为基准全长烹饪，
     * 火焰附加每级减少 5%，最低不低于 {@link #MINIMUM_COOKING_TIME} tick。
     */
    public static int getDeepFryingCookingTime(int originalCookingTime, int fireAspectLevel) {
        int cookingTime = originalCookingTime > 0 ? originalCookingTime : 600;
        int cookingSeconds = cookingTime / 20;
        float cookingTimeReduction = 1.0F;

        if (fireAspectLevel > 0) {
            cookingTimeReduction -= fireAspectLevel * 0.05F;
        }

        int result = (int) (cookingSeconds * cookingTimeReduction) * 20;

        return Mth.clamp(result, MINIMUM_COOKING_TIME, cookingTime);
    }
}
