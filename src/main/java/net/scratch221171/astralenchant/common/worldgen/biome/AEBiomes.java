package net.scratch221171.astralenchant.common.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.scratch221171.astralenchant.common.AstralEnchant;

public class AEBiomes {
    public static final ResourceKey<Biome> BABEL = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "babel"));
}
