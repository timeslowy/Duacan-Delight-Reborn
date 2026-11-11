package by.timeslowly.duacan_delight.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 创造模式标签页的**显示顺序**清单。
 * <p>
 * 这是创造标签唯一需要维护的地方：新增物品只要在 {@link #TAB_ITEMS} 的合适位置加一行，
 * 不用再去改 {@code DDCreativeTabs} 的构建逻辑。
 * <p>
 * 列表元素是 {@link Supplier}，保证 {@code DeferredItem.get()} / {@code DeferredBlock.get()}
 * 只在标签页真正构建时才求值，避免注册尚未完成时提前解析。
 * <p>
 * 顺序规则（自上而下）：
 * <ol>
 *     <li>成品菜肴（汤羹、面食、米饭、小吃等）</li>
 *     <li>生食材与半成品（肉类、蔬菜、米粉面条、调味料等）</li>
 *     <li>种植物（种子、茎、叶片）</li>
 *     <li>工具与容器（铁罐、筷子、油瓶、桶、炸锅等工作方块）</li>
 *     <li>装饰性存储方块（箱子、袋子、树苗、叶子）</li>
 * </ol>
 */
public final class DDCreativeTabOrder {

    private DDCreativeTabOrder() {
    }

    /**
     * 按显示顺序排列的标签页条目。
     */
    public static final List<Supplier<Item>> TAB_ITEMS = Arrays.asList(
            DDItems.GODONGGUI,
            DDItems.SMALLINTESTINE_TOFU_SOUP,
            DDItems.WULING_ROAST_RABBIT,
            DDItems.SMALLINTESTINE_KUCAI_SOUP,
            DDItems.ZHUJIAOGANFAN,
            DDItems.XIANFAN,
            DDItems.LAVER_PORK_SOUP,
            DDItems.PIG_LIVER_SOUP,
            DDItems.BEEF_SOUP,
            DDItems.COOKED_MIFEN,
            DDItems.RUSSULA_MIANXIAN,
            DDItems.CUMA,
            DDItems.EAJIAN,
            DDItems.EAJIAN_TOMATO_SAUCE,
            DDItems.FRIED_LAGER_INTESTINE_WITH_SUANCAI,
            DDItems.MIDENGGUI,
            DDItems.CHICKEN_CONSOMME,
            DDItems.CHICKEN_CONSOMME_MIANXIAN,
            DDItems.CLAM_TOFU_SOUP,
            DDItems.GUOJIAO,
            DDItems.CAOGUO,
            DDItems.FENSI_STEAMED_CRAB,
            DDItems.FENSI_STEAMED_CLAWSTER,
            DDItems.ROASTED_MINCED_GARLIC_OYSTER,
            DDItems.BRAISED_BAMBOOSHOOT_WITH_SUANCAI,
            DDItems.XIFAN,
            DDItems.SWEET_POTATO_PORRIDGE,
            DDItems.ROUSONGXIFAN,
            DDItems.XIFAN_WITH_MUSHROOM_MEAT_SAUCE,
            DDItems.KELP_TOFU_SOUP,
            DDItems.LAVER_EGG_SOUP,
            DDItems.SEAWEED_SALAD,
            DDItems.ROUSONG,
            DDItems.YOUZHA,
            DDItems.ANGLENG,
            DDItems.FRIED_TOFU,
            DDItems.XIANCAODONG,
            DDItems.SIGUOTENG,
            DDItems.MUSHROOM_MEAT_SAUCE,
            DDItems.PEANUTS_MILK,
            DDItems.VEGETABLE_JUICE,
            DDItems.OIL_CUT_WHEAT_TEA,
            DDItems.RAW_CUMA,
            DDItems.ZHUAFEN_MINCED_BEEF,
            DDItems.ZHUAFEN_PIG_LIVER,
            DDItems.RAW_EAJIAN,
            DDItems.MINCED_GARLIC_SAUCE,
            DDItems.ZHUJIAO,
            DDItems.SLICED_PIG_LIVER,
            DDItems.PIG_SMALL_INTESTINE,
            DDItems.PIG_LARGE_INTESTINE,
            DDItems.GAICAI,
            DDItems.GAICAI_LEAF,
            DDItems.SUANCAI,
            DDItems.DRIED_SUANCAI,
            DDItems.SLICED_SUANCAI,
            DDItems.KUCAI,
            DDItems.GAICAI_SEEDS,
            DDItems.XIANCAO_STEM,
            DDItems.XIANCAO_LEAF,
            DDItems.KUCAI_SEEDS,
            DDItems.TREMELLA,
            DDBlocks.LAVER_HEAD::asItem,
            DDItems.DRIED_LAVER,
            DDItems.TREMELLA_SPORE,
            DDItems.ADAZI,
            DDBlocks.CHINESE_WOLFBERRY_TREESAPLING::asItem,
            DDBlocks.CHINESE_WOLFBERRY_LEAVES::asItem,
            DDItems.CHINESE_WOLFBERRY,
            DDItems.YIMI,
            DDItems.LOTUS_SEEDS,
            DDItems.CHOPPED_GREENONION,
            DDItems.SLICED_SWEET_POTATO,
            DDItems.WETRICE,
            DDItems.DIGUA_POWDER_DOUGH,
            DDItems.BOILED_DIGUA_POWDER_DOUGH,
            DDItems.FENSI,
            DDItems.WET_FENSI,
            DDItems.WET_MIANXIAN,
            DDItems.MIANXIAN,
            DDItems.XIANCAO_POWDER,
            DDItems.SOYBEAN_POWDER,
            DDItems.RAW_CAOGUO,
            DDItems.BAMBOO_SHOOT,
            DDItems.SLICED_BAMBOOSHOOT,
            DDItems.SLICED_ZHUJIAO,
            DDItems.DRIED_SLICED_BAMBOOSHOOT,
            DDItems.MONASCUS,
            DDItems.FRIED_GRATED_GARLIC,
            DDItems.TOFU,
            DDItems.CHINESE_ANGELICA,
            DDItems.CASSIA_SEED,
            DDItems.FIRED_CASSIA_SEED,
            DDItems.WET_MIFEN_DOUGH,
            DDItems.COOKED_MIFEN_DOUGH,
            DDItems.WET_MIFEN,
            DDItems.MIFEN,
            DDItems.RAW_GUOJIAO,
            DDItems.RICE_MILK,
            DDItems.OYSTER,
            DDItems.OYSTER_SHELL,
            DDItems.RAW_MINCED_GARLIC_OYSTER,
            DDItems.RAW_OYSTER_MEAT,
            DDItems.IRON_CAN,
            DDItems.OPENED_IRON_CAN,
            DDItems.RICE_WINE,
            DDItems.CHOPSTICKS,
            DDItems.BOTTLE_OF_TEAOIL,
            DDItems.I_GRASS_JUICE_BUCKET,
            DDItems.SOYBEAN_MILK_FLUID_BUCKET,
            DDItems.TEAOIL_FLUID_BUCKET,
            DDItems.DEEP_FRYING_PAN,
            DDBlocks.LAVER_CRATE::asItem,
            DDBlocks.GAICAI_CRATE::asItem,
            DDBlocks.KUCAI_CRATE::asItem,
            DDBlocks.OYSTER_BARREL::asItem,
            DDBlocks.YIMI_BAG::asItem,
            DDBlocks.LOTUS_SEEDS_BAG::asItem,
            DDBlocks.CHINESE_WOLFBERRY_BAG::asItem,
            DDBlocks.BAMBOO_SHOOT_CRATE::asItem,
            DDBlocks.MONASCUS_BAG::asItem,
            DDBlocks.XIANCAO_POWDER_BAG::asItem,
            DDBlocks.TREMELLA_BAG::asItem
    );

    /**
     * 把清单内容依次加入标签页。
     */
    public static void acceptAll(CreativeTabSink sink) {
        for (Supplier<Item> item : TAB_ITEMS) {
            sink.accept(new ItemStack(item.get()));
        }
    }

    /**
     * 仅暴露 {@code accept(ItemStack)} 这一能力，避免直接把可变集合传出去。
     */
    @FunctionalInterface
    public interface CreativeTabSink {
        void accept(ItemStack stack);
    }
}