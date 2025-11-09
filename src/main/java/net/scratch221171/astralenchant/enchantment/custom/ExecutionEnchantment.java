package net.scratch221171.astralenchant.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record ExecutionEnchantment() implements EnchantmentEntityEffect {
    public static final MapCodec<ExecutionEnchantment> CODEC = MapCodec.unit(ExecutionEnchantment::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        level.sendParticles(ParticleTypes.END_ROD, entity.getX(), entity.getY(), entity.getZ(), 50, 0f, 1f, 0f, 0.04f);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
