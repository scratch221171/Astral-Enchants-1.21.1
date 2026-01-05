package net.scratch221171.astralenchant.registries;

import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.scratch221171.astralenchant.AstralEnchant;

public class ModLootModifiers {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AstralEnchant.MOD_ID);

//    public static final Supplier<MapCodec<FeatherTouchLootModifier>> FEATHER_TOUCH_LOOT_MODIFIER =
//            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("feather_touch_loot_modifier", () -> FeatherTouchLootModifier.CODEC);

    public static void register(IEventBus eventBus) { GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(eventBus); }
}
