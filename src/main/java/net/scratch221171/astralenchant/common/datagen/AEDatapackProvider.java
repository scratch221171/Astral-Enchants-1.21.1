package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class AEDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, AEEnchantmentProvider::bootstrap);

    public AEDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, AEDatapackProvider::buildConditions, Set.of(AstralEnchant.MOD_ID));
    }

    private static void buildConditions(
            BiConsumer<ResourceKey<?>, ICondition> consumer
    ) {
        AEEnchantmentProvider.getConditions().forEach(consumer);
    }
}