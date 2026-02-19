package net.scratch221171.astralenchant.common.registries;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.providers.number.LootNumberProviderType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.loot.providers.AEEnchantmentLevelProvider;

public class AENumberProviders {
    public static DeferredRegister<LootNumberProviderType> NUMBER_PROVIDERS = DeferredRegister.create(BuiltInRegistries.LOOT_NUMBER_PROVIDER_TYPE, AstralEnchant.MOD_ID);

    public static final Holder<LootNumberProviderType> AE_ENCHANTMENT_LEVEL = NUMBER_PROVIDERS.register("ae_enchantment_level",
            () -> new LootNumberProviderType(AEEnchantmentLevelProvider.CODEC));

    public static void register(IEventBus eventBus) {
        NUMBER_PROVIDERS.register(eventBus);
    }
}
