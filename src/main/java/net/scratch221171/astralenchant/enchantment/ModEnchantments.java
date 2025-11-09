package net.scratch221171.astralenchant.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.enchantment.custom.ExecutionEnchantment;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> TEST = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "test"));
    public static final ResourceKey<Enchantment> EXECUTION = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "execution"));
    public static final ResourceKey<Enchantment> LAST_STAND = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "last_stand"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, EXECUTION, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.dynamicCost(100,10),
                        Enchantment.dynamicCost(150,10),
                        8,
                        EquipmentSlotGroup.MAINHAND))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new ExecutionEnchantment()));
        register(context, LAST_STAND, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
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
