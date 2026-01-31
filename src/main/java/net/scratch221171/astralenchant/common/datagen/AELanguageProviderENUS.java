package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.registries.AEAttributes;

public class AELanguageProviderENUS extends LanguageProvider {
    public AELanguageProviderENUS(PackOutput output) {
        super(output, AstralEnchant.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // エンチャント
        add(AEEnchantments.MITIGATION_PIERCING.location().toLanguageKey("enchantment"), "Mitigation Piercing");
        add(AEEnchantments.MITIGATION_PIERCING.location().toLanguageKey("enchantment", "desc"), "Attacks with this item will penetrate various damage reductions.");
        add(AEEnchantments.LAST_STAND.location().toLanguageKey("enchantment"), "Last Stand");
        add(AEEnchantments.LAST_STAND.location().toLanguageKey("enchantment", "desc"), "Consumes experience points to avoid death.");
        add(AEEnchantments.ITEM_PROTECTION.location().toLanguageKey("enchantment"), "Item Protection");
        add(AEEnchantments.ITEM_PROTECTION.location().toLanguageKey("enchantment", "desc"), "Protects the item's components. Note: Durability is not protected.");
        add(AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().toLanguageKey("enchantment"), "Essence of Enchantment");
        add(AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().toLanguageKey("enchantment", "desc"), "All stats of the item increase based on the total level of all other enchantments besides this one.");
        add(AEEnchantments.COOLDOWN_REDUCTION.location().toLanguageKey("enchantment"), "Cooldown Reduction");
        add(AEEnchantments.COOLDOWN_REDUCTION.location().toLanguageKey("enchantment", "desc"), "Reduces the cooldown for all items, such as Shields and Ender Pearls.");
        add(AEEnchantments.FEATHER_TOUCH.location().toLanguageKey("enchantment"), "Feather Touch");
        add(AEEnchantments.FEATHER_TOUCH.location().toLanguageKey("enchantment", "desc"), "Allows you to collect all destructible blocks. Components are also saved.");
        add(AEEnchantments.ADVENTURERS_LORE.location().toLanguageKey("enchantment"), "Adventurer’s Lore");
        add(AEEnchantments.ADVENTURERS_LORE.location().toLanguageKey("enchantment", "desc"), "Increases your luck based on the number of completed achievements.");
        add(AEEnchantments.COMPATIBILITY.location().toLanguageKey("enchantment"), "Compatibility");
        add(AEEnchantments.COMPATIBILITY.location().toLanguageKey("enchantment", "desc"), "Instead of bundles, enchantments will be applied to internal items, ignoring conflicts.");
        add(AEEnchantments.ENDLESS_APPETITE.location().toLanguageKey("enchantment"), "Endless Appetite");
        add(AEEnchantments.ENDLESS_APPETITE.location().toLanguageKey("enchantment", "desc"), "Enhances natural regeneration and directly restores an amount equal to the excess over the cap of food level and hidden saturation level.");
        add(AEEnchantments.MOMENTUM.location().toLanguageKey("enchantment"), "Momentum");
        add(AEEnchantments.MOMENTUM.location().toLanguageKey("enchantment", "desc"), "Disables speed reductions caused by item usage or blocks.");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment"), "Instant Teleport");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "desc"), "When using an Ender Pearl, teleport to the location player is looking at. If sneaking, teleports to the other side of the blocks.");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "message.fail"), "No valid block exists within range!");
        add(AEEnchantments.OVERLOAD.location().toLanguageKey("enchantment"), "Overload");
        add(AEEnchantments.OVERLOAD.location().toLanguageKey("enchantment", "desc"), "Item gains Overload, increasing the level of existing enchantments by the Overload value.");
        add(AEEnchantments.OVERLOAD.location().toLanguageKey("enchantment", "tooltip.text"), "Overload: %s level(s)");

        // attribute
        add(AEAttributes.COOLDOWN_REDUCTION.unwrapKey().orElseThrow().location().toLanguageKey("attribute"), "Cooldown Reduction");

        //　設定画面
        add(AstralEnchant.MOD_ID + ".configuration.enchantment_toggling", "Enchantment Toggling");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.MITIGATION_PIERCING.location().getPath(), "Enable Mitigation Piercing");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.LAST_STAND.location().getPath(), "Enable Last Stand");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.ITEM_PROTECTION.location().getPath(), "Enable Item Protection");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().getPath(), "Enable Essence of Enchantment");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.COOLDOWN_REDUCTION.location().getPath(), "Enable Cooldown Reduction");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.FEATHER_TOUCH.location().getPath(), "Enable Feather Touch");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.ADVENTURERS_LORE.location().getPath(), "Enable Adventurer’s Lore");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.COMPATIBILITY.location().getPath(), "Enable Compatibility");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.ENDLESS_APPETITE.location().getPath(), "Enable Endless Appetite");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.MOMENTUM.location().getPath(), "Enable Momentum");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.INSTANT_TELEPORT.location().getPath(), "Enable Instant Teleport");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.OVERLOAD.location().getPath(), "Enable Overload");
    }
}