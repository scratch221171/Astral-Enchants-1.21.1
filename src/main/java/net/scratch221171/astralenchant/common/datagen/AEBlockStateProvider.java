package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.registries.AEBlocks;

public class AEBlockStateProvider extends BlockStateProvider {
    public AEBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AstralEnchant.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        addFullBlock(AEBlocks.ARCANIUM_BLOCK);
    }

    private void addFullBlock(DeferredBlock<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}
