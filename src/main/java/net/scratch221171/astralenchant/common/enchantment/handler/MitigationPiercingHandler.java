package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.scratch221171.astralenchant.common.AstralEnchantmentTags;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;

public class MitigationPiercingHandler {

    @SubscribeEvent
    private static void addDamageTag(EntityInvulnerabilityCheckEvent event) {
        Entity entity = event.getEntity();
        if (entity.level().isClientSide) return;
        DamageSource source = event.getSource();
        if (source.getEntity() instanceof LivingEntity attacker) {
            AEUtils.getEnchantmentHolder(AEEnchantments.MITIGATION_PIERCING, attacker)
                    .ifPresent(holder -> {
                        var weapon = source.getWeaponItem();
                        if (weapon != null && weapon.getEnchantmentLevel(holder) > 0) {
                            if (source.is(AstralEnchantmentTags.DamageTypes.ENABLE_MITIGATION_PIERCING)) {
                                event.setInvulnerable(false);
                            }
                        }
                    });
        }
    }

    // パーティクル
    @SubscribeEvent
    private static void onDamage(LivingIncomingDamageEvent event) {
        Entity entity = event.getEntity();
        AEUtils.getEnchantmentHolder(AEEnchantments.MITIGATION_PIERCING, entity).ifPresent(holder -> {
            ItemStack weapon = event.getSource().getWeaponItem();
            if (weapon != null
                    && weapon.getEnchantmentLevel(holder) > 0
                    && entity.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.END_ROD, entity.getX(), entity.getY(), entity.getZ(), 10, 0f, 1f, 0f, 0.04f);
            }
        });
    }
}
