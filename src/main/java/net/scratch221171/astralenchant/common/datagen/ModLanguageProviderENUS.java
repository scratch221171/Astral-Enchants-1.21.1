package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.registries.ModAttributes;

public class ModLanguageProviderENUS extends LanguageProvider {
    public ModLanguageProviderENUS(PackOutput output) {
        super(output, AstralEnchant.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // エンチャント
        add(ModEnchantments.MITIGATION_PIERCING.location().toLanguageKey("enchantment"), "Mitigation Piercing");
        add(ModEnchantments.MITIGATION_PIERCING.location().toLanguageKey("enchantment", "desc"), "Attacks with this item will penetrate various damage reductions.");
        add(ModEnchantments.LAST_STAND.location().toLanguageKey("enchantment"), "Last Stand");
        add(ModEnchantments.LAST_STAND.location().toLanguageKey("enchantment", "desc"), "Consumes experience points to avoid death.");
        add(ModEnchantments.ITEM_PROTECTION.location().toLanguageKey("enchantment"), "Item Protection");
        add(ModEnchantments.ITEM_PROTECTION.location().toLanguageKey("enchantment", "desc"), "Protects the item's components. Note: Durability is not protected.");
        add(ModEnchantments.ESSENCE_OF_ENCHANTMENT.location().toLanguageKey("enchantment"), "Essence of Enchantment");
        add(ModEnchantments.ESSENCE_OF_ENCHANTMENT.location().toLanguageKey("enchantment", "desc"), "All stats of the item increase based on the total level of all other enchantments besides this one.");
        add(ModEnchantments.COOLDOWN_REDUCTION.location().toLanguageKey("enchantment"), "Cooldown Reduction");
        add(ModEnchantments.COOLDOWN_REDUCTION.location().toLanguageKey("enchantment", "desc"), "Reduces the cooldown for all items, such as Shields and Ender Pearls.");
        add(ModEnchantments.FEATHER_TOUCH.location().toLanguageKey("enchantment"), "Feather Touch");
        add(ModEnchantments.FEATHER_TOUCH.location().toLanguageKey("enchantment", "desc"), "Allows you to collect all destructible blocks. Components are also saved.");
        add(ModEnchantments.ADVENTURERS_LORE.location().toLanguageKey("enchantment"), "Adventurer’s Lore");
        add(ModEnchantments.ADVENTURERS_LORE.location().toLanguageKey("enchantment", "desc"), "Increases your luck based on the number of completed achievements.");
        add(ModEnchantments.COMPATIBILITY.location().toLanguageKey("enchantment"), "Compatibility");
        add(ModEnchantments.COMPATIBILITY.location().toLanguageKey("enchantment", "desc"), "Instead of bundles, enchantments will be applied to internal items, ignoring conflicts.");
        add(ModEnchantments.ENDLESS_APPETITE.location().toLanguageKey("enchantment"), "Endless Appetite");
        add(ModEnchantments.ENDLESS_APPETITE.location().toLanguageKey("enchantment", "desc"), "Enhances natural regeneration and directly restores an amount equal to the excess over the cap of food level and hidden saturation level.");
        add(ModEnchantments.MOMENTUM.location().toLanguageKey("enchantment"), "Momentum");
        add(ModEnchantments.MOMENTUM.location().toLanguageKey("enchantment", "desc"), "Disables speed reductions caused by item usage or blocks.");
        add(ModEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment"), "Instant Teleport");
        add(ModEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "desc"), "When using an Ender Pearl, teleport to the location player is looking at. If sneaking, teleports to the other side of the blocks.");
        add(ModEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "message.fail"), "No valid block exists within range!");

        // attribute
        add(ModAttributes.COOLDOWN_REDUCTION.unwrapKey().orElseThrow().location().toLanguageKey("attribute"), "Cooldown Reduction");

        //　設定画面
        add(AstralEnchant.MOD_ID + ".configuration.enchantment_toggling", "Enchantment Toggling");
        add(AstralEnchant.MOD_ID + ".configuration." + ModEnchantments.MITIGATION_PIERCING.location().getPath(), "Mitigation Piercing");
        add(AstralEnchant.MOD_ID + ".configuration." + ModEnchantments.LAST_STAND.location().getPath(), "Last Stand");
        add(AstralEnchant.MOD_ID + ".configuration." + ModEnchantments.ITEM_PROTECTION.location().getPath(), "Item Protection");
        add(AstralEnchant.MOD_ID + ".configuration." + ModEnchantments.ESSENCE_OF_ENCHANTMENT.location().getPath(), "Essence of Enchantment");
        add(AstralEnchant.MOD_ID + ".configuration." + ModEnchantments.COOLDOWN_REDUCTION.location().getPath(), "Cooldown Reduction");
        add(AstralEnchant.MOD_ID + ".configuration." + ModEnchantments.FEATHER_TOUCH.location().getPath(), "Feather Touch");
        add(AstralEnchant.MOD_ID + ".configuration." + ModEnchantments.ADVENTURERS_LORE.location().getPath(), "Adventurer’s Lore");
        add(AstralEnchant.MOD_ID + ".configuration." + ModEnchantments.COMPATIBILITY.location().getPath(), "Compatibility");
        add(AstralEnchant.MOD_ID + ".configuration." + ModEnchantments.ENDLESS_APPETITE.location().getPath(), "Endless Appetite");
        add(AstralEnchant.MOD_ID + ".configuration." + ModEnchantments.MOMENTUM.location().getPath(), "Momentum");
        add(AstralEnchant.MOD_ID + ".configuration." + ModEnchantments.INSTANT_TELEPORT.location().getPath(), "Instant Teleport");
    }
}