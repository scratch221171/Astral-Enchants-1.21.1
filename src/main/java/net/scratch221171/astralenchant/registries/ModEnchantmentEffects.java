package net.scratch221171.astralenchant.registries;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.enchantment.adventurepreparation.AdventurersLoreEnchEffect;
import net.scratch221171.astralenchant.enchantment.cooldownreduction.CooldownReductionEnchEffect;
import net.scratch221171.astralenchant.enchantment.mitigationpiercing.MitigationPiercingEnchEffect;
import net.scratch221171.astralenchant.enchantment.itemprotection.ItemProtectionEnchEffect;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_ENTITY_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, AstralEnchant.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> MITIGATION_PIERCING =
            ENCHANTMENT_ENTITY_EFFECTS.register("mitigation_piercing", () -> MitigationPiercingEnchEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> ITEM_PROTECTION =
            ENCHANTMENT_ENTITY_EFFECTS.register("item_protection", () -> ItemProtectionEnchEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> CooldownReduction =
            ENCHANTMENT_ENTITY_EFFECTS.register("cooldown_reduction", () -> CooldownReductionEnchEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> ADVENTURE_PREPARATION =
            ENCHANTMENT_ENTITY_EFFECTS.register("adventurers_lore", () -> AdventurersLoreEnchEffect.CODEC);

    public static void register(IEventBus eventBus) {
        ENCHANTMENT_ENTITY_EFFECTS.register(eventBus);
    }
}
