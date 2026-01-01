package net.scratch221171.astralenchant.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.scratch221171.astralenchant.AstralEnchant;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        lookupProvider = generator.addProvider(event.includeServer(), new ModDatapackProvider(packOutput, lookupProvider)).getRegistryProvider();

        generator.addProvider(event.includeServer(), new ModLootModifierProvider(packOutput, lookupProvider));
    }
}
