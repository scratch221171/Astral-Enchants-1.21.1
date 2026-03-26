package net.scratch221171.astralenchant.common.worldgen.dimension;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.flat.FlatLayerInfo;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.scratch221171.astralenchant.common.worldgen.biome.AEBiomes;
import net.scratch221171.astralenchant.common.worldgen.structure.AEStructureSets;

import java.util.List;
import java.util.Optional;

public class AEDimensionBootstrap {

    public static void bootstrap(BootstrapContext<LevelStem> context) {

        HolderGetter<DimensionType> dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);

        Holder<DimensionType> type = dimensionTypes.getOrThrow(AEDimensionTypes.LIBRARY_OF_BABEL);

        Holder<Biome> biome = context.lookup(Registries.BIOME).getOrThrow(AEBiomes.BABEL);

        List<FlatLayerInfo> layers = List.of(
                new FlatLayerInfo(1, Blocks.BEDROCK),
                new FlatLayerInfo(254, Blocks.STONE_BRICKS),
                new FlatLayerInfo(1, Blocks.BEDROCK)
        );

        FlatLevelGeneratorSettings settings = new FlatLevelGeneratorSettings(Optional.empty(), biome, List.of())
                .withBiomeAndLayers(layers, Optional.empty(), biome);

        settings.setDecoration();

        ChunkGenerator generator = new FlatLevelSource(settings);

        context.register(
                AEDimensions.LIBRARY_OF_BABEL,
                new LevelStem(type, generator)
        );
    }
}
