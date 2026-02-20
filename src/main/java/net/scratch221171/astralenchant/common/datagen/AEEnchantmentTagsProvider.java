package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AEEnchantmentTagsProvider extends EnchantmentTagsProvider {
    public AEEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AstralEnchant.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(EnchantmentTags.CURSE).add(AEEnchantments.ITEM_PROTECTION, AEEnchantments.CURSE_OF_IGNORANCE).replace(false);
        tag(EnchantmentTags.ON_RANDOM_LOOT).add(AEEnchantments.CURSE_OF_IGNORANCE);
    }
}
