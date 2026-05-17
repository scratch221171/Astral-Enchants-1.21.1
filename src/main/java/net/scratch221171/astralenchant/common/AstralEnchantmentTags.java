package net.scratch221171.astralenchant.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class AstralEnchantmentTags {
    public static final String COMMON = "c";

    public static ResourceLocation commonId(String path) {
        return ResourceLocation.fromNamespaceAndPath(COMMON, path);
    }

    // Block
    public static final class Blocks {
        public static final TagKey<Block> STORAGE_BLOCKS_ARCANIUM =
                BlockTags.create(commonId("storage_blocks/arcanium"));
    }

    // Damage Type
    public static final class DamageTypes {
        public static final TagKey<DamageType> DISABLE_REACTIVE_ARMOR = create("disable_reactive_armor");
        public static final TagKey<DamageType> ENABLE_MITIGATION_PIERCING = create("enable_mitigation_piercing");

        private static TagKey<DamageType> create(String path) {
            return TagKey.create(Registries.DAMAGE_TYPE, AstralEnchant.id(path));
        }
    }

    // Item
    public static final class Items {
        public static final TagKey<Item> BUNDLE = ItemTags.create(AstralEnchant.id("bundle"));

        public static final TagKey<Item> GEMS_ARCANE_QUARTZ = ItemTags.create(commonId("gems/arcane_quartz"));
        public static final TagKey<Item> INGOTS_ARCANIUM = ItemTags.create(commonId("ingots/arcanium"));
        public static final TagKey<Item> STORAGE_BLOCKS_ARCANIUM = ItemTags.create(commonId("storage_blocks/arcanium"));
    }
}
