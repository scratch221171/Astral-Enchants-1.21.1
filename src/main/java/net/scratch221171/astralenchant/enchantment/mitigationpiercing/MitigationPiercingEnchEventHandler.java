package net.scratch221171.astralenchant.enchantment.mitigationpiercing;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.enchantment.ModEnchantments;
import net.scratch221171.astralenchant.util.DamageSourceAccessor;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class MitigationPiercingEnchEventHandler {
    @SubscribeEvent
    public static void addDamageTag(EntityInvulnerabilityCheckEvent event) {
        DamageSource source = event.getSource();
        if (source.getEntity() instanceof LivingEntity attacker) {
            Holder<Enchantment> mitigationPiercingEnchantment = attacker.level().registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.MITIGATION_PIERCING);
            if ((source.getWeaponItem() instanceof ItemStack weapon) && weapon.getEnchantmentLevel(mitigationPiercingEnchantment) > 0) {
                DamageSourceAccessor acc = (DamageSourceAccessor) source;
                acc.astralenchant$addDamageTag(DamageTypeTags.BYPASSES_ARMOR);
                acc.astralenchant$addDamageTag(DamageTypeTags.BYPASSES_COOLDOWN);
                acc.astralenchant$addDamageTag(DamageTypeTags.BYPASSES_EFFECTS);
                acc.astralenchant$addDamageTag(DamageTypeTags.BYPASSES_INVULNERABILITY);
            }
        }
    }
}
