package net.scratch221171.astralenchant.common.worldgen.structure;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.scratch221171.astralenchant.common.worldgen.biome.AEBiomes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AEStructureBootstrap {

    public static void bootstrap(BootstrapContext<Structure> context) {

        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

        JigsawStructure structure = new JigsawStructure(
                new Structure.StructureSettings(
                        HolderSet.direct(biomes.getOrThrow(AEBiomes.BABEL)),
                        Map.of(),
                        GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
                        TerrainAdjustment.NONE
                ),
                pools.getOrThrow(AEPools.CUBE_POOL),
                Optional.empty(),
                1,
                ConstantHeight.of(VerticalAnchor.absolute(64)),
                false,
                Optional.empty(),
                80,
                List.of(),
                JigsawStructure.DEFAULT_DIMENSION_PADDING,
                JigsawStructure.DEFAULT_LIQUID_SETTINGS
        );

        context.register(AEStructures.CUBE, structure);
    }
}
