package net.scratch221171.astralenchant.common;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;

import java.util.HashMap;
import java.util.Map;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final Map<String, String> BOOLEAN_PATH_DICT = new HashMap<>();
    private static String currentPath;

    public static final ModConfigSpec.BooleanValue ENABLE_VANILLA_ENCHANTED_BOOKS_RECIPE;
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
        push("settings");
        ENABLE_VANILLA_ENCHANTED_BOOKS_RECIPE = registerTogglingConfig("enable_vanilla_enchanted_books_recipes");
        LAST_STAND_REQUIRED_BASE_EXPERIENCE = BUILDER.defineInRange("last_stand_required_base_experience", 2000, 0, Integer.MAX_VALUE);
        LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG = BUILDER.define("last_stand_ignore_bypasses_invulnerability_tag", true);
        ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION = BUILDER.define("essence_of_enchant_include_overload_in_calculation", false);
        ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER = BUILDER.defineInRange("essence_of_enchant_level_multiplier", 1, 0, Double.MAX_VALUE);
        pop();

        push("enchantment_toggling");
        MITIGATION_PIERCING = registerTogglingConfig(AEEnchantments.MITIGATION_PIERCING.location().getPath());
        LAST_STAND = registerTogglingConfig(AEEnchantments.LAST_STAND.location().getPath());
        ITEM_PROTECTION = registerTogglingConfig(AEEnchantments.ITEM_PROTECTION.location().getPath());
        ESSENCE_OF_ENCHANTMENT = registerTogglingConfig(AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().getPath());
        COOLDOWN_REDUCTION = registerTogglingConfig(AEEnchantments.COOLDOWN_REDUCTION.location().getPath());
        FEATHER_TOUCH = registerTogglingConfig(AEEnchantments.FEATHER_TOUCH.location().getPath());
        ADVENTURERS_LORE = registerTogglingConfig(AEEnchantments.ADVENTURERS_LORE.location().getPath());
        COMPATIBILITY = registerTogglingConfig(AEEnchantments.COMPATIBILITY.location().getPath());
        ENDLESS_APPETITE = registerTogglingConfig(AEEnchantments.ENDLESS_APPETITE.location().getPath());
        MOMENTUM = registerTogglingConfig(AEEnchantments.MOMENTUM.location().getPath());
        INSTANT_TELEPORT = registerTogglingConfig(AEEnchantments.INSTANT_TELEPORT.location().getPath());
        OVERLOAD = registerTogglingConfig(AEEnchantments.OVERLOAD.location().getPath());
        SLOT_EXPANSION = registerTogglingConfig(AEEnchantments.SLOT_EXPANSION.location().getPath());
        REACTIVE_ARMOR = registerTogglingConfig(AEEnchantments.REACTIVE_ARMOR.location().getPath());
        pop();
    }

    private static void push(String path) {
        currentPath = path + ".";
        BUILDER.push(path);
    }

    private static void pop() {
        currentPath = "";
        BUILDER.pop();
    }

    private static ModConfigSpec.BooleanValue registerTogglingConfig(String path) {
        ModConfigSpec.BooleanValue value = BUILDER.define(path, true);
        BOOLEAN_PATH_DICT.put(path, currentPath + path);
        return value;
    }

    static final ModConfigSpec CONFIG_SPEC = BUILDER.build();
}
