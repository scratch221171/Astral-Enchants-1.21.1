package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.registries.AEItems;

public class AEItemModelProvider extends ItemModelProvider {
    public AEItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AstralEnchant.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(AEItems.ENCHANTMENT_SHARD.get());
        basicItem(AEItems.ARCANE_QUARTZ.get());
        basicItem(AEItems.ARCANIUM_INGOT.get());
        basicItem(AEItems.BUDDING_ARCANIUM_INGOT.get());
    }
}
