package net.scratch221171.astralenchant.enchantment;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.neoforged.neoforge.registries.holdersets.AnyHolderSet;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.enchantment.cooldownreduction.CooldownReductionEnchEffect;
import net.scratch221171.astralenchant.enchantment.mitigationpiercing.MitigationPiercingEnchEffect;
import net.scratch221171.astralenchant.enchantment.itemprotection.ItemProtectionEnchEffect;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> MITIGATION_PIERCING = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "mitigation_piercing"));
    public static final ResourceKey<Enchantment> LAST_STAND = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "last_stand"));
    public static final ResourceKey<Enchantment> ITEM_PROTECTION = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "item_protection"));
    public static final ResourceKey<Enchantment> ESSENCE_OF_ENCHANT = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "essence_of_enchant"));
    public static final ResourceKey<Enchantment> COOLDOWN_REDUCTION = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "cooldown_reduction"));
    public static final ResourceKey<Enchantment> FEATHER_TOUCH = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "feather_touch"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.registryLookup(Registries.ITEM);

        HolderSet<Item> any = new AnyHolderSet<>(items.orElseThrow());
        HolderSet<Item> armor = items.get().getOrThrow(ItemTags.ARMOR_ENCHANTABLE);
        HolderSet<Item> chestPlate = items.get().getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE);
        HolderSet<Item> weapon = items.get().getOrThrow(ItemTags.WEAPON_ENCHANTABLE);
        HolderSet<Item> mining = items.get().getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE);

        register(context, MITIGATION_PIERCING, Enchantment.enchantment(Enchantment.definition(
                weapon,
                weapon,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.MAINHAND))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new MitigationPiercingEnchEffect()));

        register(context, LAST_STAND, Enchantment.enchantment(Enchantment.definition(
                armor,
                armor,
                1,
                3,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.ARMOR)));

        register(context, ITEM_PROTECTION, Enchantment.enchantment(Enchantment.definition(
                any,
                any,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                8,
                EquipmentSlotGroup.ANY))
                .withEffect(EnchantmentEffectComponents.TICK, new ItemProtectionEnchEffect()));

        register(context, ESSENCE_OF_ENCHANT, Enchantment.enchantment(Enchantment.definition(
                any,
                any,
                1,
                5,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.ANY)));

        register(context, COOLDOWN_REDUCTION, Enchantment.enchantment(Enchantment.definition(
                chestPlate,
                chestPlate,
                1,
                3,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                8,
                EquipmentSlotGroup.CHEST))
                .withEffect(EnchantmentEffectComponents.TICK, new CooldownReductionEnchEffect()));
        register(context, FEATHER_TOUCH, Enchantment.enchantment(Enchantment.definition(
                mining,
                mining,
                1,
                3,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                8,
                EquipmentSlotGroup.MAINHAND)));
    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}
