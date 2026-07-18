package net.scratch221171.astralenchant.common.config;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.serialization.Codec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class AEConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final Map<ModConfigSpec.ConfigValue<?>, String> KEYS = new HashMap<>();

    public static String get(ModConfigSpec.ConfigValue<?> configValue) {
        return KEYS.get(configValue);
    }

    public static void put(ModConfigSpec.ConfigValue<?> configValue, String value) {
        KEYS.put(configValue, value);
    }

    public static final ModConfigSpec.IntValue LAST_STAND_REQUIRED_BASE_EXPERIENCE;
    public static final ModConfigSpec.BooleanValue LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG;
    public static final ModConfigSpec.BooleanValue ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION;
    public static final ModConfigSpec.DoubleValue ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER;
    public static final ModConfigSpec.IntValue INSTANT_TELEPORT_DISTANCE_INCREASE_PER_LEVEL;
    public static final ModConfigSpec.IntValue INSTANT_TELEPORT_MAX_DISTANCE;
    public static final ModConfigSpec.DoubleValue DISTORTION_ANGLE_PER_LEVEL;
    public static final ModConfigSpec.IntValue OVER_MENDING_TOTAL_EXPERIENCE_REQUIRED;
    public static final ModConfigSpec.DoubleValue OVER_MENDING_LIGHTNING_DAMAGE_MULTIPLIER;

    public static final ModConfigSpec.BooleanValue ENABLE_VANILLA_ITEM_RECIPES;

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
    public static final ModConfigSpec.BooleanValue CURSE_OF_ENCHANTMENT;
    public static final ModConfigSpec.BooleanValue DISTORTION;
    public static final ModConfigSpec.BooleanValue OVER_MENDING;

    static {
        BiMap<String, ModConfigSpec.BooleanValue> biMap = HashBiMap.create();
        BOOL_CONFIG_ENTRIES = biMap;
        BOOL_CONFIG_CODEC = Codec.stringResolver(value -> biMap.inverse().get(value), biMap::get);

        BUILDER.push("settings");

        LAST_STAND_REQUIRED_BASE_EXPERIENCE =
                registerInt("last_stand_required_base_experience", 2000, 0, Integer.MAX_VALUE);

        LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG =
                registerBool("last_stand_ignore_bypasses_invulnerability_tag", true);

        ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION =
                registerBool("essence_of_enchant_include_overload_in_calculation", false);

        ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER =
                registerDouble("essence_of_enchant_level_multiplier", 1, 0, Double.MAX_VALUE);

        INSTANT_TELEPORT_DISTANCE_INCREASE_PER_LEVEL =
                registerInt("instant_teleport_distance_increase_per_level", 32, 0, Integer.MAX_VALUE);

        INSTANT_TELEPORT_MAX_DISTANCE = registerInt("instant_teleport_max_distance", 256, 0, Integer.MAX_VALUE);

        DISTORTION_ANGLE_PER_LEVEL = registerDouble("distortion_angle_per_level", 7.5, 0, Double.MAX_VALUE);

        OVER_MENDING_TOTAL_EXPERIENCE_REQUIRED =
                registerInt("over_mending_total_experience_required", 100000, 0, Integer.MAX_VALUE);

        OVER_MENDING_LIGHTNING_DAMAGE_MULTIPLIER =
                registerDouble("over_mending_lightning_damage_multiplier", 16, 0, Double.MAX_VALUE);

        BUILDER.pop();

        BUILDER.push("misc");

        ENABLE_VANILLA_ITEM_RECIPES = registerBool("enable_vanilla_items_recipes", true);

        BUILDER.pop();

        BUILDER.push("enchantment_toggling");

        MITIGATION_PIERCING = registerEnchantment(AEEnchantments.MITIGATION_PIERCING);

        LAST_STAND = registerEnchantment(AEEnchantments.LAST_STAND);

        ITEM_PROTECTION = registerEnchantment(AEEnchantments.ITEM_PROTECTION);

        ESSENCE_OF_ENCHANTMENT = registerEnchantment(AEEnchantments.ESSENCE_OF_ENCHANTMENT);

        COOLDOWN_REDUCTION = registerEnchantment(AEEnchantments.COOLDOWN_REDUCTION);

        FEATHER_TOUCH = registerEnchantment(AEEnchantments.FEATHER_TOUCH);

        ADVENTURERS_LORE = registerEnchantment(AEEnchantments.ADVENTURERS_LORE);

        COMPATIBILITY = registerEnchantment(AEEnchantments.COMPATIBILITY);

        ENDLESS_APPETITE = registerEnchantment(AEEnchantments.ENDLESS_APPETITE);

        MOMENTUM = registerEnchantment(AEEnchantments.MOMENTUM);

        INSTANT_TELEPORT = registerEnchantment(AEEnchantments.INSTANT_TELEPORT);

        OVERLOAD = registerEnchantment(AEEnchantments.OVERLOAD);

        SLOT_EXPANSION = registerEnchantment(AEEnchantments.SLOT_EXPANSION);

        REACTIVE_ARMOR = registerEnchantment(AEEnchantments.REACTIVE_ARMOR);

        MYSTIC_REMNANTS = registerEnchantment(AEEnchantments.MYSTIC_REMNANTS);

        CURSE_OF_IGNORANCE = registerEnchantment(AEEnchantments.CURSE_OF_IGNORANCE);

        CURSE_OF_ENCHANTMENT = registerEnchantment(AEEnchantments.CURSE_OF_ENCHANTMENT);

        DISTORTION = registerEnchantment(AEEnchantments.DISTORTION);

        OVER_MENDING = registerEnchantment(AEEnchantments.OVER_MENDING);

        BUILDER.pop();
    }

    private static final BiMap<String, ModConfigSpec.BooleanValue> BOOL_CONFIG_ENTRIES;

    public static final Codec<ModConfigSpec.BooleanValue> BOOL_CONFIG_CODEC;

    private static ModConfigSpec.BooleanValue registerBool(String path, boolean defaultValue) {
        ModConfigSpec.BooleanValue bool = BUILDER.define(path, defaultValue);
        BOOL_CONFIG_ENTRIES.put(path, bool);
        return bool;
    }

    private static ModConfigSpec.IntValue registerInt(String path, int defaultValue, int min, int max) {
        return BUILDER.defineInRange(path, defaultValue, min, max);
    }

    private static ModConfigSpec.DoubleValue registerDouble(String path, double defaultValue, double min, double max) {
        return BUILDER.defineInRange(path, defaultValue, min, max);
    }

    private static <T> ModConfigSpec.ConfigValue<List<? extends T>> registerList(
            String path,
            Supplier<List<? extends T>> defaultSupplier,
            Supplier<T> supplier,
            Predicate<Object> elementValidator) {
        return BUILDER.defineListAllowEmpty(path, defaultSupplier, supplier, elementValidator);
    }

    @Deprecated
    public static boolean isEnabled(ResourceKey<Enchantment> key) {
        return false;
    }

    public static String getEnchConfigKey(ResourceKey<Enchantment> key) {
        return key.location().getPath();
    }

    private static ModConfigSpec.BooleanValue registerEnchantment(ResourceKey<Enchantment> key) {
        var path = getEnchConfigKey(key);
        ModConfigSpec.BooleanValue bool = BUILDER.worldRestart().define(path, true);
        BOOL_CONFIG_ENTRIES.put(path, bool);
        return bool;
    }

    public static final ModConfigSpec CONFIG_SPEC = BUILDER.build();
}
