package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.scratch221171.astralenchant.common.AstralEnchant;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(ModEnchantments.MITIGATION_PIERCING, 1, holderLookup))
                .pattern("232").pattern("515").pattern("454")
                .define('1', EnchantedBookIngredientWith(Enchantments.BREACH, 4, holderLookup))
                .define('2', Items.DIAMOND)
                .define('3', Items.NETHER_STAR)
                .define('4', Items.NETHERITE_SCRAP)
                .define('5', Items.OBSIDIAN)
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "mitigation_piercing"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(ModEnchantments.LAST_STAND, 1, holderLookup))
                .pattern("232").pattern("313").pattern("232")
                .define('1', EnchantedBookIngredientWith(Enchantments.PROTECTION, 4, holderLookup))
                .define('2', Items.EXPERIENCE_BOTTLE)
                .define('3', Items.TOTEM_OF_UNDYING)
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "last_stand"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(ModEnchantments.ITEM_PROTECTION, 1, holderLookup))
                .pattern("323").pattern("212").pattern("323")
                .define('1', EnchantedBookIngredientWith(Enchantments.MENDING, 1, holderLookup))
                .define('2', Items.ENDER_CHEST)
                .define('3', Items.GOLD_INGOT)
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "item_protection"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(ModEnchantments.ESSENCE_OF_ENCHANTMENT, 1, holderLookup))
                .pattern("333").pattern("323").pattern("414")
                .define('1', Items.BOOK)
                .define('2', Items.ENCHANTING_TABLE)
                .define('3', Items.BOOKSHELF)
                .define('4', Items.LAPIS_BLOCK)
                .unlockedBy("has_book", has(Items.BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "essence_of_enchantment"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(ModEnchantments.COOLDOWN_REDUCTION, 1, holderLookup))
                .pattern(" 3 ").pattern("412").pattern(" 5 ")
                .define('1', EnchantedBookIngredientWith(Enchantments.EFFICIENCY, 5, holderLookup))
                .define('2', Items.CHORUS_FRUIT)
                .define('3', Items.ENDER_PEARL)
                .define('4', Items.SHIELD)
                .define('5', Items.WIND_CHARGE)
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "cooldown_reduction"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(ModEnchantments.FEATHER_TOUCH, 1, holderLookup))
                .pattern("515").pattern("623").pattern("545")
                .define('1', Items.AMETHYST_SHARD)
                .define('2', EnchantedBookIngredientWith(Enchantments.SILK_TOUCH, 1, holderLookup))
                .define('3', Items.DEEPSLATE)
                .define('4', Items.ECHO_SHARD)
                .define('5', Items.FEATHER)
                .define('6', Items.MOSSY_COBBLESTONE)
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "feather_touch"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(ModEnchantments.ADVENTURERS_LORE, 1, holderLookup))
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(ModEnchantments.COMPATIBILITY, 1, holderLookup))
                .pattern("313").pattern("121").pattern("313")
                .define('1', Items.ANVIL)
                .define('2', Items.BOOK)
                .define('3', Items.GOLD_INGOT)
                .unlockedBy("has_book", has(Items.BOOK))
                .save(output, ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "compatible"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedBookItemWith(ModEnchantments.ENDLESS_APPETITE, 1, holderLookup))
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
    }
}
