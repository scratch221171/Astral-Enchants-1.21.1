package net.scratch221171.astralenchant.common.worldgen.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.scratch221171.astralenchant.common.AstralEnchant;

public class AEStructureSets {
    public static final ResourceKey<StructureSet> CUBE_SET = ResourceKey.create(Registries.STRUCTURE_SET,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "cube_set"));
}
