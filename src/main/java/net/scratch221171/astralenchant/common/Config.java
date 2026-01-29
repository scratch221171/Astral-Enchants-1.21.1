package net.scratch221171.astralenchant.common;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    static {
        BUILDER.push("enchantment_toggling");
        MITIGATION_PIERCING = BUILDER.define(AEEnchantments.MITIGATION_PIERCING.location().getPath(), true);
        LAST_STAND = BUILDER.define(AEEnchantments.LAST_STAND.location().getPath(), true);
        ITEM_PROTECTION = BUILDER.define(AEEnchantments.ITEM_PROTECTION.location().getPath(), true);
        ESSENCE_OF_ENCHANTMENT = BUILDER.define(AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().getPath(), true);
        COOLDOWN_REDUCTION = BUILDER.define(AEEnchantments.COOLDOWN_REDUCTION.location().getPath(), true);
        FEATHER_TOUCH = BUILDER.define(AEEnchantments.FEATHER_TOUCH.location().getPath(), true);
        ADVENTURERS_LORE = BUILDER.define(AEEnchantments.ADVENTURERS_LORE.location().getPath(), true);
        COMPATIBILITY = BUILDER.define(AEEnchantments.COMPATIBILITY.location().getPath(), true);
        ENDLESS_APPETITE = BUILDER.define(AEEnchantments.ENDLESS_APPETITE.location().getPath(), true);
        MOMENTUM = BUILDER.define(AEEnchantments.MOMENTUM.location().getPath(), true);
        INSTANT_TELEPORT = BUILDER.define(AEEnchantments.INSTANT_TELEPORT.location().getPath(), true);
    }
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

    static final ModConfigSpec CONFIG_SPEC = BUILDER.build();
}
