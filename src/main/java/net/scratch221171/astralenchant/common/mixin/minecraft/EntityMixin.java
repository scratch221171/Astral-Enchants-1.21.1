package net.scratch221171.astralenchant.common.mixin.minecraft;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    /** {@link AEEnchantments#MOMENTUM} が付いている場合はハチミツブロックなどによるジャンプ力低下を無効化する。 */
    @Inject(method = "getBlockJumpFactor", at = @At("HEAD"), cancellable = true)
    private void astralenchant$modifyJumpFactor(CallbackInfoReturnable<Float> cir) {
        Entity self = (Entity) (Object) this;
        if (self instanceof Player player) {
            if (AEUtils.getEnchantmentLevel(AEEnchantments.MOMENTUM, player) > 0) {
                cir.setReturnValue(1.0f);
            }
        }
    }

    /** {@link AEEnchantments#MOMENTUM} が付いている場合はソウルサンドなどによる移動速度低下を無効化する。 */
    @Inject(method = "getBlockSpeedFactor", at = @At("HEAD"), cancellable = true)
    private void astralenchant$modifySpeedFactor(CallbackInfoReturnable<Float> cir) {
        Entity self = (Entity) (Object) this;
        if (self instanceof Player player) {
            if (AEUtils.getEnchantmentLevel(AEEnchantments.MOMENTUM, player) > 0) {
                cir.setReturnValue(1.0f);
            }
        }
    }
}
