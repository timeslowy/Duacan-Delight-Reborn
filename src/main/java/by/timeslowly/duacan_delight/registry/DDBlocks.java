package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.Duacan_delight;
import by.timeslowly.duacan_delight.common.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class DDBlocks {
    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(Duacan_delight.MOD_ID);

    // Crate（木板箱）和 Bag（布袋）两类装饰性存储方块的共享属性
    // 全方块默认 getLightBlock 即返回 15，无需覆盖
    private static final Block.Properties CRATE_PROPS = Block.Properties.of().sound(SoundType.WOOD).strength(1f, 10f);
    private static final Block.Properties BAG_PROPS = Block.Properties.of().sound(SoundType.WOOL).strength(1f, 10f);

    public static final DeferredBlock<Block> GAICAI_CRATE = REGISTRY.register("gaicai_crate", () -> new Block(CRATE_PROPS.requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TREMELLA_CROP = REGISTRY.register("tremella_crop", TremellaCropBlock::new);
    public static final DeferredBlock<Block> XIANCAO_CROP = REGISTRY.register("xiancao_crop", XiancaoCropBlock::new);
    public static final DeferredBlock<Block> KUCAI_CROP = REGISTRY.register("kucai_crop", KucaiCropBlock::new);
    public static final DeferredBlock<Block> GAICAI_CROP = REGISTRY.register("gaicai_crop", GaicaiCropBlock::new);
    public static final DeferredBlock<Block> SOYBEAN_MILK_FLUID = REGISTRY.register("soybean_milk_fluid", SoybeanMilkFluidBlock::new);
    public static final DeferredBlock<Block> TEAOIL_FLUID = REGISTRY.register("teaoil_fluid", TeaoilFluidBlock::new);
    public static final DeferredBlock<Block> LAVER_HEAD = REGISTRY.register("laver_head", () -> new LaverHeadBlock(Block.Properties.of()));
    public static final DeferredBlock<Block> LAVER = REGISTRY.register("laver", () -> new LaverBlock(Block.Properties.of()));
    public static final DeferredBlock<Block> LAVER_CRATE = REGISTRY.register("laver_crate", () -> new Block(CRATE_PROPS.requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> OYSTER_BARREL = REGISTRY.register("oyster_barrel", () -> new Block(CRATE_PROPS.requiresCorrectToolForDrops()));
    // 薏米袋需要非遮挡属性（光线穿透），同时视觉形状设为空以阻止相邻方块面剔除
    public static final DeferredBlock<Block> YIMI_BAG = REGISTRY.register("yimi_bag", () -> new Block(BAG_PROPS.noOcclusion().isRedstoneConductor((bs, br, bp) -> false)) {
        @Override
        public @NotNull VoxelShape getVisualShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
            return Shapes.empty();
        }
    });
    public static final DeferredBlock<Block> TREMELLA_BAG = REGISTRY.register("tremella_bag", () -> new Block(BAG_PROPS));
    public static final DeferredBlock<Block> LOTUS_SEEDS_BAG = REGISTRY.register("lotus_seeds_bag", () -> new Block(BAG_PROPS));
    public static final DeferredBlock<Block> KUCAI_CRATE = REGISTRY.register("kucai_crate", () -> new Block(CRATE_PROPS));
    public static final DeferredBlock<Block> CHINESE_WOLFBERRY_BAG = REGISTRY.register("chinese_wolfberry_bag", () -> new Block(BAG_PROPS));
    public static final DeferredBlock<Block> BAMBOO_SHOOT_CRATE = REGISTRY.register("bamboo_shoot_crate", () -> new Block(Block.Properties.of().sound(SoundType.GRAVEL).strength(1f, 10f)));
    public static final DeferredBlock<Block> MONASCUS_BAG = REGISTRY.register("monascus_bag", () -> new Block(BAG_PROPS));
    public static final DeferredBlock<Block> XIANCAO_POWDER_BAG = REGISTRY.register("xiancao_powder_bag", () -> new Block(BAG_PROPS));
    public static final DeferredBlock<Block> CHINESE_WOLFBERRY_LEAVES = REGISTRY.register("chinese_wolfberry_leaves", ChineseWolfberryLeavesBlock::new);
    public static final DeferredBlock<Block> CHINESE_WOLFBERRY_TREESAPLING = REGISTRY.register("chinese_wolfberry_treesapling", ChineseWolfberryTreesaplingBlock::new);

}
