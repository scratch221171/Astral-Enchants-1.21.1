package net.scratch221171.astralenchant.common.enchantment.handler;

import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class LastStandHandler {

    @SubscribeEvent
    private static void onLivingDeath(LivingDeathEvent event) {
        if (!AEConfig.isEnabled(AEEnchantments.LAST_STAND)) return;
        if (!(event.getEntity() instanceof Player player)) return;
        if (!AEConfig.LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG.getAsBoolean()
                && event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return;

        Iterable<ItemStack> armorSlots = player.getArmorSlots();
        AtomicInteger totalEnchantmentLevel = new AtomicInteger();
        AEUtils.getEnchantmentHolder(AEEnchantments.LAST_STAND, player).ifPresent(holder -> {
            for (ItemStack armor : armorSlots) {
                int level = armor.getEnchantmentLevel(holder);
                if (level > 0) {
                    totalEnchantmentLevel.addAndGet(level);
                }
            }
        });
        if (totalEnchantmentLevel.get() <= 0) return;

        // default: 2000
        float baseXP = AEConfig.LAST_STAND_REQUIRED_BASE_EXPERIENCE.get();
        int required = Math.round(baseXP / totalEnchantmentLevel.get());
        if (!AEUtils.hasEnoughXPPoint(player.experienceProgress, player.experienceLevel, required)) return;
        player.giveExperiencePoints(-required);

        event.setCanceled(true);
        player.setHealth(1f);

        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.TOTEM_OF_UNDYING,
                    player.getX(),
                    player.getY() + 1.0D,
                    player.getZ(),
                    30,
                    0.5,
                    0.5,
                    0.5,
                    0.1);
            serverLevel.playSound(null, player.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }
}
