package net.scratch221171.astralenchant.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public interface IDamageSourceExtension {
    void astralenchant$addDamageTag(TagKey<DamageType> tag);
    void astralenchant$removeDamageTag(TagKey<DamageType> tag);
}
