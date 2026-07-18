package net.scratch221171.astralenchant.datagen.providers;

import java.util.Set;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.scratch221171.astralenchant.common.registries.AEBlocks;
import org.jetbrains.annotations.NotNull;

public class AEBlockLootTableProvider extends BlockLootSubProvider {
    public AEBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(AEBlocks.ARCANIUM_BLOCK.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return AEBlocks.REGISTER.getEntries().stream()
                .map(DeferredHolder::get)
                .map(block -> (Block) block)
                .toList();
    }
}
