package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.registries.AEBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AEBlockTagsProvider extends BlockTagsProvider {
    public AEBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AstralEnchant.MOD_ID, existingFileHelper);
    }

    public static final TagKey<Block> STORAGE_BLOCKS_ARCANIUM = create("c", "storage_blocks/arcanium");

    private static TagKey<Block> create(String namespace, String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(namespace, path));
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider lookupProvider) {
        tag(STORAGE_BLOCKS_ARCANIUM).add(AEBlocks.ARCANIUM_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(STORAGE_BLOCKS_ARCANIUM);
    }
}
