package net.scratch221171.astralenchant.common.registries;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.common.AstralEnchant;

public class AEEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_ENTITY_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, AstralEnchant.MOD_ID);

//    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> MITIGATION_PIERCING =
//            ENCHANTMENT_ENTITY_EFFECTS.register("mitigation_piercing", () -> MitigationPiercingEffect.CODEC);

    public static void register(IEventBus eventBus) {
        ENCHANTMENT_ENTITY_EFFECTS.register(eventBus);
    }
}
