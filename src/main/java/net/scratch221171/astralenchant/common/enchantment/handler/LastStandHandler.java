package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class LastStandHandler {

    @SubscribeEvent
    private static void onLivingDeath(LivingDeathEvent event) {
        if (Config.LAST_STAND.isFalse()) return;
        if (!(event.getEntity() instanceof Player player)) return;
        if (Config.LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG.isFalse() && event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return;

        Iterable<ItemStack> armorSlots = player.getArmorSlots();
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.LAST_STAND, player.level());
        int totalLevel = 0;
        for (ItemStack armor : armorSlots) {
            int level = armor.getEnchantmentLevel(enchantment);
            if (level > 0) {
                totalLevel += level;
            }
        }
        if (totalLevel <= 0) return;

        // default: 2000
        int baseXP = Config.LAST_STAND_REQUIRED_BASE_EXPERIENCE.getAsInt();
        int required = Math.round((float) (baseXP / totalLevel));
        if (getTotalPoint(player.experienceProgress, player.experienceLevel) < required) return;
        player.giveExperiencePoints(-required);

        event.setCanceled(true);
        player.setHealth(1f);

        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.TOTEM_OF_UNDYING,
                    player.getX(), player.getY() + 1.0D, player.getZ(),
                    30,
                    0.5, 0.5, 0.5,
                    0.1
            );
            serverLevel.playSound(
                    null,
                    player.blockPosition(),
                    SoundEvents.TOTEM_USE,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
        }
    }
    
    private static int getTotalPoint(float progress, int level) {
        int total = 0;
        for (int i = 0; i < level; i++) {
            total += getXpNeededForNextLevel(i);
        }
        // ほぼintなのでround
        total += Math.round(progress * getXpNeededForNextLevel(level));
        return total;
    }

    private static int getXpNeededForNextLevel(int j) {
        if (j >= 30) {
            return 112 + (j - 30) * 9;
        } else {
            return j >= 15 ? 37 + (j - 15) * 5 : 7 + j * 2;
        }
    }
}
