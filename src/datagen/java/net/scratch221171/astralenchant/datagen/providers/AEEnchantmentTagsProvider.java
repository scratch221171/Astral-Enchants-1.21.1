package net.scratch221171.astralenchant.datagen.providers;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import org.jetbrains.annotations.NotNull;

public class AEEnchantmentTagsProvider extends EnchantmentTagsProvider {
    public AEEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, AstralEnchant.MOD_ID, null);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(EnchantmentTags.CURSE).add(AEEnchantments.CURSE_OF_IGNORANCE, AEEnchantments.CURSE_OF_ENCHANTMENT);
        tag(EnchantmentTags.ON_RANDOM_LOOT).add(AEEnchantments.CURSE_OF_IGNORANCE);
    }
}
