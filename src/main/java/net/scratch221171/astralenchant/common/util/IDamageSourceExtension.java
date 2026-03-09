package net.scratch221171.astralenchant.common.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public interface IDamageSourceExtension {
    void astralenchant$addExtraTag(TagKey<DamageType> tag);
    void astralenchant$removeExtraTag(TagKey<DamageType> tag);
    void astralenchant$addDisabledTag(TagKey<DamageType> tag);
    void astralenchant$removeDisabledTag(TagKey<DamageType> tag);
}
