package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.ModEnchantments;
import net.scratch221171.astralenchant.common.util.AstralEnchantUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class LastStandHandler {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!Config.LAST_STAND.isTrue()) return;
        if (!(event.getEntity() instanceof Player)) return;
        if (event.getSource().is(DamageTypes.GENERIC_KILL) || event.getSource().is(DamageTypes.FELL_OUT_OF_WORLD)) return;

        LivingEntity entity = event.getEntity();
        Iterable<ItemStack> armorSlots = entity.getArmorSlots();

        Holder<Enchantment> enchantment = AstralEnchantUtils.getEnchantmentHolder(ModEnchantments.LAST_STAND, entity.level());
        int totalLevel = 0;
        for (ItemStack armor : armorSlots) {
            int level = armor.getEnchantmentLevel(enchantment);
            if (level > 0) {
                totalLevel += level;
            }
        }
        if (totalLevel <= 0) return;

        Player player = (Player) entity;
        int neededExperienceLevels = (int)Math.floor(30f/totalLevel);
        if (player.experienceLevel < neededExperienceLevels) return;

        event.setCanceled(true);
        entity.setHealth(1f);
        player.giveExperienceLevels(-neededExperienceLevels);

        if (entity.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.TOTEM_OF_UNDYING,
                    entity.getX(), entity.getY() + 1.0D, entity.getZ(),
                    30,
                    0.5, 0.5, 0.5,
                    0.1
            );
            serverLevel.playSound(
                    null,
                    entity.blockPosition(),
                    SoundEvents.TOTEM_USE,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
        }
    }
}
