package net.scratch221171.astralenchant.common.worldgen.structure;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.scratch221171.astralenchant.common.AstralEnchant;

import java.util.List;

public class AEPoolBootstrap {

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

        Holder<StructureTemplatePool> empty = pools.getOrThrow(Pools.EMPTY);

        StructurePoolElement element = StructurePoolElement
                        .single(ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "cube").toString())
                        .apply(StructureTemplatePool.Projection.RIGID);

        context.register(
                AEPools.CUBE_POOL,
                new StructureTemplatePool(
                        empty,
                        List.of(Pair.of(element, 1))
                )
        );
    }
}
