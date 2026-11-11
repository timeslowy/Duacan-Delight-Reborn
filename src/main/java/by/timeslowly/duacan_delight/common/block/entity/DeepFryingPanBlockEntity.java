package by.timeslowly.duacan_delight.common.block.entity;

import by.timeslowly.duacan_delight.common.block.DeepFryingPanBlock;
import by.timeslowly.duacan_delight.common.block.entity.container.DeepFryingPanMenu;
import by.timeslowly.duacan_delight.common.block.entity.inventory.FixedSizeItemStackHandler;
import by.timeslowly.duacan_delight.common.crafting.DeepFryingRecipe;
import by.timeslowly.duacan_delight.registry.DDBlockEntityTypes;
import by.timeslowly.duacan_delight.registry.DDItems;
import by.timeslowly.duacan_delight.registry.DDRecipeTypes;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Clearable;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.entity.HeatableBlockEntity;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.ItemUtils;

import java.util.List;
import java.util.Optional;

/**
 * 炸锅方块实体。
 * <p>
 * 最初移植自随意乐事（Casualness Delight，作者 Ginko）的 {@code DeepFryingPanBlockEntity}；
 * 现按需求重做了工作逻辑，改为「打开容器界面 → 放料 → 自动油炸 → 成品进输出槽」的形态，
 * 数据与槽位组织参照农夫乐事厨锅（{@code CookingPotBlockEntity}），但原料只有 1 格。
 * <p>
 * 槽位约定（同时也是 NBT {@code Inventory} 的索引，数据包 {@code Slot} 也使用这套索引）：
 * <ul>
 *     <li>0 - 原料输入。只接受能匹配到油炸配方的物品，一次 1 个。</li>
 *     <li>1 - 容具输入。放碗等容器；配方声明了 {@code container} 时成品会自动装进容具。</li>
 *     <li>2 - 成品输出。由机器写入，玩家只能取出。</li>
 * </ul>
 */
public class DeepFryingPanBlockEntity extends SyncedBlockEntity implements MenuProvider, Nameable, HeatableBlockEntity, Clearable {
    /** 原料槽索引。 */
    public static final int SLOT_INPUT = 0;
    /** 容具槽索引。 */
    public static final int SLOT_CONTAINER = 1;
    /** 输出槽索引。 */
    public static final int SLOT_OUTPUT = 2;
    /** 库存总槽位数。 */
    public static final int INVENTORY_SIZE = SLOT_OUTPUT + 1;

    private final ItemStackHandler inventory = createHandler();
    private int cookingTime;
    private int cookingTimeTotal;

    private ItemStack deepFryingPanStack;
    private int fireAspectLevel;

    /** 当前成品所要求的容具；为空表示该配方不需要容器。 */
    private ItemStack requiredContainer = ItemStack.EMPTY;
    /** 尚未装入容器的成品（内部中间态，不直接暴露给玩家）。 */
    private ItemStack mealStack = ItemStack.EMPTY;

    protected final ContainerData panData;
    private final Object2IntOpenHashMap<ResourceLocation> usedRecipeTracker;

    private final RecipeManager.CachedCheck<SingleRecipeInput, DeepFryingRecipe> quickCheck;

    public DeepFryingPanBlockEntity(BlockPos pos, BlockState state) {
        super(DDBlockEntityTypes.DEEP_FRYING_PAN.get(), pos, state);
        this.deepFryingPanStack = new ItemStack(DDItems.DEEP_FRYING_PAN.get());
        this.panData = createData();
        this.usedRecipeTracker = new Object2IntOpenHashMap<>();
        this.quickCheck = RecipeManager.createCheck(DDRecipeTypes.DEEP_FRYING.get());
    }

    // ========== 对外能力 ==========

    /**
     * 判断某个物品能否作为原料放进炸锅。
     * <p>
     * 两个条件：能匹配到油炸配方；且炸锅没有泡在水里
     * （含水时锅里的东西会被立刻吐出来，所以干脆不允许放入）。
     */
    public boolean isValidIngredient(@NotNull ItemStack stack) {
        if (stack.isEmpty() || isWaterlogged()) {
            return false;
        }
        return getMatchingRecipe(stack).isPresent();
    }

    /** 炸锅是否处于含水状态。 */
    public boolean isWaterlogged() {
        BlockState state = getBlockState();
        return state.hasProperty(DeepFryingPanBlock.WATERLOGGED) && state.getValue(DeepFryingPanBlock.WATERLOGGED);
    }

    /**
     * 判断某个物品能否放进容具槽。
     * 因为容具是由配方决定的（不同配方可能要求不同的碗碟），
     * 这里只在「已知当前配方要求的容具」时做严格限制，其余情况放行，
     * 以免玩家在还没放料时连碗都放不进去。
     */
    public boolean isValidContainer(@NotNull ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        if (this.requiredContainer.isEmpty()) {
            return true;
        }
        return ItemStack.isSameItem(this.requiredContainer, stack);
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public ItemStack getStoredStack() {
        return this.inventory.getStackInSlot(SLOT_INPUT);
    }

    public ItemStack getMeal() {
        return this.mealStack;
    }

    public ItemStack getContainer() {
        return this.requiredContainer;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    public int getCookingTimeTotal() {
        return this.cookingTimeTotal;
    }

    public boolean isCooking() {
        return isHeated() && !getStoredStack().isEmpty();
    }

    public boolean isHeated() {
        if (level != null) {
            return isHeated(level, worldPosition);
        }
        return false;
    }

    /** 掉落时把三格内容都吐出来，避免物品丢失。 */
    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        for (int i = 0; i < INVENTORY_SIZE; ++i) {
            drops.add(this.inventory.getStackInSlot(i));
        }
        if (!this.mealStack.isEmpty()) {
            drops.add(this.mealStack);
        }
        return drops;
    }

    // ========== 每 tick 逻辑 ==========

    public static void cookingTick(Level level, BlockPos pos, BlockState state, DeepFryingPanBlockEntity pan) {
        boolean isHeated = pan.isHeated(level, pos);
        boolean didInventoryChange = false;

        if (state.getValue(DeepFryingPanBlock.WATERLOGGED)) {
            // 泡在水里时把锅里的东西全部吐出来。
            if (hasAnyItem(pan.inventory)) {
                ItemUtils.dropItems(level, pos, pan.inventory);
                pan.mealStack = ItemStack.EMPTY;
                pan.requiredContainer = ItemStack.EMPTY;
                pan.cookingTime = 0;
                didInventoryChange = true;
            }
        } else if (isHeated && !pan.getStoredStack().isEmpty()) {
            Optional<RecipeHolder<DeepFryingRecipe>> recipe = pan.getMatchingRecipe(pan.getStoredStack());
            if (recipe.isPresent() && pan.canCook(recipe.get().value())) {
                didInventoryChange = pan.processCooking(recipe.get());
            } else {
                pan.cookingTime = Mth.clamp(pan.cookingTime - 2, 0, pan.cookingTimeTotal);
            }
        } else if (pan.cookingTime > 0) {
            // 火灭了以后进度缓慢回退，而不是直接清零。
            pan.cookingTime = Mth.clamp(pan.cookingTime - 2, 0, pan.cookingTimeTotal);
        }

        // 有成品但还没进输出槽时，尝试装进容具或直接进输出槽。
        if (!pan.mealStack.isEmpty()) {
            if (pan.requiredContainer.isEmpty()) {
                didInventoryChange |= pan.moveMealToOutput();
            } else if (!pan.inventory.getStackInSlot(SLOT_CONTAINER).isEmpty()) {
                didInventoryChange |= pan.useStoredContainerOnMeal();
            }
        }

        if (didInventoryChange) {
            pan.inventoryChanged();
        }
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, DeepFryingPanBlockEntity pan) {
        if (!pan.isHeated(level, pos) || pan.getStoredStack().isEmpty()) {
            return;
        }

        RandomSource random = level.random;
        if (random.nextFloat() < 0.2F) {
            double x = (double) pos.getX() + 0.5D + (random.nextDouble() * 0.4D - 0.2D);
            double y = (double) pos.getY() + 0.9D;
            double z = (double) pos.getZ() + 0.5D + (random.nextDouble() * 0.4D - 0.2D);
            double motionY = random.nextBoolean() ? 0.015D : 0.005D;
            level.addParticle(ModParticleTypes.STEAM.get(), x, y, z, 0.0D, motionY, 0.0D);
        }

        if (pan.fireAspectLevel > 0 && random.nextFloat() < pan.fireAspectLevel * 0.05F) {
            double x = (double) pos.getX() + 0.5D + (random.nextDouble() * 0.4D - 0.2D);
            double y = (double) pos.getY() + 0.9D;
            double z = (double) pos.getZ() + 0.5D + (random.nextDouble() * 0.4D - 0.2D);
            double motionX = level.random.nextFloat() - 0.5F;
            double motionY = level.random.nextFloat() * 0.5F + 0.2F;
            double motionZ = level.random.nextFloat() - 0.5F;
            level.addParticle(ParticleTypes.ENCHANTED_HIT, x, y, z, motionX, motionY, motionZ);
        }
    }

    // ========== 烹饪核心 ==========

    private boolean canCook(DeepFryingRecipe recipe) {
        if (this.level == null) {
            return false;
        }
        ItemStack result = recipe.getResultItem(this.level.registryAccess());
        if (result.isEmpty()) {
            return false;
        }
        // 输出槽里已有东西且不是同一种产物、或已经装不下，就暂停烹饪。
        ItemStack output = this.inventory.getStackInSlot(SLOT_OUTPUT);
        if (!this.mealStack.isEmpty()) {
            return false;
        }
        if (output.isEmpty()) {
            return true;
        }
        return ItemStack.isSameItemSameComponents(output, result)
                && output.getCount() + result.getCount() <= output.getMaxStackSize();
    }

    private boolean processCooking(RecipeHolder<DeepFryingRecipe> recipe) {
        if (this.level == null) {
            return false;
        }

        this.cookingTimeTotal = DeepFryingPanBlock.getDeepFryingCookingTime(recipe.value().getCookingTime(), this.fireAspectLevel);
        ++this.cookingTime;
        if (this.cookingTime < this.cookingTimeTotal) {
            return false;
        }

        this.cookingTime = 0;
        ItemStack ingredient = this.inventory.getStackInSlot(SLOT_INPUT);
        if (!ingredient.isEmpty()) {
            this.inventory.extractItem(SLOT_INPUT, 1, false);
        }

        this.requiredContainer = recipe.value().getOutputContainer().copy();
        this.mealStack = recipe.value().getResultItem(this.level.registryAccess()).copy();
        setRecipeUsed(recipe);

        if (this.level != null) {
            this.level.playSound(null, worldPosition.getX() + 0.5F, worldPosition.getY() + 0.5F, worldPosition.getZ() + 0.5F,
                    ModSounds.BLOCK_SKILLET_SIZZLE.get(), SoundSource.BLOCKS, 0.6F, 1.0F);
        }
        return true;
    }

    /** 无容具要求：成品直接进输出槽。 */
    private boolean moveMealToOutput() {
        ItemStack output = this.inventory.getStackInSlot(SLOT_OUTPUT);
        if (output.isEmpty()) {
            this.inventory.setStackInSlot(SLOT_OUTPUT, this.mealStack.copy());
            this.mealStack = ItemStack.EMPTY;
            return true;
        }
        if (ItemStack.isSameItemSameComponents(output, this.mealStack)
                && output.getCount() + this.mealStack.getCount() <= output.getMaxStackSize()) {
            output.grow(this.mealStack.getCount());
            this.mealStack = ItemStack.EMPTY;
            this.inventory.setStackInSlot(SLOT_OUTPUT, output);
            return true;
        }
        return false;
    }

    /** 有容具要求：从容具槽消耗一个容器，成品进输出槽。 */
    private boolean useStoredContainerOnMeal() {
        ItemStack containerStack = this.inventory.getStackInSlot(SLOT_CONTAINER);
        if (containerStack.isEmpty() || !ItemStack.isSameItem(this.requiredContainer, containerStack)) {
            return false;
        }
        ItemStack output = this.inventory.getStackInSlot(SLOT_OUTPUT);
        if (!output.isEmpty() && (!ItemStack.isSameItemSameComponents(output, this.mealStack)
                || output.getCount() + this.mealStack.getCount() > output.getMaxStackSize())) {
            return false;
        }

        containerStack.shrink(1);
        if (output.isEmpty()) {
            this.inventory.setStackInSlot(SLOT_OUTPUT, this.mealStack.copy());
        } else {
            output.grow(this.mealStack.getCount());
            this.inventory.setStackInSlot(SLOT_OUTPUT, output);
        }
        this.mealStack = ItemStack.EMPTY;
        return true;
    }

    private Optional<RecipeHolder<DeepFryingRecipe>> getMatchingRecipe(ItemStack stack) {
        if (this.level == null || stack.isEmpty()) {
            return Optional.empty();
        }
        return this.quickCheck.getRecipeFor(new SingleRecipeInput(stack), this.level);
    }

    /** 兼容旧接口：外部（如数据包或调试命令）仍可能用到。 */
    public IItemHandler getItemHandler() {
        return this.inventory;
    }

    // ========== 经验与配方统计 ==========

    public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
        if (recipe != null) {
            this.usedRecipeTracker.addTo(recipe.id(), 1);
        }
    }

    public void awardUsedRecipes(Player player) {
        List<RecipeHolder<?>> usedRecipes = getUsedRecipesAndPopExperience(player.level(), player.position());
        player.awardRecipes(usedRecipes);
        this.usedRecipeTracker.clear();
    }

    private List<RecipeHolder<?>> getUsedRecipesAndPopExperience(Level level, Vec3 pos) {
        List<RecipeHolder<?>> list = Lists.newArrayList();
        if (!(level instanceof ServerLevel serverLevel)) {
            return list;
        }
        for (Object2IntMap.Entry<ResourceLocation> entry : this.usedRecipeTracker.object2IntEntrySet()) {
            serverLevel.getRecipeManager().byKey(entry.getKey()).ifPresent(recipe -> {
                list.add(recipe);
                if (recipe.value() instanceof DeepFryingRecipe deepFrying) {
                    splitAndSpawnExperience(serverLevel, pos, entry.getIntValue(), deepFrying.getExperience());
                }
            });
        }
        return list;
    }

    private static void splitAndSpawnExperience(ServerLevel level, Vec3 pos, int craftedAmount, float experience) {
        int expTotal = Mth.floor((float) craftedAmount * experience);
        float expFraction = Mth.frac((float) craftedAmount * experience);
        if (expFraction != 0.0F && Math.random() < (double) expFraction) {
            ++expTotal;
        }
        if (expTotal > 0) {
            ExperienceOrb.award(level, pos, expTotal);
        }
    }

    // ========== 存档 ==========

    @Override
    protected void loadAdditional(@NotNull CompoundTag compound, @NotNull HolderLookup.Provider registries) {
        super.loadAdditional(compound, registries);
        this.inventory.deserializeNBT(registries, compound.getCompound("Inventory"));
        this.cookingTime = compound.getInt("CookTime");
        this.cookingTimeTotal = compound.getInt("CookTimeTotal");
        this.deepFryingPanStack = ItemStack.parseOptional(registries, compound.getCompound("DeepFryingPan"));
        this.requiredContainer = ItemStack.parseOptional(registries, compound.getCompound("Container"));
        this.mealStack = ItemStack.parseOptional(registries, compound.getCompound("Meal"));
        this.fireAspectLevel = ItemUtils.getValidatedEnchantmentLevel(Enchantments.FIRE_ASPECT, registries, this.deepFryingPanStack);

        CompoundTag recipesUsed = compound.getCompound("RecipesUsed");
        for (String key : recipesUsed.getAllKeys()) {
            this.usedRecipeTracker.put(ResourceLocation.parse(key), recipesUsed.getInt(key));
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compound, @NotNull HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        compound.put("Inventory", this.inventory.serializeNBT(registries));
        compound.putInt("CookTime", this.cookingTime);
        compound.putInt("CookTimeTotal", this.cookingTimeTotal);
        if (!this.deepFryingPanStack.isEmpty()) {
            compound.put("DeepFryingPan", this.deepFryingPanStack.save(registries));
        }
        compound.put("Container", this.requiredContainer.saveOptional(registries));
        compound.put("Meal", this.mealStack.saveOptional(registries));

        CompoundTag recipesUsed = new CompoundTag();
        this.usedRecipeTracker.forEach((id, amount) -> recipesUsed.putInt(id.toString(), amount));
        compound.put("RecipesUsed", recipesUsed);
    }

    // ========== MenuProvider ==========

    public ItemStack getDeepFryingPanAsItem() {
        return this.deepFryingPanStack;
    }

    public void setDeepFryingPanItem(ItemStack stack) {
        this.deepFryingPanStack = stack.copy();
        if (this.level != null) {
            this.fireAspectLevel = ItemUtils.getValidatedEnchantmentLevel(Enchantments.FIRE_ASPECT, this.level.registryAccess(), stack);
        }
        this.inventoryChanged();
    }

    @Override
    public @NotNull Component getName() {
        return Component.translatable("block.duacan_delight.deep_frying_pan");
    }

    @Override
    public @NotNull Component getDisplayName() {
        return getName();
    }

    @Override
    public @Nullable Component getCustomName() {
        // 炸锅暂不支持铁砧改名，保留接口以便后续扩展。
        return null;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInventory, @NotNull Player player) {
        return new DeepFryingPanMenu(id, playerInventory, this, this.panData);
    }

    // ========== 杂项 ==========

    @Override
    public void clearContent() {
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            this.inventory.setStackInSlot(i, ItemStack.EMPTY);
        }
        this.mealStack = ItemStack.EMPTY;
        this.requiredContainer = ItemStack.EMPTY;
    }

    private ContainerData createData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DeepFryingPanBlockEntity.this.cookingTime;
                    case 1 -> DeepFryingPanBlockEntity.this.cookingTimeTotal;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                if (index == 0) {
                    DeepFryingPanBlockEntity.this.cookingTime = value;
                } else if (index == 1) {
                    DeepFryingPanBlockEntity.this.cookingTimeTotal = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    private static boolean hasAnyItem(ItemStackHandler handler) {
        for (int i = 0; i < handler.getSlots(); i++) {
            if (!handler.getStackInSlot(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 建立固定三槽位的库存。
     * <p>
     * 必须用 {@link FixedSizeItemStackHandler}：旧存档里写的是
     * {@code Inventory:{Size:1,...}}（初版炸锅只有单槽位），
     * 普通 {@link ItemStackHandler} 会按 NBT 的 Size 把库存缩回 1 格，
     * 而界面要访问 0/1/2 号槽位，导致开界面即崩
     * （{@code Slot 1 not in valid range - [0,1)}）。
     */
    private ItemStackHandler createHandler() {
        return new FixedSizeItemStackHandler(INVENTORY_SIZE) {
            @Override
            protected void onContentsChanged(int slot) {
                DeepFryingPanBlockEntity.this.inventoryChanged();
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return switch (slot) {
                    case SLOT_INPUT -> DeepFryingPanBlockEntity.this.isValidIngredient(stack);
                    case SLOT_CONTAINER -> DeepFryingPanBlockEntity.this.isValidContainer(stack);
                    default -> false;
                };
            }
        };
    }

    /** 供掉落与调试：物品注册名，便于在日志里识别。 */
    @Override
    public String toString() {
        return "DeepFryingPanBlockEntity[" + BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(getType()) + "@" + this.worldPosition + "]";
    }
}
