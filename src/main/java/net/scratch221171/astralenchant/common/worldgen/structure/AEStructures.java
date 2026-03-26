package net.scratch221171.astralenchant.common.worldgen.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.scratch221171.astralenchant.common.AstralEnchant;

public class AEStructures {

    public static final ResourceKey<Structure> CUBE = ResourceKey.create(
                    Registries.STRUCTURE,
                    ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "cube")
            );
}
