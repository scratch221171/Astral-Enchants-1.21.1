package net.scratch221171.astralenchant.common.registries;

import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.loot.EBLootModifier;

import java.util.function.Supplier;

public class AELootModifiers {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AstralEnchant.MOD_ID);

    public static final Supplier<MapCodec<EBLootModifier>> EB_LOOT_MODIFIER =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("eb_loot_modifier", () -> EBLootModifier.CODEC);

    public static void register(IEventBus eventBus) { GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(eventBus); }
}
