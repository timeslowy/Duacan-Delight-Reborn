package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.Duacan_delight;
import by.timeslowly.duacan_delight.common.block.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DDBlocks {
    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(Duacan_delight.MOD_ID);

    // TODO:此处需要按照首字母顺序排序

    public static final DeferredBlock<Block> GAICAI_CRATE = REGISTRY.register("gaicai_crate", JiecaicrateBlock::new);
    public static final DeferredBlock<Block> TREMELLA_CROP = REGISTRY.register("tremella_crop", TremellaCropBlock::new);
    public static final DeferredBlock<Block> XIANCAO_CROP = REGISTRY.register("xiancao_crop", XiancaoCropBlock::new);
    public static final DeferredBlock<Block> KUCAI_CROP = REGISTRY.register("kucai_crop", KucaiCropBlock::new);
    public static final DeferredBlock<Block> GAICAI_CROP = REGISTRY.register("gaicai_crop", GaicaiCropBlock::new);
    public static final DeferredBlock<Block> SOYBEAN_MILK_FLUID = REGISTRY.register("soybean_milk_fluid", SoybeanMilkFluidBlock::new);
    public static final DeferredBlock<Block> TEAOIL_FLUID = REGISTRY.register("teaoil_fluid", TeaoilFluidBlock::new);
    public static final DeferredBlock<Block> LAVER_HEAD = REGISTRY.register("laver_head", () -> new LaverHeadBlock(Block.Properties.of()));
    public static final DeferredBlock<Block> LAVER = REGISTRY.register("laver", () -> new LaverBlock(Block.Properties.of()));
    public static final DeferredBlock<Block> LAVER_CRATE = REGISTRY.register("laver_crate", LaverCrateBlock::new);
    public static final DeferredBlock<Block> OYSTER_BARREL = REGISTRY.register("oyster_barrel", OysterBarrelBlock::new);
    public static final DeferredBlock<Block> YIMI_BAG = REGISTRY.register("yimi_bag", YimiBagBlock::new);
    public static final DeferredBlock<Block> TREMELLA_BAG = REGISTRY.register("tremella_bag", YinerBagBlock::new);
    public static final DeferredBlock<Block> LOTUS_SEEDS_BAG = REGISTRY.register("lotus_seeds_bag", LotusSeedsBagBlock::new);
    public static final DeferredBlock<Block> KUCAI_CRATE = REGISTRY.register("kucai_crate", KucaiCrateBlock::new);
    public static final DeferredBlock<Block> CHINESE_WOLFBERRY_BAG = REGISTRY.register("chinese_wolfberry_bag", ChineseWolfberryBagBlock::new);
    public static final DeferredBlock<Block> BAMBOO_SHOOT_CRATE = REGISTRY.register("bamboo_shoot_crate", BambooShootCrateBlock::new);
    public static final DeferredBlock<Block> MONASCUS_BAG = REGISTRY.register("monascus_bag", MonascusBagBlock::new);
    public static final DeferredBlock<Block> XIANCAO_POWDER_BAG = REGISTRY.register("xiancao_powder_bag", XiancaoPowderBagBlock::new);
    public static final DeferredBlock<Block> CHINESE_WOLFBERRY_LEAVES = REGISTRY.register("chinese_wolfberry_leaves", ChineseWolfberryLeavesBlock::new);
    public static final DeferredBlock<Block> CHINESE_WOLFBERRY_TREESAPLING = REGISTRY.register("chinese_wolfberry_treesapling", ChineseWolfberryTreesaplingBlock::new);

}
