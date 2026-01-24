package net.scratch221171.astralenchant.common;

import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    static {
        BUILDER.push("enchantment_toggling");
        MITIGATION_PIERCING = BUILDER.define("mitigation_piercing", true);
        LAST_STAND = BUILDER.define("last_stand", true);
        ITEM_PROTECTION = BUILDER.define("item_protection", true);
        ESSENCE_OF_ENCHANTMENT = BUILDER.define("essence_of_enchantment", true);
        COOLDOWN_REDUCTION = BUILDER.define("cooldown_reduction", true);
        FEATHER_TOUCH = BUILDER.define("feather_touch", true);
        ADVENTURERS_LORE = BUILDER.define("adventurers_lore", true);
        COMPATIBILITY = BUILDER.define("compatibility", true);
        ENDLESS_APPETITE = BUILDER.define("endless_appetite", true);
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

    static final ModConfigSpec CONFIG_SPEC = BUILDER.build();
}
