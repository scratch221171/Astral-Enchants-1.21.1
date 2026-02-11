package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.registries.AEItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AEItemTagsProvider extends ItemTagsProvider {
    public AEItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, AstralEnchant.MOD_ID, existingFileHelper);
    }

    public static final TagKey<Item> BUNDLE = create(AstralEnchant.MOD_ID, "bundle");

    public static final TagKey<Item> GEMS_ARCANE_QUARTZ = create("c", "gems/arcane_quartz");
    public static final TagKey<Item> INGOTS_ARCANIUM = create("c", "ingots/arcanium");
    public static final TagKey<Item> STORAGE_BLOCKS_ARCANIUM = create("c", "storage_blocks/arcanium");

    private static TagKey<Item> create(String namespace, String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(namespace, path));
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BUNDLE).add(Items.BUNDLE);

        tag(GEMS_ARCANE_QUARTZ).add(AEItems.ARCANE_QUARTZ.get());
        tag(Tags.Items.GEMS).addTag(GEMS_ARCANE_QUARTZ);

        tag(INGOTS_ARCANIUM).add(AEItems.ARCANIUM_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(INGOTS_ARCANIUM);

        copy(AEBlockTagsProvider.STORAGE_BLOCKS_ARCANIUM, STORAGE_BLOCKS_ARCANIUM);
        tag(Tags.Items.STORAGE_BLOCKS).addTag(STORAGE_BLOCKS_ARCANIUM);
    }
}
