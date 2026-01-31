package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AstralEnchantUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
    /**
     * {@link AEEnchantments#MOMENTUM} が付いている場合はアイテム使用中の移動速度低下を無効化する。
     */
    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"))
    private boolean astralEnchant$disableUsingItemSlowdown(LocalPlayer instance) {
        if (Config.MOMENTUM.isTrue()) {
            Holder<Enchantment> enchantment = AstralEnchantUtils.getEnchantmentHolder(AEEnchantments.MOMENTUM, instance.level());
            if (EnchantmentHelper.getEnchantmentLevel(enchantment, instance) > 0) return false;
        }
        return instance.isUsingItem();
    }

    /**
     * {@link AEEnchantments#MOMENTUM} が付いている場合はアイテム使用中でも走り始められるようにする。
     */
    @Redirect(method = "canStartSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"))
    private boolean astralEnchant$enableSprintingWhileUsingItem(LocalPlayer instance) {
        if (!Config.MOMENTUM.isTrue()) {
            Holder<Enchantment> enchantment = AstralEnchantUtils.getEnchantmentHolder(AEEnchantments.MOMENTUM, instance.level());
            if (EnchantmentHelper.getEnchantmentLevel(enchantment, instance) > 0) return false;
        }
        return instance.isUsingItem();
    }
}
