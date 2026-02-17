package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.IDamageSourceExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.Set;

@Mixin(value = DamageSource.class, remap = false)
public class DamageSourceMixin implements IDamageSourceExtension {

    @Unique
    private Set<TagKey<DamageType>> astralenchant$extraTags;

    @Unique
    private Set<TagKey<DamageType>> astralenchant$disabledTags;

    /**
     * {@link AEEnchantments#MITIGATION_PIERCING} または {@link AEEnchantments#REACTIVE_ARMOR} によるタグ編集を反映する。
     */
    @Inject(method = "is(Lnet/minecraft/tags/TagKey;)Z", at = @At("RETURN"), cancellable = true)
    private void astralEnchant$isExtraTag(TagKey<DamageType> tag, CallbackInfoReturnable<Boolean> cir) {
        if (this.astralenchant$extraTags != null && this.astralenchant$extraTags.contains(tag)) {
            cir.setReturnValue(true);
        } else if (this.astralenchant$disabledTags != null && this.astralenchant$disabledTags.contains(tag)) {
            cir.setReturnValue(false);
        }
    }

    @Override
    public void astralEnchant$addExtraTag(TagKey<DamageType> tag) {
        if (this.astralenchant$extraTags == null) {
            this.astralenchant$extraTags = new HashSet<>();
        }
        this.astralenchant$extraTags.add(tag);
    }

    @Override
    public void astralEnchant$removeExtraTag(TagKey<DamageType> tag) {
        if (this.astralenchant$extraTags != null) {
            this.astralenchant$extraTags.remove(tag);
        }
    }

    @Override
    public void astralEnchant$addDisabledTag(TagKey<DamageType> tag) {
        if (this.astralenchant$disabledTags == null) {
            this.astralenchant$disabledTags = new HashSet<>();
        }
        this.astralenchant$disabledTags.add(tag);
    }

    @Override
    public void astralEnchant$removeDisabledTag(TagKey<DamageType> tag) {
        if (this.astralenchant$disabledTags != null) {
            this.astralenchant$disabledTags.remove(tag);
        }
    }
}
