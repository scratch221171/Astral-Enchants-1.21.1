package net.scratch221171.astralenchant.common.worldgen.dimension;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.LevelStem;
import net.scratch221171.astralenchant.common.AstralEnchant;

public class AEDimensions {
    public static final ResourceKey<LevelStem> LIBRARY_OF_BABEL = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "library_of_babel"));
}
