package net.scratch221171.astralenchant.common.worldgen.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.scratch221171.astralenchant.common.AstralEnchant;

public class AEPools {

    public static final ResourceKey<StructureTemplatePool> CUBE_POOL = ResourceKey.create(Registries.TEMPLATE_POOL,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "cube_pool"));
}
