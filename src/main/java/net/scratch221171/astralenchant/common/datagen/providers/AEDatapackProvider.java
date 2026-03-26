package net.scratch221171.astralenchant.common.datagen.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.worldgen.biome.AEBiomeBootstrap;
import net.scratch221171.astralenchant.common.worldgen.dimension.AEDimensionBootstrap;
import net.scratch221171.astralenchant.common.worldgen.dimension.AEDimensionTypeBootstrap;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantmentBootstrap;
import net.scratch221171.astralenchant.common.worldgen.structure.AEStructureBootstrap;
import net.scratch221171.astralenchant.common.worldgen.structure.AEPoolBootstrap;
import net.scratch221171.astralenchant.common.worldgen.structure.AEStructureSetBootstrap;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class AEDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, AEEnchantmentBootstrap::bootstrap)
            .add(Registries.DIMENSION_TYPE, AEDimensionTypeBootstrap::bootstrap)
            .add(Registries.BIOME, AEBiomeBootstrap::bootstrap)
            .add(Registries.LEVEL_STEM, AEDimensionBootstrap::bootstrap)
            .add(Registries.STRUCTURE, AEStructureBootstrap::bootstrap)
            .add(Registries.TEMPLATE_POOL, AEPoolBootstrap::bootstrap)
            .add(Registries.STRUCTURE_SET, AEStructureSetBootstrap::bootstrap);

    public AEDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, AEDatapackProvider::buildConditions, Set.of(AstralEnchant.MOD_ID));
    }

    private static void buildConditions(
            BiConsumer<ResourceKey<?>, ICondition> consumer
    ) {
        AEEnchantmentBootstrap.getConditions().forEach(consumer);
    }
}