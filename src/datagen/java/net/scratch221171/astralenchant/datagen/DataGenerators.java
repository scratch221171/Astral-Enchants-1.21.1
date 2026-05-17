package net.scratch221171.astralenchant.datagen;

import java.util.List;
import java.util.Set;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.datagen.bootstraps.AEEnchantmentBootstrap;
import net.scratch221171.astralenchant.datagen.providers.*;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public final class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        AstralEnchant.LOGGER.info("Loading DataGenerators");
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        // Server
        event.createDatapackRegistryObjects(
                new RegistrySetBuilder().add(Registries.ENCHANTMENT, AEEnchantmentBootstrap::bootstrap),
                AEEnchantmentBootstrap::applyConditions);
        event.createProvider(AELootModifierProvider::new);

        event.createProvider((output, future) -> new LootTableProvider(
                output,
                Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(
                        AEBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
                future));

        event.createProvider(AERecipeProvider::new);

        event.createProvider(AEEnchantmentTagsProvider::new);
        event.createProvider(
                (output, future) -> new AEDamageTypeTagsProvider(output, future, event.getExistingFileHelper()));
        event.createBlockAndItemTags(AEBlockTagsProvider::new, AEItemTagsProvider::new);
        // Client
        event.createProvider(output -> new AEBlockStateProvider(output, fileHelper));
        event.createProvider(output -> new AEItemModelProvider(output, fileHelper));

        event.createProvider(AEEnglishLangProvider::new);
        event.createProvider(AEJapaneseLangProvider::new);
    }
}
