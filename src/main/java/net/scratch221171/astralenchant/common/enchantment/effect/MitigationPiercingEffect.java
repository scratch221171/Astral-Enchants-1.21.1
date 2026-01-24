package net.scratch221171.astralenchant.common.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.scratch221171.astralenchant.common.Config;
import org.jetbrains.annotations.NotNull;

public record MitigationPiercingEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<MitigationPiercingEffect> CODEC = MapCodec.unit(MitigationPiercingEffect::new);

    @Override
    public void apply(@NotNull ServerLevel level, int enchantmentLevel, @NotNull EnchantedItemInUse item, @NotNull Entity entity, @NotNull Vec3 origin) {
        if (!Config.MITIGATION_PIERCING.isTrue()) return;
        level.sendParticles(ParticleTypes.END_ROD, entity.getX(), entity.getY(), entity.getZ(), 10, 0f, 1f, 0f, 0.04f);
    }

    @Override
    public @NotNull MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
