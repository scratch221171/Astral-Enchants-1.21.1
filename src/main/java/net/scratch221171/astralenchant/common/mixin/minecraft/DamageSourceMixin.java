/*
 * Based on code from Apotheosis
 * https://github.com/Shadows-of-Fire/Apotheosis
 *
 * Licensed under the MIT License.
 *
 * Original copyright (c) 2018-2025 Stormraven Studios, LLC
 */

package net.scratch221171.astralenchant.common.mixin.minecraft;

import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.scratch221171.astralenchant.common.AstralEnchantmentTags;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DamageSource.class, remap = false)
public abstract class DamageSourceMixin {
    @Shadow
    public abstract Holder<DamageType> typeHolder();

    @Shadow
    @Final
    private Holder<DamageType> type;

    /** {@link AEEnchantments#MITIGATION_PIERCING} または {@link AEEnchantments#REACTIVE_ARMOR} によるタグ編集を反映する。 */
    @Inject(method = "is(Lnet/minecraft/tags/TagKey;)Z", at = @At("RETURN"), cancellable = true)
    private void astralenchant$isExtraTag(TagKey<DamageType> tag, CallbackInfoReturnable<Boolean> cir) {
        if (typeHolder().is(AstralEnchantmentTags.DamageTypes.ENABLE_MITIGATION_PIERCING)) {
            cir.setReturnValue(true);
        } else if (typeHolder().is(AstralEnchantmentTags.DamageTypes.DISABLE_REACTIVE_ARMOR)) {
            cir.setReturnValue(false);
        }
    }
}
