package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.registries.AEAttributes;
import net.scratch221171.astralenchant.common.registries.AEBlocks;
import net.scratch221171.astralenchant.common.registries.AEItems;

public class AELanguageProviderENUS extends LanguageProvider {
    public AELanguageProviderENUS(PackOutput output) {
        super(output, AstralEnchant.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // エンチャント
        add(AEEnchantments.MITIGATION_PIERCING.location().toLanguageKey("enchantment"), "Mitigation Piercing");
        add(AEEnchantments.MITIGATION_PIERCING.location().toLanguageKey("enchantment", "desc"), "Attacks penetrate various forms of damage mitigation.");
        add(AEEnchantments.LAST_STAND.location().toLanguageKey("enchantment"), "Last Stand");
        add(AEEnchantments.LAST_STAND.location().toLanguageKey("enchantment", "desc"), "Consumes experience points to avoid death.");
        add(AEEnchantments.ITEM_PROTECTION.location().toLanguageKey("enchantment"), "Item Protection");
        add(AEEnchantments.ITEM_PROTECTION.location().toLanguageKey("enchantment", "desc"), "Dropped items will never disappear by any means. As a trade-off, the enchantments on the item can no longer be modified.");
        add(AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().toLanguageKey("enchantment"), "Essence of Enchantment");
        add(AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().toLanguageKey("enchantment", "desc"), "All item stats increase based on the total level of all enchantments other than this one.");
        add(AEEnchantments.COOLDOWN_REDUCTION.location().toLanguageKey("enchantment"), "Cooldown Reduction");
        add(AEEnchantments.COOLDOWN_REDUCTION.location().toLanguageKey("enchantment", "desc"), "Reduces the cooldown of all items, including shields and Ender Pearls.");
        add(AEEnchantments.FEATHER_TOUCH.location().toLanguageKey("enchantment"), "Feather Touch");
        add(AEEnchantments.FEATHER_TOUCH.location().toLanguageKey("enchantment", "desc"), "All breakable blocks are collected as items. While sneaking, block states and components are preserved.");
        add(AEEnchantments.ADVENTURERS_LORE.location().toLanguageKey("enchantment"), "Adventurer's Lore");
        add(AEEnchantments.ADVENTURERS_LORE.location().toLanguageKey("enchantment", "desc"), "Increases luck and the amount of experience gained from mobs and blocks based on the number of completed advancements.");
        add(AEEnchantments.COMPATIBILITY.location().toLanguageKey("enchantment"), "Compatibility");
        add(AEEnchantments.COMPATIBILITY.location().toLanguageKey("enchantment", "desc"), "Allows additional enchantments to be applied to internal items of a bundle with this enchantment while ignoring conflicts.");
        add(AEEnchantments.ENDLESS_APPETITE.location().toLanguageKey("enchantment"), "Endless Appetite");
        add(AEEnchantments.ENDLESS_APPETITE.location().toLanguageKey("enchantment", "desc"), "Enhances natural regeneration and directly heals based on the amount of excess hunger and hidden saturation beyond their caps.");
        add(AEEnchantments.MOMENTUM.location().toLanguageKey("enchantment"), "Momentum");
        add(AEEnchantments.MOMENTUM.location().toLanguageKey("enchantment", "desc"), "Negates movement speed reductions caused by item use or blocks.");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment"), "Instant Teleport");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "desc"), "When using an Ender Pearl, teleports you to the point you are looking at. While sneaking, the teleport ray passes through a block and sends you to the opposite side of it.");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "message.fail"), "No valid block exists within range!");
        add(AEEnchantments.OVERLOAD.location().toLanguageKey("enchantment"), "Overload");
        add(AEEnchantments.OVERLOAD.location().toLanguageKey("enchantment", "desc"), "Grants Overload to the item, increasing the level of existing enchantments by the Overload value.");
        add(AEEnchantments.OVERLOAD.location().toLanguageKey("enchantment", "tooltip.text"), "Overload: %s Levels");
        add(AEEnchantments.SLOT_EXPANSION.location().toLanguageKey("enchantment"), "Slot Expansion");
        add(AEEnchantments.SLOT_EXPANSION.location().toLanguageKey("enchantment", "desc"), "Increases the number of accessory slots the item currently occupies by the enchantment level.");
        add(AEEnchantments.REACTIVE_ARMOR.location().toLanguageKey("enchantment"), "Reactive Armor");
        add(AEEnchantments.REACTIVE_ARMOR.location().toLanguageKey("enchantment", "desc"), "Negates effects that bypass armor and enchantments for certain types of damage.");

        // attribute
        add(AEAttributes.COOLDOWN_REDUCTION.unwrapKey().orElseThrow().location().toLanguageKey("attribute"), "Cooldown Reduction");

        // アイテム
        add(AEItems.ENCHANTMENT_SHARD.getKey().location().toLanguageKey("item"), "Enchantment Shard");
        add(AEItems.ARCANE_QUARTZ.getKey().location().toLanguageKey("item"), "Arcane Quartz");
        add(AEItems.ARCANIUM_INGOT.getKey().location().toLanguageKey("item"), "Arcanium Ingot");
        add(AEItems.BUDDING_ARCANIUM_INGOT.getKey().location().toLanguageKey("item"), "Budding Arcanium Ingot");

        // ブロック
        add(AEBlocks.ARCANIUM_BLOCK.getKey().location().toLanguageKey("block"), "Arcanium Block");

        //　設定画面
        add(AstralEnchant.MOD_ID + ".configuration.title", "Config");
        add(AstralEnchant.MOD_ID + ".configuration.section.astralenchant.server.toml.title", "Astral Enchant Server Config");

        add(AstralEnchant.MOD_ID + ".configuration.settings", "Enchantment Settings");
        add(AstralEnchant.MOD_ID + ".configuration.last_stand_required_base_experience", "Base experience points required for Last Stand");
        add(AstralEnchant.MOD_ID + ".configuration.last_stand_required_base_experience.tooltip", "The cost for Last Stand at level N is (base value / N). By default, this is approximately equivalent to 34 levels.");
        add(AstralEnchant.MOD_ID + ".configuration.last_stand_ignore_bypasses_invulnerability_tag", "Last Stand ignores invulnerability-bypassing damage");
        add(AstralEnchant.MOD_ID + ".configuration.last_stand_ignore_bypasses_invulnerability_tag.tooltip", "When this setting is disabled, revival is not possible if the damage source has the invulnerability-bypassing tag (such as /kill or the void).");
        add(AstralEnchant.MOD_ID + ".configuration.essence_of_enchant_include_overload_in_calculation", "Include Overload levels in the total level calculation for Essence of Enchanting");
        add(AstralEnchant.MOD_ID + ".configuration.essence_of_enchant_include_overload_in_calculation.tooltip", "Regardless of this setting, it is always applied to the level of Essence of Enchanting itself.");
        add(AstralEnchant.MOD_ID + ".configuration.essence_of_enchant_level_multiplier", "Essence of Enchant level multiplier (%)");
        add(AstralEnchant.MOD_ID + ".configuration.essence_of_enchant_level_multiplier.tooltip", "Specifies how many percent the modifier multiplier increases for each additional total enchantment level on the item.");

        add(AstralEnchant.MOD_ID + ".configuration.enchantment_toggling", "Enchantment Toggling");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.MITIGATION_PIERCING.location().getPath(), "Mitigation Piercing");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.LAST_STAND.location().getPath(), "Last Stand");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.ITEM_PROTECTION.location().getPath(), "Item Protection");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().getPath(), "Essence of Enchantment");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.COOLDOWN_REDUCTION.location().getPath(), "Cooldown Reduction");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.FEATHER_TOUCH.location().getPath(), "Feather Touch");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.ADVENTURERS_LORE.location().getPath(), "Adventurer’s Lore");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.COMPATIBILITY.location().getPath(), "Compatibility");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.ENDLESS_APPETITE.location().getPath(), "Endless Appetite");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.MOMENTUM.location().getPath(), "Momentum");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.INSTANT_TELEPORT.location().getPath(), "Instant Teleport");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.OVERLOAD.location().getPath(), "Overload");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.SLOT_EXPANSION.location().getPath(), "Slot Expansion");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.REACTIVE_ARMOR.location().getPath(), "Reactive Armor");
    }
}