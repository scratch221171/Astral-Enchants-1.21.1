package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class InstantTeleportHandler {

    @SubscribeEvent
    private static void onUsingEnderPearl(PlayerInteractEvent.RightClickItem event) {
        if (Config.INSTANT_TELEPORT.isFalse()) return;

        ItemStack stack = event.getItemStack();
        if (!stack.is(Items.ENDER_PEARL)) return;

        Player player = event.getEntity();
        Level level = event.getLevel();

        Holder<Enchantment> enchantment =
                AEUtils.getEnchantmentHolder(AEEnchantments.INSTANT_TELEPORT, level);
        int levelValue = EnchantmentHelper.getEnchantmentLevel(enchantment, player);
        if (levelValue <= 0) return;

        if (level.isClientSide) {
            cancel(event, InteractionResult.SUCCESS);
            return;
        }

        boolean success = tryInstantTeleport(player, level, levelValue, stack, event);
        cancel(event, success ? InteractionResult.SUCCESS : InteractionResult.FAIL);
    }

    private static boolean tryInstantTeleport(
            Player player,
            Level level,
            int enchantmentLevel,
            ItemStack stack,
            PlayerInteractEvent.RightClickItem event
    ) {
        double maxDistance = 32 * enchantmentLevel;
        Vec3 start = player.getEyePosition(1.0F);
        Vec3 direction = player.getLookAngle();
        Vec3 end = start.add(direction.scale(maxDistance));

        BlockHitResult hit = level.clip(
                new ClipContext(start, end, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)
        );

        if (hit.getType() != HitResult.Type.BLOCK) {
            notifyFail(player, event);
            return false;
        }

        Vec3 target = player.isCrouching()
                ? findSneakTarget(player, level, hit, direction, maxDistance, event)
                : hit.getBlockPos().relative(hit.getDirection(), 1).getBottomCenter();

        if (target == null) {
            return false;
        }

        performTeleport(player, level, target, stack);
        return true;
    }

    private static Vec3 findSneakTarget(
            Player player,
            Level level,
            BlockHitResult hit,
            Vec3 direction,
            double maxDistance,
            PlayerInteractEvent.RightClickItem event
    ) {
        Vec3 current = hit.getLocation();
        double remaining =
                maxDistance - Math.sqrt(hit.getBlockPos().distSqr(player.getOnPos()));

        while (remaining > 0) {
            BlockHitResult next = level.clip(
                    new ClipContext(
                            current,
                            current.add(direction.scale(0.25)),
                            ClipContext.Block.COLLIDER,
                            ClipContext.Fluid.NONE,
                            player
                    )
            );

            if (next.getType() == HitResult.Type.MISS) {
                return next.getLocation();
            }

            current = current.add(direction.scale(0.25));
            remaining -= 0.25;
        }

        notifyFail(player, event);
        return null;
    }

    private static void performTeleport(
            Player player,
            Level level,
            Vec3 target,
            ItemStack stack
    ) {
        level.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENDER_PEARL_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        player.getCooldowns().addCooldown(Items.ENDER_PEARL, 20);
        player.teleportTo(target.x, target.y, target.z);
        player.setDeltaMovement(Vec3.ZERO);
        player.resetFallDistance();
        player.hurtMarked = true;

        ((ServerLevel) level).sendParticles(
                ParticleTypes.PORTAL,
                target.x, target.y, target.z,
                32, 0, 0, 0, 0.5
        );

        level.playSound(
                null,
                target.x, target.y, target.z,
                SoundEvents.PLAYER_TELEPORT,
                SoundSource.PLAYERS
        );

        player.awardStat(Stats.ITEM_USED.get(Items.ENDER_PEARL));
        stack.consume(1, player);
    }

    private static void notifyFail(
            Player player,
            PlayerInteractEvent.RightClickItem event
    ) {
        player.displayClientMessage(
                Component.translatable("enchantment.astralenchant.instant_teleport.message.fail"),
                true
        );
        cancel(event, InteractionResult.FAIL);
    }

    private static void cancel(
            PlayerInteractEvent.RightClickItem event,
            InteractionResult result
    ) {
        event.setCancellationResult(result);
        event.setCanceled(true);
    }
}
