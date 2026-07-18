package net.scratch221171.astralenchant.datagen.providers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.AstralEnchantmentTags;
import net.scratch221171.astralenchant.common.condition.ConfigCondition;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEBlocks;
import net.scratch221171.astralenchant.common.registries.AEItems;
import org.jetbrains.annotations.NotNull;

public class AERecipeProvider extends RecipeProvider {
    public AERecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output, HolderLookup.@NotNull Provider holderLookup) {
        // AEエンチャントの本レシピ
        enchantedBookRecipe(output, holderLookup, AEEnchantments.MITIGATION_PIERCING, builder -> {
            var holder = holderLookup.holderOrThrow(Enchantments.BREACH);
            builder.pattern("262")
                    .pattern("515")
                    .pattern("434")
                    .define('1', enchantedBook(holder, 4))
                    .define('2', Tags.Items.GEMS_DIAMOND)
                    .define('3', potion(Potions.STRONG_STRENGTH))
                    .define('4', Items.NETHERITE_SCRAP)
                    .define('5', AEItems.BUDDING_ARCANIUM_INGOT)
                    .define('6', Items.HEAVY_CORE)
                    .unlockedBy("has_breach", has(holder));
        });
        enchantedBookRecipe(output, holderLookup, AEEnchantments.LAST_STAND, builder -> {
            var holder = holderLookup.holderOrThrow(Enchantments.PROTECTION);
            builder.pattern("242")
                    .pattern("313")
                    .pattern("536")
                    .define('1', enchantedBook(holder, 4))
                    .define('2', Items.EXPERIENCE_BOTTLE)
                    .define('3', Items.TOTEM_OF_UNDYING)
                    .define('4', Items.DRAGON_HEAD)
                    .define('5', potion(Potions.STRONG_REGENERATION))
                    .define('6', potion(Potions.LONG_FIRE_RESISTANCE))
                    .unlockedBy("has_protection", has(holder));
        });
        enchantedBookRecipe(
                output.withConditions(new ModLoadedCondition("l2hostility")),
                holderLookup,
                AEEnchantments.ITEM_PROTECTION,
                builder -> {
                    var holder = holderLookup.holderOrThrow(Enchantments.MENDING);
                    builder.pattern("323")
                            .pattern("415")
                            .pattern("626")
                            .define('1', enchantedBook(holder, 1))
                            .define('2', Tags.Items.CHESTS_ENDER)
                            .define('3', AEItems.BUDDING_ARCANIUM_INGOT)
                            .define('4', getFromRegistry("l2hostility", "reprint"))
                            .define('5', getFromRegistry("l2hostility", "dispell"))
                            .define('6', getFromRegistry("l2hostility", "hostility_essence"))
                            .unlockedBy("has_mending", has(holder));
                });
        enchantedBookRecipe(
                output,
                holderLookup,
                AEEnchantments.ESSENCE_OF_ENCHANTMENT,
                builder -> builder.pattern("353")
                        .pattern("323")
                        .pattern("414")
                        .define('1', Items.BOOK)
                        .define('2', Items.ENCHANTING_TABLE)
                        .define('3', Items.BOOKSHELF)
                        .define('4', Tags.Items.STORAGE_BLOCKS_LAPIS)
                        .define('5', Items.BEACON)
                        .unlockedBy("has_book", has(Items.BOOK)));
        enchantedBookRecipe(output, holderLookup, AEEnchantments.COOLDOWN_REDUCTION, builder -> {
            var holder = holderLookup.holderOrThrow(Enchantments.EFFICIENCY);
            builder.pattern("636")
                    .pattern("412")
                    .pattern("656")
                    .define('1', enchantedBook(holder, 5))
                    .define('2', Items.CHORUS_FRUIT)
                    .define('3', Tags.Items.ENDER_PEARLS)
                    .define('4', Items.SHIELD)
                    .define('5', Items.WIND_CHARGE)
                    .define('6', AstralEnchantmentTags.Items.GEMS_ARCANE_QUARTZ)
                    .unlockedBy("has_efficiency", has(holder));
        });
        enchantedBookRecipe(output, holderLookup, AEEnchantments.FEATHER_TOUCH, builder -> {
            var holder = holderLookup.holderOrThrow(Enchantments.SILK_TOUCH);
            builder.pattern("515")
                    .pattern("623")
                    .pattern("545")
                    .define('1', Tags.Items.GEMS_AMETHYST)
                    .define('2', enchantedBook(holder, 1))
                    .define('3', Items.DEEPSLATE)
                    .define('4', Items.ECHO_SHARD)
                    .define('5', Tags.Items.FEATHERS)
                    .define('6', Tags.Items.COBBLESTONES_MOSSY)
                    .unlockedBy("has_silk_touch", has(holder));
        });
        enchantedBookRecipe(
                output,
                holderLookup,
                AEEnchantments.ADVENTURERS_LORE,
                builder -> builder.pattern("823")
                        .pattern("517")
                        .pattern("496")
                        .define('1', Items.BOOK)
                        .define('2', Items.BRAIN_CORAL)
                        .define('3', Items.GLOW_BERRIES)
                        .define('4', Items.BLUE_ICE)
                        .define('5', Items.MYCELIUM)
                        .define('6', Items.PRISMARINE_CRYSTALS)
                        .define('7', Items.RED_SANDSTONE)
                        .define('8', Items.SWEET_BERRIES)
                        .define('9', Items.DEEPSLATE_COAL_ORE)
                        .unlockedBy("has_book", has(Items.BOOK)));
        enchantedBookRecipe(
                output,
                holderLookup,
                AEEnchantments.COMPATIBILITY,
                builder -> builder.pattern("313")
                        .pattern("525")
                        .pattern("414")
                        .define('1', Items.ANVIL)
                        .define('2', Items.BOOK)
                        .define('3', Items.CONDUIT)
                        .define('4', AEItems.BUDDING_ARCANIUM_INGOT)
                        .define('5', AstralEnchantmentTags.Items.STORAGE_BLOCKS_ARCANIUM)
                        .unlockedBy("has_book", has(Items.BOOK)));
        enchantedBookRecipe(
                output,
                holderLookup,
                AEEnchantments.ENDLESS_APPETITE,
                builder -> builder.pattern("635")
                        .pattern("124")
                        .pattern("897")
                        .define('1', Items.GOLDEN_APPLE)
                        .define('2', Items.BOOK)
                        .define('3', Items.GOLDEN_CARROT)
                        .define('4', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('5', Items.GLOW_BERRIES)
                        .define('6', Items.BAKED_POTATO)
                        .define('7', Items.MUSHROOM_STEW)
                        .define('8', Items.RABBIT_STEW)
                        .define('9', Items.GLISTERING_MELON_SLICE)
                        .unlockedBy("has_book", has(Items.BOOK)));
        enchantedBookRecipe(output, holderLookup, AEEnchantments.MOMENTUM, builder -> {
            var holder = holderLookup.holderOrThrow(Enchantments.SWIFT_SNEAK);
            builder.pattern("434")
                    .pattern("212")
                    .pattern("535")
                    .define('1', enchantedBook(holder, 3))
                    .define('2', potion(Potions.STRONG_SWIFTNESS))
                    .define('3', Items.COBWEB)
                    .define('4', Items.RABBIT_FOOT)
                    .define('5', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                    .unlockedBy("has_swift_sneak", has(holder));
        });
        enchantedBookRecipe(output, holderLookup, AEEnchantments.INSTANT_TELEPORT, builder -> {
            var holder = holderLookup.holderOrThrow(Enchantments.RIPTIDE);
            builder.pattern("353")
                    .pattern("212")
                    .pattern("646")
                    .define('1', enchantedBook(holder, 3))
                    .define('2', Tags.Items.GEMS_AMETHYST)
                    .define('3', Tags.Items.ENDER_PEARLS)
                    .define('4', Items.END_CRYSTAL)
                    .define('5', Items.ENDER_EYE)
                    .define('6', AEItems.BUDDING_ARCANIUM_INGOT)
                    .unlockedBy("has_riptide", has(holder));
        });
        enchantedBookRecipe(output, holderLookup, AEEnchantments.OVERLOAD, builder -> {
            var mitigation = holderLookup.holderOrThrow(AEEnchantments.MITIGATION_PIERCING);
            var compatibility = holderLookup.holderOrThrow(AEEnchantments.COMPATIBILITY);
            var essenceOfEnchantment = holderLookup.holderOrThrow(AEEnchantments.ESSENCE_OF_ENCHANTMENT);
            var adventurersLore = holderLookup.holderOrThrow(AEEnchantments.ADVENTURERS_LORE);
            var lastStand = holderLookup.holderOrThrow(AEEnchantments.LAST_STAND);
            builder.pattern("121")
                    .pattern("345")
                    .pattern("161")
                    .define('1', Items.ENCHANTING_TABLE)
                    .define('2', enchantedBook(mitigation, 1))
                    .define('3', enchantedBook(compatibility, 1))
                    .define('4', enchantedBook(essenceOfEnchantment, 1))
                    .define('5', enchantedBook(adventurersLore, 1))
                    .define('6', enchantedBook(lastStand, 1))
                    .unlockedBy("has_mitigation", has(mitigation))
                    .unlockedBy("has_compatibility", has(compatibility))
                    .unlockedBy("has_essenceOfEnchantment", has(essenceOfEnchantment))
                    .unlockedBy("has_adventurersLore", has(adventurersLore))
                    .unlockedBy("has_lastStand", has(lastStand));
        });
        enchantedBookRecipe(
                output.withConditions(new ModLoadedCondition("accessories")),
                holderLookup,
                AEEnchantments.SLOT_EXPANSION,
                builder -> {
                    var holder = holderLookup.holderOrThrow(Enchantments.BINDING_CURSE);
                    builder.pattern("434")
                            .pattern("212")
                            .pattern("434")
                            .define('1', enchantedBook(holder, 1))
                            .define('2', Items.SHULKER_SHELL)
                            .define('3', Items.BUNDLE)
                            .define('4', Items.CHAIN)
                            .unlockedBy("has_binding_curse", has(holder));
                });
        enchantedBookRecipe(output, holderLookup, AEEnchantments.REACTIVE_ARMOR, builder -> {
            var protection = holderLookup.holderOrThrow(Enchantments.PROTECTION);
            var featherFalling = holderLookup.holderOrThrow(Enchantments.FEATHER_FALLING);
            builder.pattern("121")
                    .pattern("347")
                    .pattern("565")
                    .define('1', Items.WITHER_ROSE)
                    .define('2', potion(Potions.STRONG_HARMING, Items.SPLASH_POTION))
                    .define('3', Items.SCULK_CATALYST)
                    .define('4', enchantedBook(protection, 4))
                    .define('5', AstralEnchantmentTags.Items.GEMS_ARCANE_QUARTZ)
                    .define('6', enchantedBook(featherFalling, 4))
                    .define('7', Items.DRAGON_BREATH)
                    .unlockedBy("has_protection", has(protection))
                    .unlockedBy("has_feather_falling", has(featherFalling));
        });
        enchantedBookRecipe(
                output,
                holderLookup,
                AEEnchantments.MYSTIC_REMNANTS,
                builder -> builder.pattern(" 1 ")
                        .pattern("232")
                        .pattern(" 4 ")
                        .define('1', Tags.Items.GEMS)
                        .define('2', Tags.Items.GEMS_AMETHYST)
                        .define('3', Items.BOOK)
                        .define('4', AstralEnchantmentTags.Items.GEMS_ARCANE_QUARTZ)
                        .unlockedBy("has_book", has(Items.BOOK)));
        enchantedBookRecipe(output, holderLookup, AEEnchantments.DISTORTION, builder -> {
            var holder = holderLookup.holderOrThrow(Enchantments.WIND_BURST);
            builder.pattern("121")
                    .pattern("343")
                    .pattern("151")
                    .define('1', Tags.Items.TOOLS_FISHING_ROD)
                    .define('2', Tags.Items.FEATHERS)
                    .define('3', Items.WIND_CHARGE)
                    .define('4', enchantedBook(holder, 3))
                    .define('5', Items.ENDER_EYE)
                    .unlockedBy("has_wind_burst", has(holder));
        });
        enchantedBookRecipe(output, holderLookup, AEEnchantments.OVER_MENDING, builder -> {
            var channeling = holderLookup.holderOrThrow(Enchantments.CHANNELING);
            var mending = holderLookup.holderOrThrow(Enchantments.MENDING);
            var unbreaking = holderLookup.holderOrThrow(Enchantments.UNBREAKING);
            builder.pattern("123")
                    .pattern("454")
                    .pattern("361")
                    .define('1', AEItems.BUDDING_ARCANIUM_INGOT)
                    .define('2', enchantedBook(channeling, 1))
                    .define('3', Items.EXPERIENCE_BOTTLE)
                    .define('4', enchantedBook(mending, 1))
                    .define('5', Items.LIGHTNING_ROD)
                    .define('6', enchantedBook(unbreaking, 3))
                    .unlockedBy("has_channeling", has(channeling))
                    .unlockedBy("has_mending", has(mending))
                    .unlockedBy("has_unbreaking", has(unbreaking));
        });
        // 精錬
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(Items.ENCHANTED_BOOK),
                        RecipeCategory.MISC,
                        AEItems.ENCHANTMENT_SHARD.toStack(3),
                        0.0f,
                        400)
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, AstralEnchant.id("enchantment_shard_smelting"));

        SimpleCookingRecipeBuilder.blasting(
                        Ingredient.of(Items.ENCHANTED_BOOK),
                        RecipeCategory.MISC,
                        AEItems.ENCHANTMENT_SHARD.toStack(3),
                        0.0f,
                        200)
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, AstralEnchant.id("enchantment_shard_blasting"));
        // 素材
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
                .unlockedBy(getHasName(AEItems.ENCHANTMENT_SHARD), has(AEItems.ENCHANTMENT_SHARD))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AEItems.ARCANIUM_INGOT)
                .pattern("323")
                .pattern("414")
                .pattern("323")
                .define('1', AstralEnchantmentTags.Items.GEMS_ARCANE_QUARTZ)
                .define('2', Tags.Items.INGOTS_GOLD)
                .define('3', Tags.Items.INGOTS_IRON)
                .define('4', Tags.Items.GEMS_LAPIS)
                .unlockedBy(getHasName(AEItems.ARCANE_QUARTZ), has(AstralEnchantmentTags.Items.GEMS_ARCANE_QUARTZ))
                .save(output, AstralEnchant.id("arcanium_ingot_from_arcane_quartz"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AEBlocks.ARCANIUM_BLOCK)
                .pattern("111")
                .pattern("121")
                .pattern("111")
                .define('1', AstralEnchantmentTags.Items.INGOTS_ARCANIUM)
                .define('2', AEItems.ARCANIUM_INGOT)
                .unlockedBy(getHasName(AEItems.ARCANIUM_INGOT), has(AstralEnchantmentTags.Items.INGOTS_ARCANIUM))
                .save(output, AstralEnchant.id("arcanium_block_from_ingot"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AEItems.ARCANIUM_INGOT, 9)
                .requires(AstralEnchantmentTags.Items.STORAGE_BLOCKS_ARCANIUM)
                .unlockedBy(
                        getHasName(AEBlocks.ARCANIUM_BLOCK), has(AstralEnchantmentTags.Items.STORAGE_BLOCKS_ARCANIUM))
                .save(output, AstralEnchant.id("arcanium_ingot_from_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, new ItemStack(AEItems.BUDDING_ARCANIUM_INGOT.get(), 4))
                .pattern("323")
                .pattern("212")
                .pattern("323")
                .define('1', Items.BUDDING_AMETHYST)
                .define('2', AstralEnchantmentTags.Items.INGOTS_ARCANIUM)
                .define('3', AstralEnchantmentTags.Items.GEMS_ARCANE_QUARTZ)
                .unlockedBy(getHasName(Items.BUDDING_AMETHYST), has(Items.BUDDING_AMETHYST))
                .save(output);
        // バニラレシピ
        // バンドル救済
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BUNDLE)
                .pattern("121")
                .pattern("2 2")
                .pattern("222")
                .define('1', Tags.Items.STRINGS)
                .define('2', Items.RABBIT_HIDE)
                .unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE))
                .save(
                        output.withConditions(new ConfigCondition(AEConfig.ENABLE_VANILLA_ITEM_RECIPES)),
                        AstralEnchant.id("bundle"));
    }

    //    Ingredient    //

    private static Item getFromRegistry(String namespace, String path) {
        return BuiltInRegistries.ITEM
                .get(ResourceLocation.fromNamespaceAndPath(namespace, path))
                .asItem();
    }

    private Ingredient potion(Holder<Potion> potion) {
        return potion(potion, Items.POTION);
    }

    private Ingredient potion(Holder<Potion> potion, ItemLike item) {
        return DataComponentIngredient.of(false, DataComponents.POTION_CONTENTS, new PotionContents(potion), item);
    }

    private Ingredient enchantedBook(Holder<Enchantment> holder, int level) {
        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        mutable.set(holder, level);
        return DataComponentIngredient.of(
                false, DataComponents.STORED_ENCHANTMENTS, mutable.toImmutable(), Items.ENCHANTED_BOOK);
    }

    //    Builder    //

    private void enchantedBookRecipe(
            @NotNull RecipeOutput output,
            HolderLookup.@NotNull Provider provider,
            ResourceKey<Enchantment> key,
            Consumer<ShapedRecipeBuilder> consumer) {
        enchantedBookRecipe(output, provider, key, 1, consumer);
    }

    private void enchantedBookRecipe(
            @NotNull RecipeOutput output,
            HolderLookup.@NotNull Provider provider,
            ResourceKey<Enchantment> key,
            int level,
            Consumer<ShapedRecipeBuilder> consumer) {
        Holder<Enchantment> holder = provider.holderOrThrow(key);
        ItemStack result = new ItemStack(Items.ENCHANTED_BOOK);
        result.enchant(holder, level);
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result);
        consumer.accept(builder);
        builder.save(output, AstralEnchant.id("enchanted_book_" + key.location().getPath()));
    }

    //    Criterion    //

    private static Criterion<InventoryChangeTrigger.TriggerInstance> has(Holder<Enchantment> holder) {
        return inventoryTrigger(ItemPredicate.Builder.item()
                .of(Items.ENCHANTED_BOOK)
                .withSubPredicate(
                        ItemSubPredicates.STORED_ENCHANTMENTS,
                        ItemEnchantmentsPredicate.storedEnchantments(List.of(
                                new EnchantmentPredicate(HolderSet.direct(holder), MinMaxBounds.Ints.atLeast(1))))));
    }
}
