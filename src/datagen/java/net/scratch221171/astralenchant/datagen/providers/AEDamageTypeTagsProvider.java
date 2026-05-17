package net.scratch221171.astralenchant.datagen.providers;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.AstralEnchantmentTags;
import org.jetbrains.annotations.NotNull;

public class AEDamageTypeTagsProvider extends TagsProvider<DamageType> {
    public AEDamageTypeTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper existingFileHelper) {
        super(output, Registries.DAMAGE_TYPE, lookupProvider, AstralEnchant.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(AstralEnchantmentTags.DamageTypes.ENABLE_MITIGATION_PIERCING)
                .addTag(DamageTypeTags.BYPASSES_ARMOR)
                .addTag(DamageTypeTags.BYPASSES_EFFECTS)
                .addTag(DamageTypeTags.BYPASSES_ENCHANTMENTS)
                .addTag(DamageTypeTags.BYPASSES_INVULNERABILITY)
                .addTag(DamageTypeTags.BYPASSES_SHIELD)
                .addOptionalTag(DamageTypeTags.BYPASSES_COOLDOWN);
        tag(AstralEnchantmentTags.DamageTypes.DISABLE_REACTIVE_ARMOR)
                .addTag(DamageTypeTags.BYPASSES_ARMOR)
                .addTag(DamageTypeTags.BYPASSES_ENCHANTMENTS);
    }
}
