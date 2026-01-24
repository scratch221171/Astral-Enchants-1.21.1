package net.scratch221171.astralenchant.common.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public interface IDamageSourceExtension {
    void astralEnchant$addDamageTag(TagKey<DamageType> tag);
    void astralEnchant$removeDamageTag(TagKey<DamageType> tag);
}
