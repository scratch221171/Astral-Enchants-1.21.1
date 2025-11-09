package net.scratch221171.astralenchant.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.enchantment.custom.ExecutionEnchantment;
import net.scratch221171.astralenchant.enchantment.custom.LastStandEnchantment;

import java.util.function.Supplier;

public class ModEnchantmentEffect {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_ENTITY_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, AstralEnchant.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> EXECUTION =
            ENCHANTMENT_ENTITY_EFFECTS.register("execution", () -> ExecutionEnchantment.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> LAST_STAND =
            ENCHANTMENT_ENTITY_EFFECTS.register("last_stand", () -> LastStandEnchantment.CODEC);

    public static void register(IEventBus eventBus) {
        ENCHANTMENT_ENTITY_EFFECTS.register(eventBus);
    }
}
