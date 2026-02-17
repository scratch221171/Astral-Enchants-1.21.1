package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderpearlItem.class)
public class EnderPearlItemMixin {
    /**
     * {@link AEEnchantments#INSTANT_TELEPORT} が付いている場合はエンダーパールの使用プロセスを丸ごと書き換える。
     */
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void astralEnchant$instantTeleport(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (Config.INSTANT_TELEPORT.isFalse()) return;
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.INSTANT_TELEPORT, level);
        int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(enchantment, player);
        if (enchantmentLevel > 0) {
            ItemStack itemstack = player.getItemInHand(hand);

            if (!level.isClientSide) {
                double maxDistance = 32 * enchantmentLevel;
                Vec3 start = player.getEyePosition(1.0F);
                Vec3 angle = player.getLookAngle();
                Vec3 end = start.add(angle.scale(maxDistance));

                // 最初の立ち位置からの衝突判定
                BlockHitResult result = level.clip(new ClipContext(start,end,ClipContext.Block.OUTLINE,ClipContext.Fluid.NONE,player));

                if (result.getType() == HitResult.Type.BLOCK) {
                    Vec3 tp;
                    if (player.isCrouching()) {
                        Vec3 current = result.getLocation();
                        maxDistance -= Math.sqrt(result.getBlockPos().distSqr(player.getOnPos()));
                        // スニークしている場合は衝突判定がなくなるまで(ブロックの外に出るまで)前進を繰り返す
                        BlockHitResult next = result;
                        for (; next.getType() != HitResult.Type.MISS; maxDistance -= 0.25) {
                            next = level.clip(new ClipContext(current,current.add(angle.scale(0.25)),ClipContext.Block.COLLIDER,ClipContext.Fluid.NONE,player));
                            if (maxDistance <= 0) {
                                player.displayClientMessage(Component.translatable("enchantment.astralenchant.instant_teleport.message.fail"), true);
                                cir.setReturnValue(InteractionResultHolder.fail(itemstack));
                                return;
                            }
                            current = current.add(angle.scale(0.25));
                        }
                        tp = next.getLocation();
                    } else {
                        tp = result.getBlockPos().relative(result.getDirection(), 1).getBottomCenter();
                    }
                    // テレポートに成功
                    level.playSound(null,player.getX(),player.getY(),player.getZ(),SoundEvents.ENDER_PEARL_THROW,SoundSource.NEUTRAL,0.5F,0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
                    player.getCooldowns().addCooldown(Items.ENDER_PEARL, 20);
                    player.teleportTo(tp.x, tp.y, tp.z);
                    player.setDeltaMovement(Vec3.ZERO);
                    player.resetFallDistance();
                    player.hurtMarked = true;
                    ((ServerLevel)level).sendParticles(ParticleTypes.PORTAL, tp.x, tp.y, tp.z, 32, 0, 0, 0, 0.5);
                    level.playSound(null, tp.x, tp.y, tp.z, SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
                    player.awardStat(Stats.ITEM_USED.get(Items.ENDER_PEARL));
                    itemstack.consume(1, player);
                } else {
                    // 空を向いているときなど、最初から判定がなかったとき
                    player.displayClientMessage(Component.translatable("enchantment.astralenchant.instant_teleport.message.fail"), true);
                    cir.setReturnValue(InteractionResultHolder.fail(itemstack));
                    return;
                }
            }
            cir.setReturnValue(InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide()));
        }
    }
}
