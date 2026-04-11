package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.Duacan_delight;
import by.timeslowly.duacan_delight.common.item.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class DDItems {
    public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems( Duacan_delight.MOD_ID);
    // TODO: 1.将此处所有物品注册项按照创造模式标签页的顺序排列/或者更简单按照首字母顺序排序

    // 纯物品

    public static final DeferredItem<Item> GODONGGUI = REGISTRY.register("godonggui", GodongguiItem::new);
    public static final DeferredItem<Item> ZHUJIAO = REGISTRY.register("zhujiao", ZhujiaoItem::new);
    public static final DeferredItem<Item> ZHUJIOAGANFAN = REGISTRY.register("zhujioaganfan", ZhujioaganfanItem::new);
    public static final DeferredItem<Item> SUANCAI = REGISTRY.register("suancai", SuancaiItem::new);
    public static final DeferredItem<Item> ANGLENG = REGISTRY.register("angleng", AnglengItem::new);
    public static final DeferredItem<Item> XIANFAN = REGISTRY.register("xianfan", XianfanItem::new);
    public static final DeferredItem<Item> RAW_CUMA = REGISTRY.register("raw_cuma", ShengcumaItem::new);
    public static final DeferredItem<Item> CUMA = REGISTRY.register("cuma", CumaItem::new);
    public static final DeferredItem<Item> YOUZHA = REGISTRY.register("youzha", YouzhaItem::new);
    public static final DeferredItem<Item> CHOPPED_GREENONION = REGISTRY.register("chopped_greenonion", ChoppedgreenonionItem::new);
    public static final DeferredItem<Item> GAICAI = REGISTRY.register("gaicai", GaicaiItem::new);
    public static final DeferredItem<Item> ZHUAFEN_MINCED_BEEF = REGISTRY.register("zhuafen_minced_beef", ZhuafenbeefItem::new);
    public static final DeferredItem<Item> BEEF_SOUP = REGISTRY.register("beef_soup", MinnanniuroutengItem::new);
    public static final DeferredItem<Item> SLICED_SUANCAI = REGISTRY.register("sliced_suancai", SuancaisiItem::new);
    public static final DeferredItem<Item> PIG_LARGE_INTESTINE = REGISTRY.register("pig_large_intestine", ZhudachangItem::new);
    public static final DeferredItem<Item> DACHANGCHAOSUANCAI = REGISTRY.register("dachangchaosuancai", DachangchaosuancaiItem::new);
    public static final DeferredItem<Item> BAMBOO_SHOOT = REGISTRY.register("bamboo_shoot", ZhusunItem::new);
    public static final DeferredItem<Item> SLICED_BAMBOOSHOOT = REGISTRY.register("sliced_bambooshoot", ZhusunpianItem::new);
    public static final DeferredItem<Item> BRAISED_BAMBOOSHOOT_WITH_SUANCAI = REGISTRY.register("braised_bambooshoot_with_suancai", SuancaimensunItem::new);
    public static final DeferredItem<Item> DRIED_SLICED_BAMBOOSHOOT = REGISTRY.register("dried_sliced_bambooshoot", ZhusunganItem::new);
    public static final DeferredItem<Item> SLICED_ZHUJIAO = REGISTRY.register("sliced_zhujiao", SlicedzhujiaoItem::new);
    public static final DeferredItem<Item> YIMI = REGISTRY.register("yimi", YimiItem::new);
    public static final DeferredItem<Item> LIANZI = REGISTRY.register("lianzi", LianziItem::new);
    public static final DeferredItem<Item> TREMELLA = REGISTRY.register("tremella", YinerItem::new);
    public static final DeferredItem<Item> XIANCAO_POWDER = REGISTRY.register("xiancao_powder", XiancaofenItem::new);
    public static final DeferredItem<Item> XIANCAODONG = REGISTRY.register("xiancaodong", XiancaodongItem::new);
    public static final DeferredItem<Item> ADAZI = REGISTRY.register("adazi", AdaziItem::new);
    public static final DeferredItem<Item> SIGUOTENG = REGISTRY.register("siguoteng", SiguotengItem::new);
    public static final DeferredItem<Item> TREMELLA_SPORE = REGISTRY.register("tremella_spore", YinerbaoziItem::new);
    public static final DeferredItem<Item> XIFAN = REGISTRY.register("xifan", XifanItem::new);
    public static final DeferredItem<Item> ROUSONGXIFAN = REGISTRY.register("rousongxifan", RousongxifanItem::new);
    public static final DeferredItem<Item> SWEET_POTATO_PORRIDGE = REGISTRY.register("sweet_potato_porridge", DiguaxifanItem::new);
    public static final DeferredItem<Item> WETRICE = REGISTRY.register("wetrice", WetriceItem::new);
    public static final DeferredItem<Item> ROUSONG = REGISTRY.register("rousong", RousongItem::new);
    public static final DeferredItem<Item> SLICED_SWEET_POTATO = REGISTRY.register("sliced_sweet_potato", SliceddiguaItem::new);
    public static final DeferredItem<Item> DIGUA_POWDER_DOUGH = REGISTRY.register("digua_powder_dough", DiguaPowerDoughItem::new);
    public static final DeferredItem<Item> BOILED_DIGUA_POWDER_DOUGH = REGISTRY.register("boiled_digua_powder_dough", BoiledDiguaPowderDoughItem::new);
    public static final DeferredItem<Item> FENSI = REGISTRY.register("fensi", FensiItem::new);
    public static final DeferredItem<Item> FENSI_STEAMED_CRAB = REGISTRY.register("fensi_steamed_crab", FensizhengxieItem::new);
    public static final DeferredItem<Item> WET_FENSI = REGISTRY.register("wet_fensi", WetFensiItem::new);
    public static final DeferredItem<Item> XIANCAO_STEM = REGISTRY.register("xiancao_stem", XiancaoStemItem::new);
    public static final DeferredItem<Item> IRON_CAN = REGISTRY.register("iron_can", IronCanItem::new);
    public static final DeferredItem<Item> OPENED_IRON_CAN = REGISTRY.register("opened_iron_can", OpenedIronCanItem::new);
    public static final DeferredItem<Item> MUSHROOM_MEAT_SAUCE = REGISTRY.register("mushroom_meat_sauce", MushroomMeatSauceItem::new);
    public static final DeferredItem<Item> XIFAN_WITH_MUSHROOM_MEAT_SAUCE = REGISTRY.register("xifan_with_mushroom_meat_sauce", XifanWithMushroomMeatSauceItem::new);
    public static final DeferredItem<Item> PIG_SMALL_INTESTINE = REGISTRY.register("pig_small_intestine", PigSmallIntestineItem::new);
    public static final DeferredItem<Item> TOFU = REGISTRY.register("tofu", TofuItem::new);
    public static final DeferredItem<Item> SMALLINTESTINE_TOFU_SOUP = REGISTRY.register("smallintestine_tofu_soup", SmallintestineTofuSoupItem::new);
    public static final DeferredItem<Item> SOYBEAN_MILK_FLUID_BUCKET = REGISTRY.register("soybean_milk_fluid_bucket", SoybeanMilkFluidItem::new);
    public static final DeferredItem<Item> WET_MIFEN_DOUGH = REGISTRY.register("wet_mifen_dough", WetMifenDoughItem::new);
    public static final DeferredItem<Item> COOKED_MIFEN_DOUGH = REGISTRY.register("cooked_mifen_dough", CookedMifenDoughItem::new);
    public static final DeferredItem<Item> WET_MIFEN = REGISTRY.register("wet_mifen", WetMifenItem::new);
    public static final DeferredItem<Item> MIFEN = REGISTRY.register("mifen", MifenItem::new);
    public static final DeferredItem<Item> COOKED_MIFEN = REGISTRY.register("cooked_mifen", CookedMifenItem::new);
    public static final DeferredItem<Item> RICE_MILK = REGISTRY.register("rice_milk", RiceMilkItem::new);
    public static final DeferredItem<Item> MIDENGGUI = REGISTRY.register("midenggui", MidengguiItem::new);
    public static final DeferredItem<Item> BOTTLE_OF_TEAOIL = REGISTRY.register("bottle_of_teaoil", BottleOfTeaoilItem::new);
    public static final DeferredItem<Item> MONASCUS = REGISTRY.register("monascus", MonascusItem::new);
    public static final DeferredItem<Item> TEAOIL_FLUID_BUCKET = REGISTRY.register("teaoil_fluid_bucket", TeaoilFluidItem::new);
    public static final DeferredItem<Item> WULING_ROAST_RABBIT = REGISTRY.register("wuling_roast_rabbit", WulingRoastRabbitItem::new);
    public static final DeferredItem<Item> CHINESE_ANGELICA = REGISTRY.register("chinese_angelica", ChineseAngelicaItem::new);
    public static final DeferredItem<Item> VEGETABLE_JUICE = REGISTRY.register("vegetable_juice", VegetableJuiceItem::new);
    public static final DeferredItem<Item> CHOPSTICKS = REGISTRY.register("chopsticks", ChopsticksItem::new);
    public static final DeferredItem<Item> MIANXIAN = REGISTRY.register("mianxian", MianxianItem::new);
    public static final DeferredItem<Item> WET_MIANXIAN = REGISTRY.register("wet_mianxian", WetMianxianItem::new);
    public static final DeferredItem<Item> CHINESE_WOLFBERRY = REGISTRY.register("chinese_wolfberry", ChineseWolfberryItem::new);
    public static final DeferredItem<Item> CHICKEN_CONSOMME = REGISTRY.register("chicken_consomme", GitengItem::new);
    public static final DeferredItem<Item> GITENG_MIANXIAN = REGISTRY.register("giteng_mianxian", GitengMianxianItem::new);
    public static final DeferredItem<Item> OYSTER = REGISTRY.register("oyster", OysterItem::new);
    public static final DeferredItem<Item> FRIED_GRATED_GARLIC = REGISTRY.register("fried_grated_garlic", FriedGratedGarlicItem::new);
    public static final DeferredItem<Item> MINCED_GARLIC_SAUCE = REGISTRY.register("minced_garlic_sauce", MincedGarlicSauceItem::new);
    public static final DeferredItem<Item> OYSTER_SHELL = REGISTRY.register("oyster_shell", OysterShellItem::new);
    public static final DeferredItem<Item> ROASTED_MINCED_GARLIC_OYSTER = REGISTRY.register("roasted_minced_garlic_oyster", RoastedOysterItem::new);
    public static final DeferredItem<Item> RAW_MINCED_GARLIC_OYSTER = REGISTRY.register("raw_minced_garlic_oyster", RawMincedGarlicOysterItem::new);
    public static final DeferredItem<Item> FENSI_STEAMED_CLAWSTER = REGISTRY.register("fensi_steamed_clawster", FensiSteamedClawsterItem::new);
    public static final DeferredItem<Item> PEANUTS_MILK = REGISTRY.register("peanuts_milk", PeanutsMilkItem::new);
    public static final DeferredItem<Item> RAW_OYSTER_MEAT = REGISTRY.register("raw_oyster_meat", RawOysterMeatItem::new);
    public static final DeferredItem<Item> EAJIAN = REGISTRY.register("eajian", EajianItem::new);
    public static final DeferredItem<Item> EAJIAN_TOMATO_SAUCE = REGISTRY.register("eajian_tomato_sauce", EajianTomatoSauceItem::new);
    public static final DeferredItem<Item> RAW_EAJIAN = REGISTRY.register("raw_eajian", RawEajianItem::new);
    public static final DeferredItem<Item> CASSIA_SEED = REGISTRY.register("cassia_seed", CassiaSeedItem::new);
    public static final DeferredItem<Item> FIRED_CASSIA_SEED = REGISTRY.register("fired_cassia_seed", FriedCassiaSeedItem::new);
    public static final DeferredItem<Item> OIL_CUT_WHEAT_TEA = REGISTRY.register("oil_cut_wheat_tea", OilCutWheatTeaItem::new);
    public static final DeferredItem<Item> KUCAI_SEEDS = REGISTRY.register("kucai_seeds", KucaiSeedsItem::new);
    public static final DeferredItem<Item> KUCAI = REGISTRY.register("kucai", KucaiItem::new);
    public static final DeferredItem<Item> SMALLINTESTINE_KUCAI_SOUP = REGISTRY.register("smallintestine_kucai_soup", SmallintestineKucaiSoupItem::new);
    public static final DeferredItem<Item> RAW_GUOJIAO = REGISTRY.register("raw_guojiao", RawGuojiaoItem::new);
    public static final DeferredItem<Item> GUOJIAO = REGISTRY.register("guojiao", GuojiaoItem::new);
    public static final DeferredItem<Item> CAOGUO = REGISTRY.register("caoguo", CaoguoItem::new);
    public static final DeferredItem<Item> RAW_CAOGUO = REGISTRY.register("raw_caoguo", RawCaoguoItem::new);
    public static final DeferredItem<Item> I_GRASS_JUICE_BUCKET = REGISTRY.register("i_grass_juice_bucket", IGrassJuiceBucketItem::new);
    public static final DeferredItem<Item> FRIED_TOFU = REGISTRY.register("fried_tofu", FriedTofuItem::new);
    public static final DeferredItem<Item> CLAM_TOFU_SOUP = REGISTRY.register("clam_tofu_soup", ClamTofuSoupItem::new);
    public static final DeferredItem<Item> DRIED_LAVER = REGISTRY.register("dried_laver", DriedLaverItem::new);
    public static final DeferredItem<Item> LAVER_PORK_SOUP = REGISTRY.register("laver_pork_soup", LaverPorkSoupItem::new);
    public static final DeferredItem<Item> KELP_TOFU_SOUP = REGISTRY.register("kelp_tofu_soup", KelpTofuSoupItem::new);
    public static final DeferredItem<Item> LAVER_EGG_SOUP = REGISTRY.register("laver_egg_soup", LaverEggSoupItem::new);
    public static final DeferredItem<Item> SEAWEED_SALAD = REGISTRY.register("seaweed_salad", SeaweedSaladItem::new);
    public static final DeferredItem<Item> RICE_WINE = REGISTRY.register("rice_wine", RiceWineItem::new);
    public static final DeferredItem<Item> SLICED_PIG_LIVER = REGISTRY.register("sliced_pig_liver", SlicedPigLiverItem::new);
    public static final DeferredItem<Item> ZHUAFEN_PIG_LIVER = REGISTRY.register("zhuafen_pig_liver", ZhuafenPigLiverItem::new);
    public static final DeferredItem<Item> PIG_LIVER_SOUP = REGISTRY.register("pig_liver_soup", PigLiverSoupItem::new);
    public static final DeferredItem<Item> XIANCAO_LEAF = REGISTRY.register("xiancao_leaf", XiancaoLeafItem::new);
    public static final DeferredItem<Item> RUSSULA_MIANXIAN = REGISTRY.register("russula_mianxian", RussulaMianxianItem::new);
    public static final DeferredItem<Item> DRIED_SUANCAI = REGISTRY.register("dried_suancai", DriedSuancaiItem::new);
    public static final DeferredItem<Item> GAICAI_SEEDS = REGISTRY.register("gaicai_seeds", JiecaiseedItem::new);
    public static final DeferredItem<Item> GAICAI_LEAF = REGISTRY.register("gaicai_leaf", GaicaileafItem::new);

    // 方块物品

    private static @NotNull DeferredItem<Item> block(@NotNull DeferredHolder<Block, Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static final DeferredItem<Item> GAICAI_CRATE = block(DDBlocks.GAICAI_CRATE);
    public static final DeferredItem<Item> LAVER = block(DDBlocks.LAVER_HEAD);
    public static final DeferredItem<Item> LAVER_CRATE = block(DDBlocks.LAVER_CRATE);
    public static final DeferredItem<Item> OYSTER_BARREL = block(DDBlocks.OYSTER_BARREL);
    public static final DeferredItem<Item> YIMI_BAG = block(DDBlocks.YIMI_BAG);
    public static final DeferredItem<Item> TREMELLA_BAG = block(DDBlocks.TREMELLA_BAG);
    public static final DeferredItem<Item> LIANZI_BAG = block(DDBlocks.LIANZI_BAG);
    public static final DeferredItem<Item> KUCAI_CRATE = block(DDBlocks.KUCAI_CRATE);
    public static final DeferredItem<Item> CHINESE_WOLFBERRY_BAG = block(DDBlocks.CHINESE_WOLFBERRY_BAG);
    public static final DeferredItem<Item> BAMBOO_SHOOT_CRATE = block(DDBlocks.BAMBOO_SHOOT_CRATE);
    public static final DeferredItem<Item> MONASCUS_BAG = block(DDBlocks.MONASCUS_BAG);
    public static final DeferredItem<Item> XIANCAO_POWDER_BAG = block(DDBlocks.XIANCAO_POWDER_BAG);
    public static final DeferredItem<Item> CHINESE_WOLFBERRY_LEAVES = block(DDBlocks.CHINESE_WOLFBERRY_LEAVES);
    public static final DeferredItem<Item> CHINESE_WOLFBERRY_TREESAPLING = block(DDBlocks.CHINESE_WOLFBERRY_TREESAPLING);


}
