package net.scratch221171.astralenchant.common.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public interface IDamageSourceExtension {
    void astralEnchant$addExtraTag(TagKey<DamageType> tag);
    void astralEnchant$removeExtraTag(TagKey<DamageType> tag);
    void astralEnchant$addDisabledTag(TagKey<DamageType> tag);
    void astralEnchant$removeDisabledTag(TagKey<DamageType> tag);
}
