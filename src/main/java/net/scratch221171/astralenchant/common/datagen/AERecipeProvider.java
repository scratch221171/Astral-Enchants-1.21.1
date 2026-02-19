package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.AndCondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEBlocks;
import net.scratch221171.astralenchant.common.registries.AEItems;
import net.scratch221171.astralenchant.common.util.ConfigCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AERecipeProvider extends RecipeProvider {
    public AERecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output, HolderLookup.@NotNull Provider holderLookup) {
        // AEエンチャントの本レシピ
        {
            AERecipeBuildingHelper.shapedEB(AEEnchantments.MITIGATION_PIERCING, 1, holderLookup)
                    .pattern("262").pattern("515").pattern("434")
                    .add('1', EBIngredient(Enchantments.BREACH, 4, holderLookup))
                    .add('2', Items.DIAMOND)
                    .add('3', DataComponentIngredient.of(true, DataComponents.POTION_CONTENTS, new PotionContents(Potions.STRONG_STRENGTH), Items.POTION))
                    .add('4', Items.NETHERITE_SCRAP)
                    .add('5', AEItems.BUDDING_ARCANIUM_INGOT)
                    .add('6', Items.HEAVY_CORE)
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.LAST_STAND, 1, holderLookup)
                    .pattern("242").pattern("313").pattern("536")
                    .add('1', EBIngredient(Enchantments.PROTECTION, 4, holderLookup))
                    .add('2', Items.EXPERIENCE_BOTTLE)
                    .add('3', Items.TOTEM_OF_UNDYING)
                    .add('4', Items.DRAGON_HEAD)
                    .add('5', DataComponentIngredient.of(true, DataComponents.POTION_CONTENTS, new PotionContents(Potions.STRONG_REGENERATION), Items.POTION))
                    .add('6', DataComponentIngredient.of(true, DataComponents.POTION_CONTENTS, new PotionContents(Potions.LONG_FIRE_RESISTANCE), Items.POTION))
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.ITEM_PROTECTION, 1, holderLookup)
                    .pattern("323").pattern("212").pattern("323")
                    .add('1', EBIngredient(Enchantments.MENDING, 1, holderLookup))
                    .add('2', Items.ENDER_CHEST)
                    .add('3', AEItems.ARCANE_QUARTZ)
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.ESSENCE_OF_ENCHANTMENT, 1, holderLookup)
                    .pattern("353").pattern("323").pattern("414")
                    .add('1', Items.BOOK)
                    .add('2', Items.ENCHANTING_TABLE)
                    .add('3', Items.BOOKSHELF)
                    .add('4', Items.LAPIS_BLOCK)
                    .add('5', Items.BEACON)
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.COOLDOWN_REDUCTION, 1, holderLookup)
                    .pattern("636").pattern("412").pattern("656")
                    .add('1', EBIngredient(Enchantments.EFFICIENCY, 5, holderLookup))
                    .add('2', Items.CHORUS_FRUIT)
                    .add('3', Items.ENDER_PEARL)
                    .add('4', Items.SHIELD)
                    .add('5', Items.WIND_CHARGE)
                    .add('6', AEItems.ARCANE_QUARTZ)
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.FEATHER_TOUCH, 1, holderLookup)
                    .pattern("515").pattern("623").pattern("545")
                    .add('1', Items.AMETHYST_SHARD)
                    .add('2', EBIngredient(Enchantments.SILK_TOUCH, 1, holderLookup))
                    .add('3', Items.DEEPSLATE)
                    .add('4', Items.ECHO_SHARD)
                    .add('5', Items.FEATHER)
                    .add('6', Items.MOSSY_COBBLESTONE)
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.ADVENTURERS_LORE, 1, holderLookup)
                    .pattern("823").pattern("517").pattern("496")
                    .add('1', Items.BOOK)
                    .add('2', Items.BRAIN_CORAL)
                    .add('3', Items.GLOW_BERRIES)
                    .add('4', Items.BLUE_ICE)
                    .add('5', Items.MYCELIUM)
                    .add('6', Items.PRISMARINE_CRYSTALS)
                    .add('7', Items.RED_SANDSTONE)
                    .add('8', Items.SWEET_BERRIES)
                    .add('9', Items.DEEPSLATE_COAL_ORE)
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.COMPATIBILITY, 1, holderLookup)
                    .pattern("313").pattern("525").pattern("414")
                    .add('1', Items.ANVIL)
                    .add('2', Items.BOOK)
                    .add('3', Items.CONDUIT)
                    .add('4', AEItems.BUDDING_ARCANIUM_INGOT)
                    .add('5', AEBlocks.ARCANIUM_BLOCK)
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.ENDLESS_APPETITE, 1, holderLookup)
                    .pattern("635").pattern("124").pattern("897")
                    .add('1', Items.GOLDEN_APPLE)
                    .add('2', Items.BOOK)
                    .add('3', Items.GOLDEN_CARROT)
                    .add('4', Items.ENCHANTED_GOLDEN_APPLE)
                    .add('5', Items.GLOW_BERRIES)
                    .add('6', Items.BAKED_POTATO)
                    .add('7', Items.MUSHROOM_STEW)
                    .add('8', Items.RABBIT_STEW)
                    .add('9', Items.GLISTERING_MELON_SLICE)
                    .unlockedBy("has_book", has(Items.BOOK))
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.MOMENTUM, 1, holderLookup)
                    .pattern("434").pattern("212").pattern("535")
                    .add('1', EBIngredient(Enchantments.SWIFT_SNEAK, 3, holderLookup))
                    .add('2', DataComponentIngredient.of(true, DataComponents.POTION_CONTENTS, new PotionContents(Potions.STRONG_SWIFTNESS), Items.POTION))
                    .add('3', Items.COBWEB)
                    .add('4', Items.RABBIT_FOOT)
                    .add('5', Items.SOUL_SAND)
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.INSTANT_TELEPORT, 1, holderLookup)
                    .pattern("353").pattern("212").pattern("646")
                    .add('1', EBIngredient(Enchantments.RIPTIDE, 3, holderLookup))
                    .add('2', Items.AMETHYST_SHARD)
                    .add('3', Items.ENDER_PEARL)
                    .add('4', Items.END_CRYSTAL)
                    .add('5', Items.ENDER_EYE)
                    .add('6', AEItems.BUDDING_ARCANIUM_INGOT)
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.OVERLOAD, 1, holderLookup)
                    .pattern("121").pattern("345").pattern("161")
                    .add('1', Items.ENCHANTING_TABLE)
                    .add('2', EBIngredient(AEEnchantments.MITIGATION_PIERCING, 1, holderLookup))
                    .add('3', EBIngredient(AEEnchantments.COMPATIBILITY, 1, holderLookup))
                    .add('4', EBIngredient(AEEnchantments.ESSENCE_OF_ENCHANTMENT, 1, holderLookup))
                    .add('5', EBIngredient(AEEnchantments.ADVENTURERS_LORE, 1, holderLookup))
                    .add('6', EBIngredient(AEEnchantments.LAST_STAND, 1, holderLookup))
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.SLOT_EXPANSION, 1, holderLookup)
                    .pattern("434").pattern("212").pattern("434")
                    .add('1', EBIngredient(Enchantments.BINDING_CURSE, 1, holderLookup))
                    .add('2', Items.SHULKER_SHELL)
                    .add('3', Items.BUNDLE)
                    .add('4', Items.CHAIN)
                    .save(output.withConditions(new AndCondition(List.of(
                            new ModLoadedCondition("accessories"),
                            ConfigCondition.of(AEEnchantments.SLOT_EXPANSION)
                    ))));

            AERecipeBuildingHelper.shapedEB(AEEnchantments.REACTIVE_ARMOR, 1, holderLookup)
                    .pattern("121").pattern("347").pattern("565")
                    .add('1', Items.WITHER_ROSE)
                    .add('2', DataComponentIngredient.of(true, DataComponents.POTION_CONTENTS, new PotionContents(Potions.STRONG_HARMING), Items.SPLASH_POTION))
                    .add('3', Items.SCULK_CATALYST)
                    .add('4', EBIngredient(Enchantments.PROTECTION, 4, holderLookup))
                    .add('5', AEItems.ARCANE_QUARTZ)
                    .add('6', EBIngredient(Enchantments.FEATHER_FALLING, 4, holderLookup))
                    .add('7', Items.DRAGON_BREATH)
                    .save(output);

            AERecipeBuildingHelper.shapedEB(AEEnchantments.MYSTIC_REMNANTS, 1, holderLookup)
                    .pattern(" 1 ").pattern("232").pattern(" 4 ")
                    .add('1', Ingredient.of(Tags.Items.GEMS))
                    .add('2', Items.AMETHYST_SHARD)
                    .add('3', Items.BOOK)
                    .add('4', AEItems.ARCANE_QUARTZ)
                    .save(output);
        }

        // 精錬
        {
            SimpleCookingRecipeBuilder.smelting(
                            Ingredient.of(Items.ENCHANTED_BOOK),
                            RecipeCategory.MISC,
                            new ItemStack(AEItems.ENCHANTMENT_SHARD.get(), 3),
                            0.0f,
                            400
                    )
                    .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                    .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "enchantment_shard_smelting"));

            SimpleCookingRecipeBuilder.blasting(
                            Ingredient.of(Items.ENCHANTED_BOOK),
                            RecipeCategory.MISC,
                            new ItemStack(AEItems.ENCHANTMENT_SHARD.get(), 3),
                            0.0f,
                            200
                    )
                    .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                    .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "enchantment_shard_blasting"));
        }

        // 素材
        {
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AEItems.ARCANE_QUARTZ)
                    .requires(Items.QUARTZ)
                    .requires(AEItems.ENCHANTMENT_SHARD)
                    .requires(AEItems.ENCHANTMENT_SHARD)
                    .requires(AEItems.ENCHANTMENT_SHARD)
                    .requires(AEItems.ENCHANTMENT_SHARD)
                    .requires(AEItems.ENCHANTMENT_SHARD)
                    .requires(AEItems.ENCHANTMENT_SHARD)
                    .requires(AEItems.ENCHANTMENT_SHARD)
                    .requires(AEItems.ENCHANTMENT_SHARD)
                    .unlockedBy("has_enchantment_shard", has(AEItems.ENCHANTMENT_SHARD))
                    .save(output);

            AERecipeBuildingHelper.shapedItem(AEItems.ARCANIUM_INGOT)
                    .pattern("323").pattern("414").pattern("323")
                    .add('1', AEItems.ARCANE_QUARTZ)
                    .add('2', Items.GOLD_INGOT)
                    .add('3', Items.IRON_INGOT)
                    .add('4', Items.LAPIS_LAZULI)
                    .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "arcanium_ingot_from_arcane_quartz"));

            AERecipeBuildingHelper.shapedItem(RecipeCategory.BUILDING_BLOCKS, AEBlocks.ARCANIUM_BLOCK)
                    .pattern("111").pattern("111").pattern("111").add('1', AEItems.ARCANIUM_INGOT).save(output);
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AEItems.ARCANIUM_INGOT, 9)
                    .requires(AEBlocks.ARCANIUM_BLOCK).unlockedBy("has_arcanium_block", has(AEBlocks.ARCANIUM_BLOCK)).save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "arcanium_ingot_from_arcanium_block"));

            AERecipeBuildingHelper.shapedItem(RecipeCategory.MISC, new ItemStack(AEItems.BUDDING_ARCANIUM_INGOT.get(), 4))
                    .pattern("323").pattern("212").pattern("323")
                    .add('1', Items.BUDDING_AMETHYST)
                    .add('2', AEItems.ARCANIUM_INGOT)
                    .add('3', AEItems.ARCANE_QUARTZ)
                    .save(output);
        }

        // バニラレシピ
        {
            // バンドル救済
            AERecipeBuildingHelper.shapedItem(Items.BUNDLE)
                .pattern("121").pattern("2 2").pattern("222")
                .add('1', Items.STRING).add('2', Items.RABBIT_HIDE)
                .save(output.withConditions(ConfigCondition.of("enable_vanilla_items_recipes")));
        }
    }

    private static ItemStack EBItemStack(ResourceKey<Enchantment> enchantment, int level, HolderLookup.Provider holderLookup) {
        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
        stack.enchant(holderLookup.holderOrThrow(enchantment), level);
        return stack;
    }

    private static Ingredient EBIngredient(ResourceKey<Enchantment> enchantment, int level, HolderLookup.Provider holderLookup) {
        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        mutable.set(holderLookup.holderOrThrow(enchantment), level);
        return DataComponentIngredient.of(
                false,
                DataComponents.STORED_ENCHANTMENTS,
                mutable.toImmutable(),
                Items.ENCHANTED_BOOK);
    }

    private static class AERecipeBuildingHelper extends ShapedRecipeBuilder {
        @Nullable
        private static String enchantmentName;

        public AERecipeBuildingHelper(RecipeCategory category, ItemStack result) {
            super(category, result);
        }

        @Override
        public @NotNull AERecipeBuildingHelper pattern(@NotNull String pattern) {
            super.pattern(pattern);
            return this;
        }

        public @NotNull AERecipeBuildingHelper add(@NotNull Character symbol, @NotNull ItemLike item) {
            super.define(symbol, item).unlockedBy("has_" + item, has(item));
            return this;
        }

        public @NotNull AERecipeBuildingHelper add(@NotNull Character symbol, @NotNull Ingredient ingredient) {
            super.define(symbol, ingredient);
            return this;
        }

        @Override
        public void save(@NotNull RecipeOutput recipeOutput){
            String path;
            if (this.getResult().asItem() == Items.ENCHANTED_BOOK) {
                // エンチャントの本の場合は必ずenchantmentNameがNotNull
                path = "enchanted_book_" + enchantmentName;
            } else {
                path = BuiltInRegistries.ITEM.getKey(getResult()).getPath();
            }
            super.save(recipeOutput, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, path));
        }

        private static AERecipeBuildingHelper shapedEB(ResourceKey<Enchantment> enchantment, int level, HolderLookup.@NotNull Provider holderLookup) {
            enchantmentName = enchantment.location().getPath();
            return new AERecipeBuildingHelper(RecipeCategory.MISC, EBItemStack(enchantment, level, holderLookup));
        }

        public static AERecipeBuildingHelper shapedItem(ItemLike result) {
            return shapedItem(RecipeCategory.MISC, new ItemStack(result));
        }

        public static AERecipeBuildingHelper shapedItem(RecipeCategory category, ItemLike result) {
            return shapedItem(category, new ItemStack(result));
        }

        public static AERecipeBuildingHelper shapedItem(RecipeCategory category, ItemStack result) {
            return new AERecipeBuildingHelper(category, result);
        }
    }
}
