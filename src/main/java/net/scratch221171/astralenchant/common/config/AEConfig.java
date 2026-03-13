package net.scratch221171.astralenchant.common.config;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

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

    public static final ModConfigSpec.ConfigValue<List<? extends String>> MITIGATION_PIERCING_ADDED_DAMAGE_TYPE_TAGS;
    public static final ModConfigSpec.IntValue LAST_STAND_REQUIRED_BASE_EXPERIENCE;
    public static final ModConfigSpec.BooleanValue LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG;
    public static final ModConfigSpec.BooleanValue ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION;
    public static final ModConfigSpec.DoubleValue ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER;
    public static final ModConfigSpec.IntValue INSTANT_TELEPORT_DISTANCE_INCREASE_PER_LEVEL;
    public static final ModConfigSpec.IntValue INSTANT_TELEPORT_MAX_DISTANCE;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> REACTIVE_ARMOR_DISABLED_DAMAGE_TYPE_TAGS;
    public static final ModConfigSpec.DoubleValue DISTORTION_ANGLE_PER_LEVEL;

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

    static {
        BUILDER.push("settings");

            MITIGATION_PIERCING_ADDED_DAMAGE_TYPE_TAGS =
                    registerList(
                        "mitigation_piercing_added_damage_type_tags",
                        () -> Stream.of(
                                DamageTypeTags.BYPASSES_ARMOR,
                                DamageTypeTags.BYPASSES_COOLDOWN,
                                DamageTypeTags.BYPASSES_EFFECTS,
                                DamageTypeTags.BYPASSES_ENCHANTMENTS,
                                DamageTypeTags.BYPASSES_INVULNERABILITY,
                                DamageTypeTags.BYPASSES_SHIELD
                        ).map(TagKey::location).map(ResourceLocation::toString).toList(),
                        () -> DamageTypeTags.BYPASSES_ARMOR.location().toString(),
                        obj -> obj instanceof String s && ResourceLocation.tryParse(s) != null
                    );

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

            INSTANT_TELEPORT_MAX_DISTANCE =
                    registerInt("instant_teleport_max_distance", 256, 0, Integer.MAX_VALUE);

            REACTIVE_ARMOR_DISABLED_DAMAGE_TYPE_TAGS =
                    registerList(
                        "reactive_armor_disabled_damage_type_tags",
                        () -> Stream.of(
                                DamageTypeTags.BYPASSES_ARMOR,
                                DamageTypeTags.BYPASSES_ENCHANTMENTS
                        ).map(TagKey::location).map(ResourceLocation::toString).toList(),
                        () -> DamageTypeTags.BYPASSES_ARMOR.location().toString(),
                        obj -> obj instanceof String s && s.contains(":")
                    );

        DISTORTION_ANGLE_PER_LEVEL =
                registerDouble("distortion_angle_per_level", 7.5, 0, Double.MAX_VALUE);

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

        BUILDER.pop();
    }

    static ModConfigSpec.BooleanValue registerBool(String path, boolean defaultValue) {
        ModConfigSpec.BooleanValue bool = BUILDER.define(path, defaultValue);
        ConfigRegistry.register(path, bool, defaultValue);
        return bool;
    }

    static ModConfigSpec.IntValue registerInt(String path, int defaultValue, int min, int max) {
        ModConfigSpec.IntValue integer = BUILDER.defineInRange(path, defaultValue, min, max);
        ConfigRegistry.register(path, integer, defaultValue);
        return integer;
    }

    static ModConfigSpec.DoubleValue registerDouble(String path, double defaultValue, double min, double max) {
        ModConfigSpec.DoubleValue integer = BUILDER.defineInRange(path, defaultValue, min, max);
        ConfigRegistry.register(path, integer, defaultValue);
        return integer;
    }

    static <T> ModConfigSpec.ConfigValue<List<? extends T>> registerList(String path, Supplier<List<? extends T>> defaultSupplier, Supplier<T> supplier, Predicate<Object> elementValidator) {
        ModConfigSpec.ConfigValue<List<? extends T>> list = BUILDER.defineListAllowEmpty(path, defaultSupplier, supplier, elementValidator);
        ConfigRegistry.register(path, list, defaultSupplier.get());
        return list;
    }

    static ModConfigSpec.BooleanValue registerEnchantment(ResourceKey<Enchantment> key) {
        return registerBool(key.location().getPath(), true);
    }

    public static final ModConfigSpec CONFIG_SPEC = BUILDER.build();
}
