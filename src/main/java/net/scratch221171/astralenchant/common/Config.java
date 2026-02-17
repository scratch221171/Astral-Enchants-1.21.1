package net.scratch221171.astralenchant.common;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;

import java.util.HashMap;
import java.util.Map;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final Map<String, ModConfigSpec.BooleanValue> TOGGLING_CONFIG_DICT = new HashMap<>();
    public static final Map<ResourceKey<Enchantment>, ModConfigSpec.BooleanValue> ENCHANTMENT_CONFIG_DICT = new HashMap<>();

    public static final ModConfigSpec.BooleanValue ENABLE_VANILLA_ITEM_RECIPES;
    public static final ModConfigSpec.IntValue LAST_STAND_REQUIRED_BASE_EXPERIENCE;
    public static final ModConfigSpec.BooleanValue LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG;
    public static final ModConfigSpec.BooleanValue ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION;
    public static final ModConfigSpec.DoubleValue ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER;

    public static final ModConfigSpec.BooleanValue MITIGATION_PIERCING;
    public static final ModConfigSpec.BooleanValue LAST_STAND;
    public static final ModConfigSpec.BooleanValue ITEM_PROTECTION;
    public static final ModConfigSpec.BooleanValue ESSENCE_OF_ENCHANTMENT;
    public static final ModConfigSpec.BooleanValue COOLDOWN_REDUCTION;
    public static final ModConfigSpec.BooleanValue FEATHER_TOUCH;
    public static final ModConfigSpec.BooleanValue ADVENTURERS_LORE;
    public static final ModConfigSpec.BooleanValue COMPATIBILITY;
    public static final ModConfigSpec.BooleanValue ENDLESS_APPETITE;
    public static final ModConfigSpec.BooleanValue MOMENTUM;
    public static final ModConfigSpec.BooleanValue INSTANT_TELEPORT;
    public static final ModConfigSpec.BooleanValue OVERLOAD;
    public static final ModConfigSpec.BooleanValue SLOT_EXPANSION;
    public static final ModConfigSpec.BooleanValue REACTIVE_ARMOR;

    static {
        BUILDER.push("settings");
        ENABLE_VANILLA_ITEM_RECIPES = registerTogglingConfig("enable_vanilla_items_recipes");
        LAST_STAND_REQUIRED_BASE_EXPERIENCE = BUILDER.defineInRange("last_stand_required_base_experience", 2000, 0, Integer.MAX_VALUE);
        LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG = BUILDER.define("last_stand_ignore_bypasses_invulnerability_tag", true);
        ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION = BUILDER.define("essence_of_enchant_include_overload_in_calculation", false);
        ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER = BUILDER.defineInRange("essence_of_enchant_level_multiplier", 1, 0, Double.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("enchantment_toggling");
        MITIGATION_PIERCING = registerEnchantmentTogglingConfig(AEEnchantments.MITIGATION_PIERCING);
        LAST_STAND = registerEnchantmentTogglingConfig(AEEnchantments.LAST_STAND);
        ITEM_PROTECTION = registerEnchantmentTogglingConfig(AEEnchantments.ITEM_PROTECTION);
        ESSENCE_OF_ENCHANTMENT = registerEnchantmentTogglingConfig(AEEnchantments.ESSENCE_OF_ENCHANTMENT);
        COOLDOWN_REDUCTION = registerEnchantmentTogglingConfig(AEEnchantments.COOLDOWN_REDUCTION);
        FEATHER_TOUCH = registerEnchantmentTogglingConfig(AEEnchantments.FEATHER_TOUCH);
        ADVENTURERS_LORE = registerEnchantmentTogglingConfig(AEEnchantments.ADVENTURERS_LORE);
        COMPATIBILITY = registerEnchantmentTogglingConfig(AEEnchantments.COMPATIBILITY);
        ENDLESS_APPETITE = registerEnchantmentTogglingConfig(AEEnchantments.ENDLESS_APPETITE);
        MOMENTUM = registerEnchantmentTogglingConfig(AEEnchantments.MOMENTUM);
        INSTANT_TELEPORT = registerEnchantmentTogglingConfig(AEEnchantments.INSTANT_TELEPORT);
        OVERLOAD = registerEnchantmentTogglingConfig(AEEnchantments.OVERLOAD);
        SLOT_EXPANSION = registerEnchantmentTogglingConfig(AEEnchantments.SLOT_EXPANSION);
        REACTIVE_ARMOR = registerEnchantmentTogglingConfig(AEEnchantments.REACTIVE_ARMOR);
        BUILDER.pop();
    }

    private static ModConfigSpec.BooleanValue registerEnchantmentTogglingConfig(ResourceKey<Enchantment> enchantment) {
        ModConfigSpec.BooleanValue value = registerTogglingConfig(enchantment.location().getPath(), true);
        ENCHANTMENT_CONFIG_DICT.put(enchantment, value);
        return value;
    }

    private static ModConfigSpec.BooleanValue registerTogglingConfig(String path) {
        return registerTogglingConfig(path, true);
    }

    private static ModConfigSpec.BooleanValue registerTogglingConfig(String path, boolean defaultValue) {
        ModConfigSpec.BooleanValue value = BUILDER.define(path, defaultValue);
        TOGGLING_CONFIG_DICT.put(path, value);
        return value;
    }

    static final ModConfigSpec CONFIG_SPEC = BUILDER.build();
}
