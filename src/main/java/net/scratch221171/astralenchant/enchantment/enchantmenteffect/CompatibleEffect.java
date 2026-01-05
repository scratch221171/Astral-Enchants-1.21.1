package net.scratch221171.astralenchant.enchantment.enchantmenteffect;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public record CompatibleEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<CompatibleEffect> CODEC = MapCodec.unit(CompatibleEffect::new);

    @Override
    public void apply(@NotNull ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, @NotNull Entity entity, @NotNull Vec3 origin) {}

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
