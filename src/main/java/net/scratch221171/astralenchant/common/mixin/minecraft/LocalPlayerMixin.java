package net.scratch221171.astralenchant.common.mixin.minecraft;

import net.minecraft.client.player.LocalPlayer;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
    /** {@link AEEnchantments#MOMENTUM} が付いている場合は，アイテム使用中の移動速度低下を無効にし，アイテム使用中でも走り始められるようにする。 */
    @Redirect(
            method = {"aiStep", "canStartSprinting"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"))
    private boolean astralenchant$disableUsingItemSlowdown(LocalPlayer instance) {
        return AEUtils.getEnchantmentLevel(AEEnchantments.MOMENTUM, instance) <= 0 && instance.isUsingItem();
    }
}
