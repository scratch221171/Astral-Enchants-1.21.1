package net.scratch221171.astralenchant.common.mixin.minecraft;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
    /**
     * {@link AEEnchantments#MOMENTUM} が付いている場合はアイテム使用中の移動速度低下を無効化する。
     */
    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"))
    private boolean astralenchant$disableUsingItemSlowdown(LocalPlayer instance) {
        if (RuntimeConfigState.get(AEConfig.MOMENTUM)) {
            Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.MOMENTUM, instance.level());
            if (EnchantmentHelper.getEnchantmentLevel(enchantment, instance) > 0) return false;
        }
        return instance.isUsingItem();
    }

    /**
     * {@link AEEnchantments#MOMENTUM} が付いている場合はアイテム使用中でも走り始められるようにする。
     */
    @Redirect(method = "canStartSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"))
    private boolean astralenchant$enableSprintingWhileUsingItem(LocalPlayer instance) {
        if (RuntimeConfigState.get(AEConfig.MOMENTUM)) {
            Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.MOMENTUM, instance.level());
            if (EnchantmentHelper.getEnchantmentLevel(enchantment, instance) > 0) return false;
        }
        return instance.isUsingItem();
    }
}
