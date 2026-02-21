package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import net.scratch221171.astralenchant.common.util.IDamageSourceExtension;

import java.util.List;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class MitigationPiercingHandler {

    @SubscribeEvent
    private static void addDamageTag(EntityInvulnerabilityCheckEvent event) {
        if (Config.MITIGATION_PIERCING.isFalse()) return;
        Entity entity = event.getEntity();
        if (entity.level().isClientSide) return;
        DamageSource source = event.getSource();
        if (source.getEntity() instanceof LivingEntity attacker) {
            Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.MITIGATION_PIERCING, attacker.level());
            if ((source.getWeaponItem() instanceof ItemStack weapon) && weapon.getEnchantmentLevel(enchantment) > 0) {
                IDamageSourceExtension acc = (IDamageSourceExtension) source;
                List<TagKey<DamageType>> tags = Config.MITIGATION_PIERCING_ADDED_DAMAGE_TYPE_TAGS.get()
                        .stream().map(id -> TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.read(id).getOrThrow())).toList();
                tags.forEach(acc::astralEnchant$addExtraTag);
            }
        }
    }

    // パーティクル
    @SubscribeEvent
    private static void onDamage(LivingIncomingDamageEvent event) {
        if (Config.MITIGATION_PIERCING.isFalse()) return;
        Entity entity = event.getEntity();
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.MITIGATION_PIERCING, entity.level());
        ItemStack weapon = event.getSource().getWeaponItem();
        if (weapon != null && weapon.getEnchantmentLevel(enchantment) > 0 && entity.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.END_ROD, entity.getX(), entity.getY(), entity.getZ(), 10, 0f, 1f, 0f, 0.04f);
        }
    }
}
