package net.scratch221171.astralenchant.common.worldgen.dimension;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;
import net.scratch221171.astralenchant.common.AstralEnchant;

public class AEDimensionTypes {
    public static final ResourceKey<DimensionType> LIBRARY_OF_BABEL = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "library_of_babel"));
}
