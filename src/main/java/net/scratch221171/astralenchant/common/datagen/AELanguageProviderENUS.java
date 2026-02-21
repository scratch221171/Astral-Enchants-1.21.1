package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
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
        add(AstralEnchant.MOD_ID + ".enchantment.disabled", "[Disabled]");

        addEnchantWithDesc(AEEnchantments.MITIGATION_PIERCING, "Mitigation Piercing", "Attacks penetrate various forms of damage mitigation.");
        addEnchantWithDesc(AEEnchantments.LAST_STAND, "Last Stand", "Consumes experience points to avoid death.");
        addEnchantWithDesc(AEEnchantments.ITEM_PROTECTION, "Item Protection", "Dropped items will never disappear by any means. As a trade-off, the enchantments on the item can no longer be modified.");
        addEnchantWithDesc(AEEnchantments.ESSENCE_OF_ENCHANTMENT, "Essence of Enchantment", "All item stats increase based on the total level of all enchantments other than this one.");
        addEnchantWithDesc(AEEnchantments.COOLDOWN_REDUCTION, "Cooldown Reduction", "Reduces the cooldown of all items, including shields and Ender Pearls.");
        addEnchantWithDesc(AEEnchantments.FEATHER_TOUCH, "Feather Touch", "All breakable blocks are collected as items. While sneaking, block states and components are preserved.");
        addEnchantWithDesc(AEEnchantments.ADVENTURERS_LORE, "Adventurer's Lore", "Increases luck and the amount of experience gained from mobs and blocks based on the number of completed advancements.");
        addEnchantWithDesc(AEEnchantments.COMPATIBILITY, "Compatibility", "Allows additional enchantments to be applied to internal items of a bundle with this enchantment while ignoring conflicts.");
        addEnchantWithDesc(AEEnchantments.ENDLESS_APPETITE, "Endless Appetite", "Enhances natural regeneration and directly heals based on the amount of excess hunger and hidden saturation beyond their caps.");
        addEnchantWithDesc(AEEnchantments.MOMENTUM, "Momentum", "Negates movement speed reductions caused by item use or blocks.");
        addEnchantWithDesc(AEEnchantments.INSTANT_TELEPORT, "Instant Teleport", "When using an Ender Pearl, teleports you to the point you are looking at. While sneaking, the teleport ray passes through a block and sends you to the opposite side of it.");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "message.fail"), "No valid block exists within range!");
        addEnchantWithDesc(AEEnchantments.OVERLOAD, "Overload", "Grants Overload to the item, increasing the level of existing enchantments by the Overload value.");
        add(AEEnchantments.OVERLOAD.location().toLanguageKey("enchantment", "tooltip.text"), "Overload: %s Levels");
        addEnchantWithDesc(AEEnchantments.SLOT_EXPANSION, "Slot Expansion", "Increases the number of accessory slots the item currently occupies by the enchantment level.");
        addEnchantWithDesc(AEEnchantments.REACTIVE_ARMOR, "Reactive Armor", "Negates effects that bypass armor and enchantments for certain types of damage.");
        addEnchantWithDesc(AEEnchantments.MYSTIC_REMNANTS, "Mystic Remnants", "Killed mobs will occasionally drop enchanted books.");
        addEnchantWithDesc(AEEnchantments.CURSE_OF_IGNORANCE, "Curse of Ignorance", "Replaces the tooltip with cursed text.");

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
        add(AstralEnchant.MOD_ID + ".configuration." + Config.LAST_STAND_REQUIRED_BASE_EXPERIENCE.getPath().getLast(), "Required experience points for Last Stand (base value)");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.LAST_STAND_REQUIRED_BASE_EXPERIENCE.getPath().getLast() + ".tooltip", "The experience cost of Last Stand at level N is \"base value ÷ N\". By default, this is approximately 34 levels.");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG.getPath().getLast(), "Last Stand ignores bypass-invulnerability");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG.getPath().getLast() + ".tooltip", "When disabled, Last Stand will not activate against damage that has the BYPASSES_INVULNERABILITY tag (such as /kill or void damage).");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION.getPath().getLast(), "Include Overload levels in Essence of Enchant total level calculation");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION.getPath().getLast() + ".tooltip", "Regardless of this setting, Overload levels are always applied to the level of Essence of Enchant itself.");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER.getPath().getLast(), "Essence of Enchant level multiplier (%)");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER.getPath().getLast() + ".tooltip", "Specifies how many percent the modifier multiplier increases for each increase of 1 in the total enchantment level.");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ENABLE_VANILLA_ITEM_RECIPES.getPath().getLast(), "Enable vanilla item recipes");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ENABLE_VANILLA_ITEM_RECIPES.getPath().getLast() + ".tooltip", "Enables recipes for vanilla items that may be required for game progression (such as bundles).");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.INSTANT_TELEPORT_DISTANCE_INCREASE_PER_LEVEL.getPath().getLast(), "Instant Teleport distance increase per level");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.INSTANT_TELEPORT_DISTANCE_INCREASE_PER_LEVEL.getPath().getLast() + ".tooltip", "Specifies how much the maximum teleportable distance increases for each level gained.");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.INSTANT_TELEPORT_MAX_DISTANCE.getPath().getLast(), "Instant Teleport maximum distance");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.INSTANT_TELEPORT_MAX_DISTANCE.getPath().getLast() + ".tooltip", "Specifies the maximum teleport distance provided by this enchantment.");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.MITIGATION_PIERCING_ADDED_DAMAGE_TYPE_TAGS.getPath().getLast(), "Damage type tags added by Mitigation Piercing");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.MITIGATION_PIERCING_ADDED_DAMAGE_TYPE_TAGS.getPath().getLast() + ".tooltip", "Configures the damage type tags added by Mitigation Piercing. These take priority over Reactive Armor.");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.REACTIVE_ARMOR_DISABLED_DAMAGE_TYPE_TAGS.getPath().getLast(), "Damage type tags disabled by Reactive Armor");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.REACTIVE_ARMOR_DISABLED_DAMAGE_TYPE_TAGS.getPath().getLast() + ".tooltip", "Configures the damage type tags that are disabled by Reactive Armor.");

        add(AstralEnchant.MOD_ID + ".configuration.enchantment_toggling", "Enchantment Toggling");
    }

    private void addEnchantWithDesc(ResourceKey<Enchantment> key, String name, String desc) {
        add(key.location().toLanguageKey("enchantment"), name);
        add(key.location().toLanguageKey("enchantment", "desc"), desc);
        add(AstralEnchant.MOD_ID + ".configuration." + Config.TOGGLING_CONFIG_DICT.get(key.location().getPath()).getPath().getLast(), name);
    }
}