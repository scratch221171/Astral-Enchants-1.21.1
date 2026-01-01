package net.scratch221171.astralenchant.mixin;

import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.scratch221171.astralenchant.util.DamageSourceAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.Set;

@Mixin(value = DamageSource.class, remap = false)
public class DamageSourceMixin implements DamageSourceAccessor {

    @Unique
    private Set<TagKey<DamageType>> astralenchant$extraTags;

    @Inject(
            method = "is(Lnet/minecraft/tags/TagKey;)Z",
            at = @At("RETURN"),
            cancellable = true
    )
    private void astralenchant$isExtraTag(TagKey<DamageType> tag, CallbackInfoReturnable<Boolean> cir) {
        if (this.astralenchant$extraTags != null && this.astralenchant$extraTags.contains(tag)) {
            cir.setReturnValue(true);
        }
    }

    @Override
    public void astralenchant$addDamageTag(TagKey<DamageType> tag) {
        if (this.astralenchant$extraTags == null) {
            this.astralenchant$extraTags = new HashSet<>();
        }
        this.astralenchant$extraTags.add(tag);
    }

    @Override
    public void astralenchant$removeDamageTag(TagKey<DamageType> tag) {
        if (this.astralenchant$extraTags != null) {
            this.astralenchant$extraTags.remove(tag);
        }
    }
}
