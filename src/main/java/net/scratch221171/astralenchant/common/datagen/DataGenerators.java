package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


        lookupProvider = generator.addProvider(event.includeServer(), new AEDatapackProvider(packOutput, lookupProvider)).getRegistryProvider();
        generator.addProvider(event.includeServer(), new AELootModifierProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new AERecipeProvider(packOutput, lookupProvider));
        AEBlockTagsProvider blockTags = new AEBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new AEItemTagsProvider(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new AEEnchantmentTagsProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new AELanguageProviderENUS(packOutput));
        generator.addProvider(event.includeServer(), new AELanguageProviderJAJP(packOutput));
    }
}
