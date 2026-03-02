package net.scratch221171.astralenchant.common;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final Map<String, ModConfigSpec.BooleanValue> TOGGLING_CONFIG_DICT = new HashMap<>();

    public static final ModConfigSpec.ConfigValue<List<? extends String>> MITIGATION_PIERCING_ADDED_DAMAGE_TYPE_TAGS;
    public static final ModConfigSpec.BooleanValue ENABLE_VANILLA_ITEM_RECIPES;
    public static final ModConfigSpec.IntValue LAST_STAND_REQUIRED_BASE_EXPERIENCE;
    public static final ModConfigSpec.BooleanValue LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG;
    public static final ModConfigSpec.BooleanValue ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION;
    public static final ModConfigSpec.DoubleValue ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER;
    public static final ModConfigSpec.IntValue INSTANT_TELEPORT_DISTANCE_INCREASE_PER_LEVEL;
    public static final ModConfigSpec.IntValue INSTANT_TELEPORT_MAX_DISTANCE;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> REACTIVE_ARMOR_DISABLED_DAMAGE_TYPE_TAGS;

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
    public static final ModConfigSpec.BooleanValue MYSTIC_REMNANTS;
    public static final ModConfigSpec.BooleanValue CURSE_OF_IGNORANCE;

    static {
        BUILDER.push("settings");
        MITIGATION_PIERCING_ADDED_DAMAGE_TYPE_TAGS = BUILDER.defineListAllowEmpty(
                "mitigation_piercing_added_damage_type_tags",
                () -> List.of(
                        DamageTypeTags.BYPASSES_ARMOR.location().toString(),
                        DamageTypeTags.BYPASSES_COOLDOWN.location().toString(),
                        DamageTypeTags.BYPASSES_EFFECTS.location().toString(),
                        DamageTypeTags.BYPASSES_ENCHANTMENTS.location().toString(),
                        DamageTypeTags.BYPASSES_INVULNERABILITY.location().toString(),
                        DamageTypeTags.BYPASSES_SHIELD.location().toString()
                ),
                () -> DamageTypeTags.BYPASSES_ARMOR.location().toString(),
                obj -> obj instanceof String s && s.contains(":")
        );
        LAST_STAND_REQUIRED_BASE_EXPERIENCE = BUILDER.defineInRange("last_stand_required_base_experience", 2000, 0, Integer.MAX_VALUE);
        LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG = BUILDER.define("last_stand_ignore_bypasses_invulnerability_tag", true);
        ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION = BUILDER.define("essence_of_enchant_include_overload_in_calculation", false);
        ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER = BUILDER.defineInRange("essence_of_enchant_level_multiplier", 1, 0, Double.MAX_VALUE);
        INSTANT_TELEPORT_DISTANCE_INCREASE_PER_LEVEL = BUILDER.defineInRange("instant_teleport_distance_increase_per_level", 32, 0, Integer.MAX_VALUE);
        INSTANT_TELEPORT_MAX_DISTANCE = BUILDER.defineInRange("instant_teleport_max_distance", 256, 0, Integer.MAX_VALUE);
        REACTIVE_ARMOR_DISABLED_DAMAGE_TYPE_TAGS = BUILDER.defineListAllowEmpty(
                "reactive_armor_disabled_damage_type_tags",
                () -> List.of(
                        DamageTypeTags.BYPASSES_ARMOR.location().toString(),
                        DamageTypeTags.BYPASSES_ENCHANTMENTS.location().toString()
                ),
                () -> DamageTypeTags.BYPASSES_ARMOR.location().toString(),
                obj -> obj instanceof String s && s.contains(":")
        );
        BUILDER.pop();

        BUILDER.push("enchantment_toggling");
        MITIGATION_PIERCING = registerTogglingConfig(AEEnchantments.MITIGATION_PIERCING);
        LAST_STAND = registerTogglingConfig(AEEnchantments.LAST_STAND);
        ITEM_PROTECTION = registerTogglingConfig(AEEnchantments.ITEM_PROTECTION);
        ESSENCE_OF_ENCHANTMENT = registerTogglingConfig(AEEnchantments.ESSENCE_OF_ENCHANTMENT);
        COOLDOWN_REDUCTION = registerTogglingConfig(AEEnchantments.COOLDOWN_REDUCTION);
        FEATHER_TOUCH = registerTogglingConfig(AEEnchantments.FEATHER_TOUCH);
        ADVENTURERS_LORE = registerTogglingConfig(AEEnchantments.ADVENTURERS_LORE);
        COMPATIBILITY = registerTogglingConfig(AEEnchantments.COMPATIBILITY);
        ENDLESS_APPETITE = registerTogglingConfig(AEEnchantments.ENDLESS_APPETITE);
        MOMENTUM = registerTogglingConfig(AEEnchantments.MOMENTUM);
        INSTANT_TELEPORT = registerTogglingConfig(AEEnchantments.INSTANT_TELEPORT);
        OVERLOAD = registerTogglingConfig(AEEnchantments.OVERLOAD);
        SLOT_EXPANSION = registerTogglingConfig(AEEnchantments.SLOT_EXPANSION);
        REACTIVE_ARMOR = registerTogglingConfig(AEEnchantments.REACTIVE_ARMOR);
        MYSTIC_REMNANTS = registerTogglingConfig(AEEnchantments.MYSTIC_REMNANTS);
        CURSE_OF_IGNORANCE = registerTogglingConfig(AEEnchantments.CURSE_OF_IGNORANCE);
        BUILDER.pop();

        ENABLE_VANILLA_ITEM_RECIPES = registerTogglingConfig("enable_vanilla_items_recipes");
    }

    private static ModConfigSpec.BooleanValue registerTogglingConfig(ResourceKey<Enchantment> enchantment) {
        return registerTogglingConfig(enchantment.location().getPath(), true);
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
