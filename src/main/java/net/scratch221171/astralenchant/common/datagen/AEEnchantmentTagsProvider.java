package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.scratch221171.astralenchant.common.AstralEnchant;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AEEnchantmentTagsProvider extends EnchantmentTagsProvider {
    public AEEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AstralEnchant.MOD_ID, existingFileHelper);
    }

    public static final TagKey<Enchantment> CURSE = TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath("minecraft", "curse"));

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(CURSE).add(AEEnchantments.ITEM_PROTECTION).replace(false);
    }
}
