package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
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
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.registries.AEItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AERecipeProvider extends RecipeProvider {
    public AERecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    private ItemStack EnchantedBookItemWith(ResourceKey<Enchantment> enchantment, int level, HolderLookup.Provider holderLookup) {
        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
        stack.enchant(holderLookup.holderOrThrow(enchantment), level);
        return stack;
    }

    private Ingredient EnchantedBookIngredientWith(ResourceKey<Enchantment> enchantment, int level, HolderLookup.Provider holderLookup) {
        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        mutable.set(holderLookup.holderOrThrow(enchantment), level);
        return DataComponentIngredient.of(
                false,
                DataComponents.STORED_ENCHANTMENTS,
                mutable.toImmutable(),
                Items.ENCHANTED_BOOK);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output, HolderLookup.@NotNull Provider holderLookup) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.MITIGATION_PIERCING, 1, holderLookup))
                .pattern("262").pattern("515").pattern("434")
                .define('1', EnchantedBookIngredientWith(Enchantments.BREACH, 4, holderLookup))
                .define('2', Items.DIAMOND)
                .define('3', DataComponentIngredient.of(true, DataComponents.POTION_CONTENTS, new PotionContents(Potions.STRONG_STRENGTH), Items.POTION))
                .define('4', Items.NETHERITE_SCRAP)
                .define('5', AEItems.SHARD_EMBEDDED_ARCANIUM_INGOT)
                .define('6', Items.HEAVY_CORE)
                .unlockedBy("has_shard_embedded_arcanium_ingot_book", has(AEItems.SHARD_EMBEDDED_ARCANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "mitigation_piercing"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.LAST_STAND, 1, holderLookup))
                .pattern("242").pattern("313").pattern("536")
                .define('1', EnchantedBookIngredientWith(Enchantments.PROTECTION, 4, holderLookup))
                .define('2', Items.EXPERIENCE_BOTTLE)
                .define('3', Items.TOTEM_OF_UNDYING)
                .define('4', Items.DRAGON_HEAD)
                .define('5', DataComponentIngredient.of(true, DataComponents.POTION_CONTENTS, new PotionContents(Potions.STRONG_REGENERATION), Items.POTION))
                .define('6', DataComponentIngredient.of(true, DataComponents.POTION_CONTENTS, new PotionContents(Potions.LONG_FIRE_RESISTANCE), Items.POTION))
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "last_stand"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.ITEM_PROTECTION, 1, holderLookup))
                .pattern("323").pattern("212").pattern("323")
                .define('1', EnchantedBookIngredientWith(Enchantments.MENDING, 1, holderLookup))
                .define('2', Items.ENDER_CHEST)
                .define('3', AEItems.ARCANIUM_INGOT)
                .unlockedBy("has_arcanium_ingot_book", has(AEItems.ARCANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "item_protection"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.ESSENCE_OF_ENCHANTMENT, 1, holderLookup))
                .pattern("353").pattern("323").pattern("414")
                .define('1', Items.BOOK)
                .define('2', Items.ENCHANTING_TABLE)
                .define('3', Items.BOOKSHELF)
                .define('4', Items.LAPIS_BLOCK)
                .define('5', Items.BEACON)
                .unlockedBy("has_book", has(Items.BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "essence_of_enchantment"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.COOLDOWN_REDUCTION, 1, holderLookup))
                .pattern("636").pattern("412").pattern("656")
                .define('1', EnchantedBookIngredientWith(Enchantments.EFFICIENCY, 5, holderLookup))
                .define('2', Items.CHORUS_FRUIT)
                .define('3', Items.ENDER_PEARL)
                .define('4', Items.SHIELD)
                .define('5', Items.WIND_CHARGE)
                .define('6', AEItems.ARCANIUM_INGOT)
                .unlockedBy("has_arcanium_ingot_book", has(AEItems.ARCANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "cooldown_reduction"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.FEATHER_TOUCH, 1, holderLookup))
                .pattern("515").pattern("623").pattern("545")
                .define('1', Items.AMETHYST_SHARD)
                .define('2', EnchantedBookIngredientWith(Enchantments.SILK_TOUCH, 1, holderLookup))
                .define('3', Items.DEEPSLATE)
                .define('4', Items.ECHO_SHARD)
                .define('5', Items.FEATHER)
                .define('6', Items.MOSSY_COBBLESTONE)
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "feather_touch"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.ADVENTURERS_LORE, 1, holderLookup))
                .pattern("823").pattern("517").pattern("496")
                .define('1', Items.BOOK)
                .define('2', Items.BRAIN_CORAL)
                .define('3', Items.GLOW_BERRIES)
                .define('4', Items.BLUE_ICE)
                .define('5', Items.MYCELIUM)
                .define('6', Items.PRISMARINE_CRYSTALS)
                .define('7', Items.RED_SANDSTONE)
                .define('8', Items.SWEET_BERRIES)
                .define('9', Items.DEEPSLATE_COAL_ORE)
                .unlockedBy("has_book", has(Items.BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "adventurers_lore"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.COMPATIBILITY, 1, holderLookup))
                .pattern("313").pattern("525").pattern("414")
                .define('1', Items.ANVIL)
                .define('2', Items.BOOK)
                .define('3', Items.CONDUIT)
                .define('4', AEItems.SHARD_EMBEDDED_ARCANIUM_INGOT)
                .define('5', Items.NETHERITE_BLOCK)
                .unlockedBy("has_shard_embedded_arcanium_ingot_book", has(AEItems.SHARD_EMBEDDED_ARCANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "compatible"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.ENDLESS_APPETITE, 1, holderLookup))
                .pattern("635").pattern("124").pattern("897")
                .define('1', Items.GOLDEN_APPLE)
                .define('2', Items.BOOK)
                .define('3', Items.GOLDEN_CARROT)
                .define('4', Items.ENCHANTED_GOLDEN_APPLE)
                .define('5', Items.GLOW_BERRIES)
                .define('6', Items.BAKED_POTATO)
                .define('7', Items.MUSHROOM_STEW)
                .define('8', Items.RABBIT_STEW)
                .define('9', Items.GLISTERING_MELON_SLICE)
                .unlockedBy("has_book", has(Items.BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "endless_appetite"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.MOMENTUM, 1, holderLookup))
                .pattern("434").pattern("212").pattern("535")
                .define('1', EnchantedBookIngredientWith(Enchantments.SWIFT_SNEAK, 3, holderLookup))
                .define('2', DataComponentIngredient.of(true, DataComponents.POTION_CONTENTS, new PotionContents(Potions.STRONG_SWIFTNESS), Items.POTION))
                .define('3', Items.COBWEB)
                .define('4', Items.RABBIT_FOOT)
                .define('5', Items.SOUL_SAND)
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "momentum"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.INSTANT_TELEPORT, 1, holderLookup))
                .pattern("353").pattern("212").pattern("646")
                .define('1', EnchantedBookIngredientWith(Enchantments.RIPTIDE, 3, holderLookup))
                .define('2', Items.AMETHYST_SHARD)
                .define('3', Items.ENDER_PEARL)
                .define('4', Items.END_CRYSTAL)
                .define('5', Items.ENDER_EYE)
                .define('6', AEItems.SHARD_EMBEDDED_ARCANIUM_INGOT)
                .unlockedBy("has_shard_embedded_arcanium_ingot_book", has(AEItems.SHARD_EMBEDDED_ARCANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "instant_teleport"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.OVERLOAD, 1, holderLookup))
                .pattern("121").pattern("345").pattern("161")
                .define('1', Items.ENCHANTING_TABLE)
                .define('2', EnchantedBookIngredientWith(AEEnchantments.MITIGATION_PIERCING, 1, holderLookup))
                .define('3', EnchantedBookIngredientWith(AEEnchantments.COMPATIBILITY, 1, holderLookup))
                .define('4', EnchantedBookIngredientWith(AEEnchantments.ESSENCE_OF_ENCHANTMENT, 1, holderLookup))
                .define('5', EnchantedBookIngredientWith(AEEnchantments.ADVENTURERS_LORE, 1, holderLookup))
                .define('6', EnchantedBookIngredientWith(AEEnchantments.LAST_STAND, 1, holderLookup))
                .unlockedBy("has_shard_embedded_arcanium_ingot_book", has(AEItems.SHARD_EMBEDDED_ARCANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "overload"));
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(AEEnchantments.SLOT_EXPANSION, 1, holderLookup))
//                .pattern("434").pattern("212").pattern("434")
//                .define('1', EnchantedBookIngredientWith(Enchantments.BINDING_CURSE, 1, holderLookup))
//                .define('2', Items.SHULKER_SHELL)
//                .define('3', Items.BUNDLE)
//                .define('4', Items.CHAIN)
//                .unlockedBy("has_book", has(Items.BOOK))
//                .save(output.withConditions(new ModLoadedCondition("accessories")), ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "slot_expansion"));

        // 精錬レシピ
        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(Items.ENCHANTED_BOOK),
                RecipeCategory.MISC,
                AEItems.ENCHANTMENT_SHARD,
                0.0f,
                400
                )
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, "enchantment_shard_smelting");


        // 素材
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AEItems.ARCANIUM_INGOT)
                .pattern("323").pattern("414").pattern("323")
                .define('1', AEItems.ENCHANTMENT_SHARD)
                .define('2', Items.GOLD_INGOT)
                .define('3', Items.IRON_INGOT)
                .define('4', Items.LAPIS_LAZULI)
                .unlockedBy("has_enchantment_shard", has(AEItems.ENCHANTMENT_SHARD))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "arcanium_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AEItems.SHARD_EMBEDDED_ARCANIUM_INGOT)
                .pattern("323").pattern("414").pattern("323")
                .define('1', AEItems.ARCANIUM_INGOT)
                .define('2', AEItems.ENCHANTMENT_SHARD)
                .define('3', Items.DIAMOND)
                .define('4', Items.AMETHYST_SHARD)
                .unlockedBy("has_arcanium_ingot", has(AEItems.ARCANIUM_INGOT))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "shard_embedded_arcanium_ingot"));
    }
}
