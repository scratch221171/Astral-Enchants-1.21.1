package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.registries.holdersets.AnyHolderSet;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEAttributes;

import java.util.HashMap;

public class AEEnchantmentProvider {
    private static final HashMap<ResourceKey<Enchantment>, ICondition> conditions = new HashMap<>();

    public static HashMap<ResourceKey<Enchantment>, ICondition> getConditions() {
        conditions.clear();

        addCondition(AEEnchantments.SLOT_EXPANSION, new ModLoadedCondition("accessories"));

        return conditions;
    }

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var items = context.registryLookup(Registries.ITEM);

        HolderSet<Item> any = new AnyHolderSet<>(items.orElseThrow());
        HolderSet<Item> armor = items.get().getOrThrow(ItemTags.ARMOR_ENCHANTABLE);
        HolderSet<Item> head = items.get().getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE);
        HolderSet<Item> chest = items.get().getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE);
        HolderSet<Item> foot = items.get().getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE);
        HolderSet<Item> weapon = items.get().getOrThrow(ItemTags.WEAPON_ENCHANTABLE);
        HolderSet<Item> mining = items.get().getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE);
        HolderSet<Item> bundle = items.get().getOrThrow(AEItemTagsProvider.BUNDLE);

        register(context, AEEnchantments.MITIGATION_PIERCING, Enchantment.enchantment(Enchantment.definition(
                weapon,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                32,
                EquipmentSlotGroup.MAINHAND)));

        register(context, AEEnchantments.LAST_STAND, Enchantment.enchantment(Enchantment.definition(
                armor,
                1,
                3,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                32,
                EquipmentSlotGroup.ARMOR)));

        register(context, AEEnchantments.ITEM_PROTECTION, Enchantment.enchantment(Enchantment.definition(
                any,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.ANY)));

        register(context, AEEnchantments.ESSENCE_OF_ENCHANTMENT, Enchantment.enchantment(Enchantment.definition(
                any,
                1,
                5,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                32,
                EquipmentSlotGroup.ANY)));

        register(context, AEEnchantments.COOLDOWN_REDUCTION, Enchantment.enchantment(Enchantment.definition(
                        chest,
                        1,
                        3,
                        Enchantment.dynamicCost(100,10),
                        Enchantment.dynamicCost(150,10),
                        16,
                        EquipmentSlotGroup.CHEST))
                .withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                        ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "cr_bonus"),
                        AEAttributes.COOLDOWN_REDUCTION,
                        LevelBasedValue.perLevel(0.1F),
                        AttributeModifier.Operation.ADD_VALUE
                )));

        register(context, AEEnchantments.FEATHER_TOUCH, Enchantment.enchantment(Enchantment.definition(
                mining,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.MAINHAND)));

        register(context, AEEnchantments.ADVENTURERS_LORE, Enchantment.enchantment(Enchantment.definition(
                foot,
                1,
                3,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                8,
                EquipmentSlotGroup.FEET)));

        register(context, AEEnchantments.COMPATIBILITY, Enchantment.enchantment(Enchantment.definition(
                bundle,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                32,
                EquipmentSlotGroup.ANY)));

        register(context, AEEnchantments.ENDLESS_APPETITE, Enchantment.enchantment(Enchantment.definition(
                chest,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                32,
                EquipmentSlotGroup.CHEST)));

        register(context, AEEnchantments.MOMENTUM, Enchantment.enchantment(Enchantment.definition(
                chest,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.CHEST)));

        register(context, AEEnchantments.INSTANT_TELEPORT, Enchantment.enchantment(Enchantment.definition(
                head,
                1,
                4,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.HEAD)));

        register(context, AEEnchantments.OVERLOAD, Enchantment.enchantment(Enchantment.definition(
                any,
                1,
                5,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                32,
                EquipmentSlotGroup.ANY)));

        register(context, AEEnchantments.SLOT_EXPANSION, Enchantment.enchantment(Enchantment.definition(
                any,
                1,
                3,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.ANY)));

        register(context, AEEnchantments.REACTIVE_ARMOR, Enchantment.enchantment(Enchantment.definition(
                chest,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.CHEST)));

        register(context, AEEnchantments.MYSTIC_REMNANTS, Enchantment.enchantment(Enchantment.definition(
                weapon,
                1,
                5,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.MAINHAND)));
    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }

    private static void addCondition(ResourceKey<Enchantment> key, ICondition condition) {
        conditions.put(key, condition);
    }
}
