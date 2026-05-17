package net.scratch221171.astralenchant.datagen.providers;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.AstralEnchantmentTags;
import net.scratch221171.astralenchant.common.registries.AEItems;
import org.jetbrains.annotations.NotNull;

public class AEItemTagsProvider extends ItemTagsProvider {
    public AEItemTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, AstralEnchant.MOD_ID, null);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(AstralEnchantmentTags.Items.BUNDLE).add(Items.BUNDLE);

        tag(AstralEnchantmentTags.Items.GEMS_ARCANE_QUARTZ).add(AEItems.ARCANE_QUARTZ.get());
        tag(Tags.Items.GEMS).addTag(AstralEnchantmentTags.Items.GEMS_ARCANE_QUARTZ);

        tag(AstralEnchantmentTags.Items.INGOTS_ARCANIUM).add(AEItems.ARCANIUM_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(AstralEnchantmentTags.Items.INGOTS_ARCANIUM);

        copy(AstralEnchantmentTags.Blocks.STORAGE_BLOCKS_ARCANIUM, AstralEnchantmentTags.Items.STORAGE_BLOCKS_ARCANIUM);
        tag(Tags.Items.STORAGE_BLOCKS).addTag(AstralEnchantmentTags.Items.STORAGE_BLOCKS_ARCANIUM);
    }
}
