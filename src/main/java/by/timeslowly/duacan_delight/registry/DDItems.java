package by.timeslowly.duacan_delight.registry;

import by.timeslowly.duacan_delight.Duacan_delight;
import by.timeslowly.duacan_delight.common.item.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class DDItems {
    public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems( Duacan_delight.MOD_ID);

    // 通用辅助方法
    private static final Item.Properties COMMON_PROPS = new Item.Properties().stacksTo(64).rarity(Rarity.COMMON);

    // 纯物品（基本按创造模式标签页顺序排列）

    public static final DeferredItem<Item> GODONGGUI = REGISTRY.register("godonggui", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(8).saturationModifier(0.5f).alwaysEdible().build()), "item.duacan_delight.godonggui.description_1"));
    public static final DeferredItem<Item> OPENED_IRON_CAN = REGISTRY.register("opened_iron_can", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> SMALLINTESTINE_TOFU_SOUP = REGISTRY.register("smallintestine_tofu_soup", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(10).saturationModifier(0.6f).alwaysEdible().usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.smallintestine_tofu_soup.description_1"));
    public static final DeferredItem<Item> WULING_ROAST_RABBIT = REGISTRY.register("wuling_roast_rabbit", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(16).saturationModifier(0.8f).effect(() -> new MobEffectInstance(MobEffects.JUMP, 1200, 1), 1.0f).build()), "item.duacan_delight.wuling_roast_rabbit.description_1"));
    public static final DeferredItem<Item> SMALLINTESTINE_KUCAI_SOUP = REGISTRY.register("smallintestine_kucai_soup", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.5f).alwaysEdible().usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.smallintestine_kucai_soup.description_1"));
    public static final DeferredItem<Item> ZHUJIOAGANFAN = REGISTRY.register("zhujioaganfan", ZhujioaganfanItem::new);
    public static final DeferredItem<Item> XIANFAN = REGISTRY.register("xianfan", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(16).saturationModifier(0.8f).usingConvertsTo(Items.BOWL).effect(() -> new MobEffectInstance(MobEffects.HEAL, 2, 0), 1.0f).build()), "item.duacan_delight.xianfan.description_1"));
    public static final DeferredItem<Item> LAVER_PORK_SOUP = REGISTRY.register("laver_pork_soup", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).alwaysEdible().usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.laver_pork_soup.description_1"));
    public static final DeferredItem<Item> PIG_LIVER_SOUP = REGISTRY.register("pig_liver_soup", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.pig_liver_soup.description_1"));
    public static final DeferredItem<Item> BEEF_SOUP = REGISTRY.register("beef_soup", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().nutrition(18).saturationModifier(0.8f).usingConvertsTo(Items.BOWL).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3000, 0), 1.0f).build()), "item.duacan_delight.beef_soup.description_1"));
    public static final DeferredItem<Item> COOKED_MIFEN = REGISTRY.register("cooked_mifen", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(16).saturationModifier(0.9f).usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.cooked_mifen.description_1"));
    public static final DeferredItem<Item> RUSSULA_MIANXIAN = REGISTRY.register("russula_mianxian", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(8).saturationModifier(1f).usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.russula_mianxian.description_1"));
    public static final DeferredItem<Item> CUMA = REGISTRY.register("cuma", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(14).saturationModifier(0.8f).usingConvertsTo(Items.BOWL).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 3600, 0), 1.0f).build()), "item.duacan_delight.cuma.description_1"));
    public static final DeferredItem<Item> EAJIAN = REGISTRY.register("eajian", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(11).saturationModifier(1f).usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.eajian.description_1"));
    public static final DeferredItem<Item> EAJIAN_TOMATO_SAUCE = REGISTRY.register("eajian_tomato_sauce", EajianTomatoSauceItem::new);
    public static final DeferredItem<Item> FRIED_LAGER_INTESTINE_WITH_SUANCAI = REGISTRY.register("fried_lager_intestine_with_suancai", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(12).saturationModifier(0.8f).usingConvertsTo(Items.BOWL).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 1.0f).build()), "item.duacan_delight.fried_lager_intestine_with_suancai.description_1"));
    public static final DeferredItem<Item> MIDENGGUI = REGISTRY.register("midenggui", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(6).saturationModifier(0.3f).build()), "item.duacan_delight.midenggui.description_1"));
    public static final DeferredItem<Item> CHICKEN_CONSOMME = REGISTRY.register("chicken_consomme", () -> new TooltipItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().nutrition(10).saturationModifier(0.6f).alwaysEdible().usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.chicken_consomme.description_1"));
    public static final DeferredItem<Item> CHICKEN_CONSOMME_MIANXIAN = REGISTRY.register("chicken_consomme_mianxian", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(10).saturationModifier(1f).usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.chicken_consomme_mianxian.description_1"));
    public static final DeferredItem<Item> CLAM_TOFU_SOUP = REGISTRY.register("clam_tofu_soup", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.8f).usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.clam_tofu_soup.description_1"));
    public static final DeferredItem<Item> GUOJIAO = REGISTRY.register("guojiao", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(8).saturationModifier(1.2f).build()), "item.duacan_delight.guojiao.description_1"));
    public static final DeferredItem<Item> CAOGUO = REGISTRY.register("caoguo", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(5).saturationModifier(0.8f).build()), "item.duacan_delight.caoguo.description_1"));
    public static final DeferredItem<Item> FENSI_STEAMED_CRAB = REGISTRY.register("fensi_steamed_crab", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().nutrition(14).saturationModifier(0.8f).usingConvertsTo(Items.BOWL).effect(() -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1200, 0), 1.0f).build()), "item.duacan_delight.fensi_steamed_crab.description_0"));
    public static final DeferredItem<Item> FENSI_STEAMED_CLAWSTER = REGISTRY.register("fensi_steamed_clawster", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().nutrition(15).saturationModifier(0.8f).usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.fensi_steamed_clawster.description_0"));
    public static final DeferredItem<Item> BRAISED_BAMBOOSHOOT_WITH_SUANCAI = REGISTRY.register("braised_bambooshoot_with_suancai", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(14).saturationModifier(0.8f).usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.braised_bambooshoot_with_suancai.description_0"));
    public static final DeferredItem<Item> XIFAN = REGISTRY.register("xifan", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(4).saturationModifier(1f).usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.xifan.description_0"));
    public static final DeferredItem<Item> SWEET_POTATO_PORRIDGE = REGISTRY.register("sweet_potato_porridge", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.7f).usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.sweet_potato_porridge.description_0"));
    public static final DeferredItem<Item> ROUSONGXIFAN = REGISTRY.register("rousongxifan", RousongxifanItem::new);
    public static final DeferredItem<Item> XIFAN_WITH_MUSHROOM_MEAT_SAUCE = REGISTRY.register("xifan_with_mushroom_meat_sauce", XifanWithMushroomMeatSauceItem::new);
    public static final DeferredItem<Item> KELP_TOFU_SOUP = REGISTRY.register("kelp_tofu_soup", KelpTofuSoupItem::new);
    public static final DeferredItem<Item> LAVER_EGG_SOUP = REGISTRY.register("laver_egg_soup", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(2).saturationModifier(1f).alwaysEdible().usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.laver_egg_soup.description_0"));
    public static final DeferredItem<Item> SEAWEED_SALAD = REGISTRY.register("seaweed_salad", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.8f).usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.seaweed_salad.description_0"));
    public static final DeferredItem<Item> ROUSONG = REGISTRY.register("rousong", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.5f).build()).craftRemainder(Items.BOWL)));
    public static final DeferredItem<Item> YOUZHA = REGISTRY.register("youzha", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).alwaysEdible().usingConvertsTo(Items.BOWL).build()), "item.duacan_delight.youzha.description_0"));
    public static final DeferredItem<Item> ANGLENG = REGISTRY.register("angleng", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(4).saturationModifier(0.5f).build()), "item.duacan_delight.angleng.description_0"));
    public static final DeferredItem<Item> FRIED_TOFU = REGISTRY.register("fried_tofu", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(4).saturationModifier(0.5f).build()), "item.duacan_delight.fried_tofu.description_0"));
    public static final DeferredItem<Item> XIANCAODONG = REGISTRY.register("xiancaodong", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.8f).alwaysEdible().usingConvertsTo(Items.BOWL).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0), 1.0f).build()), "item.duacan_delight.xiancaodong.description_1"));
    public static final DeferredItem<Item> SIGUOTENG = REGISTRY.register("siguoteng", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(7).saturationModifier(0.6f).alwaysEdible().usingConvertsTo(Items.BOWL).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1), 1.0f).build()), "item.duacan_delight.siguoteng.description_0", "item.duacan_delight.siguoteng.description_1", "item.duacan_delight.siguoteng.description_2"));
    public static final DeferredItem<Item> MUSHROOM_MEAT_SAUCE = REGISTRY.register("mushroom_meat_sauce", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(10).saturationModifier(0.6f).build()).craftRemainder(DDItems.OPENED_IRON_CAN.get()), "item.duacan_delight.mushroom_meat_sauce.description_0", "item.duacan_delight.mushroom_meat_sauce.description_1"));
    public static final DeferredItem<Item> PEANUTS_MILK = REGISTRY.register("peanuts_milk", PeanutsMilkItem::new);
    public static final DeferredItem<Item> VEGETABLE_JUICE = REGISTRY.register("vegetable_juice", VegetableJuiceItem::new);
    public static final DeferredItem<Item> OIL_CUT_WHEAT_TEA = REGISTRY.register("oil_cut_wheat_tea", OilCutWheatTeaItem::new);
    public static final DeferredItem<Item> RAW_CUMA = REGISTRY.register("raw_cuma", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).craftRemainder(Items.BOWL), "item.duacan_delight.raw_cuma.description_0"));
    public static final DeferredItem<Item> ZHUAFEN_MINCED_BEEF = REGISTRY.register("zhuafen_minced_beef", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> ZHUAFEN_PIG_LIVER = REGISTRY.register("zhuafen_pig_liver", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> RAW_EAJIAN = REGISTRY.register("raw_eajian", () -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> MINCED_GARLIC_SAUCE = REGISTRY.register("minced_garlic_sauce", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build()).craftRemainder(Items.BOWL), "item.duacan_delight.minced_garlic_sauce.description_0"));
    public static final DeferredItem<Item> ZHUJIAO = REGISTRY.register("zhujiao", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON), "item.duacan_delight.zhujiao.description_0", "item.duacan_delight.zhujiao.description_1"));
    public static final DeferredItem<Item> SLICED_PIG_LIVER = REGISTRY.register("sliced_pig_liver", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> PIG_SMALL_INTESTINE = REGISTRY.register("pig_small_intestine", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> PIG_LARGE_INTESTINE = REGISTRY.register("pig_large_intestine", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON), "item.duacan_delight.pig_large_intestine.description_0"));
    public static final DeferredItem<Item> GAICAI = REGISTRY.register("gaicai", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON), "item.duacan_delight.gaicai.description_0"));
    public static final DeferredItem<Item> GAICAI_LEAF = REGISTRY.register("gaicai_leaf", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> SUANCAI = REGISTRY.register("suancai", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON), "item.duacan_delight.suancai.description_0"));
    public static final DeferredItem<Item> DRIED_SUANCAI = REGISTRY.register("dried_suancai", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 1.0f).build()), "item.duacan_delight.dried_suancai.description_0"));
    public static final DeferredItem<Item> SLICED_SUANCAI = REGISTRY.register("sliced_suancai", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> KUCAI = REGISTRY.register("kucai", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON), "item.duacan_delight.kucai.description_0"));
    public static final DeferredItem<Item> GAICAI_SEEDS = REGISTRY.register("gaicai_seeds", () -> new ItemNameBlockItem(DDBlocks.GAICAI_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> XIANCAO_STEM = REGISTRY.register("xiancao_stem", () -> new ItemNameBlockItem(DDBlocks.XIANCAO_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> XIANCAO_LEAF = REGISTRY.register("xiancao_leaf", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> KUCAI_SEEDS = REGISTRY.register("kucai_seeds", () -> new ItemNameBlockItem(DDBlocks.KUCAI_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> TREMELLA = REGISTRY.register("tremella", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> DRIED_LAVER = REGISTRY.register("dried_laver", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> TREMELLA_SPORE = REGISTRY.register("tremella_spore", TremellaSporeItem::new);
    public static final DeferredItem<Item> ADAZI = REGISTRY.register("adazi", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3f).build()), "item.duacan_delight.adazi.description_0"));
    public static final DeferredItem<Item> CHINESE_WOLFBERRY = REGISTRY.register("chinese_wolfberry", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> YIMI = REGISTRY.register("yimi", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> LOTUS_SEEDS = REGISTRY.register("lotus_seeds", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> CHOPPED_GREENONION = REGISTRY.register("chopped_greenonion", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON), "item.duacan_delight.chopped_greenonion.description_0"));
    public static final DeferredItem<Item> SLICED_SWEET_POTATO = REGISTRY.register("sliced_sweet_potato", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> WETRICE = REGISTRY.register("wetrice", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> DIGUA_POWDER_DOUGH = REGISTRY.register("digua_powder_dough", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> BOILED_DIGUA_POWDER_DOUGH = REGISTRY.register("boiled_digua_powder_dough", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> FENSI = REGISTRY.register("fensi", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> WET_FENSI = REGISTRY.register("wet_fensi", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> WET_MIANXIAN = REGISTRY.register("wet_mianxian", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> MIANXIAN = REGISTRY.register("mianxian", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON), "item.duacan_delight.mianxian.description_0"));
    public static final DeferredItem<Item> XIANCAO_POWDER = REGISTRY.register("xiancao_powder", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> RAW_CAOGUO = REGISTRY.register("raw_caoguo", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> BAMBOO_SHOOT = REGISTRY.register("bamboo_shoot", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> SLICED_BAMBOOSHOOT = REGISTRY.register("sliced_bambooshoot", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> SLICED_ZHUJIAO = REGISTRY.register("sliced_zhujiao", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> DRIED_SLICED_BAMBOOSHOOT = REGISTRY.register("dried_sliced_bambooshoot", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> MONASCUS = REGISTRY.register("monascus", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> FRIED_GRATED_GARLIC = REGISTRY.register("fried_grated_garlic", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> TOFU = REGISTRY.register("tofu", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> CHINESE_ANGELICA = REGISTRY.register("chinese_angelica", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON), "item.duacan_delight.chinese_angelica.description_0"));
    public static final DeferredItem<Item> CASSIA_SEED = REGISTRY.register("cassia_seed", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> FIRED_CASSIA_SEED = REGISTRY.register("fired_cassia_seed", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> WET_MIFEN_DOUGH = REGISTRY.register("wet_mifen_dough", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> COOKED_MIFEN_DOUGH = REGISTRY.register("cooked_mifen_dough", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> WET_MIFEN = REGISTRY.register("wet_mifen", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> MIFEN = REGISTRY.register("mifen", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> RAW_GUOJIAO = REGISTRY.register("raw_guojiao", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> RICE_MILK = REGISTRY.register("rice_milk", () -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.5f).alwaysEdible().build()).craftRemainder(Items.BOWL)));
    public static final DeferredItem<Item> OYSTER_SHELL = REGISTRY.register("oyster_shell", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON), "item.duacan_delight.oyster_shell.description_0"));
    public static final DeferredItem<Item> OYSTER = REGISTRY.register("oyster", () -> new TooltipItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON), "item.duacan_delight.oyster.description_0"));
    public static final DeferredItem<Item> RAW_MINCED_GARLIC_OYSTER = REGISTRY.register("raw_minced_garlic_oyster", () -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> RAW_OYSTER_MEAT = REGISTRY.register("raw_oyster_meat", () -> new Item(COMMON_PROPS));
    public static final DeferredItem<Item> ROASTED_MINCED_GARLIC_OYSTER = REGISTRY.register("roasted_minced_garlic_oyster", () -> new TooltipItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.6f).build()).craftRemainder(DDItems.OYSTER_SHELL.get()), "item.duacan_delight.roasted_minced_garlic_oyster.description_0"));
    public static final DeferredItem<Item> IRON_CAN = REGISTRY.register("iron_can", IronCanItem::new);
    public static final DeferredItem<Item> RICE_WINE = REGISTRY.register("rice_wine", RiceWineItem::new);
    public static final DeferredItem<Item> CHOPSTICKS = REGISTRY.register("chopsticks", ChopsticksItem::new);
    public static final DeferredItem<Item> BOTTLE_OF_TEAOIL = REGISTRY.register("bottle_of_teaoil", () -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).craftRemainder(Items.GLASS_BOTTLE)));
    public static final DeferredItem<Item> I_GRASS_JUICE_BUCKET = REGISTRY.register("i_grass_juice_bucket", IGrassJuiceBucketItem::new);
    public static final DeferredItem<Item> SOYBEAN_MILK_FLUID_BUCKET = REGISTRY.register("soybean_milk_fluid_bucket", () -> fluidBucket(DDFluids.SOYBEAN_MILK_FLUID));
    public static final DeferredItem<Item> TEAOIL_FLUID_BUCKET = REGISTRY.register("teaoil_fluid_bucket", () -> fluidBucket(DDFluids.TEAOIL_FLUID));

    // 方块物品

    private static @NotNull DeferredItem<Item> block(@NotNull DeferredHolder<Block, Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // 流体桶物品的通用构造——流体引用在 lambda 内通过 .get() 延迟解析，由 DeferredRegister 机制保证注册顺序安全
    private static @NotNull Item fluidBucket(@NotNull DeferredHolder<Fluid, ? extends Fluid> fluid) {
        return new BucketItem(fluid.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).rarity(Rarity.COMMON));
    }

    public static final DeferredItem<Item> GAICAI_CRATE = block(DDBlocks.GAICAI_CRATE);
    public static final DeferredItem<Item> LAVER = block(DDBlocks.LAVER_HEAD);
    public static final DeferredItem<Item> LAVER_CRATE = block(DDBlocks.LAVER_CRATE);
    public static final DeferredItem<Item> OYSTER_BARREL = block(DDBlocks.OYSTER_BARREL);
    public static final DeferredItem<Item> YIMI_BAG = block(DDBlocks.YIMI_BAG);
    public static final DeferredItem<Item> TREMELLA_BAG = block(DDBlocks.TREMELLA_BAG);
    public static final DeferredItem<Item> LOTUS_SEEDS_BAG = block(DDBlocks.LOTUS_SEEDS_BAG);
    public static final DeferredItem<Item> KUCAI_CRATE = block(DDBlocks.KUCAI_CRATE);
    public static final DeferredItem<Item> CHINESE_WOLFBERRY_BAG = block(DDBlocks.CHINESE_WOLFBERRY_BAG);
    public static final DeferredItem<Item> BAMBOO_SHOOT_CRATE = block(DDBlocks.BAMBOO_SHOOT_CRATE);
    public static final DeferredItem<Item> MONASCUS_BAG = block(DDBlocks.MONASCUS_BAG);
    public static final DeferredItem<Item> XIANCAO_POWDER_BAG = block(DDBlocks.XIANCAO_POWDER_BAG);
    public static final DeferredItem<Item> CHINESE_WOLFBERRY_LEAVES = block(DDBlocks.CHINESE_WOLFBERRY_LEAVES);
    public static final DeferredItem<Item> CHINESE_WOLFBERRY_TREESAPLING = block(DDBlocks.CHINESE_WOLFBERRY_TREESAPLING);


}
