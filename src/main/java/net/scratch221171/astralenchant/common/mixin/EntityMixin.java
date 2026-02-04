package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    /**
     * {@link AEEnchantments#MOMENTUM} が付いている場合はハチミツブロックなどによるジャンプ力低下を無効化する。
     */
    @Inject(method = "getBlockJumpFactor", at = @At("HEAD"), cancellable = true)
    private void astralEnchant$modifyJumpFactor(CallbackInfoReturnable<Float> cir) {
        if (!Config.MOMENTUM.isTrue()) return;
        Entity self = (Entity)(Object)this;
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.MOMENTUM, self.level());
        if (self instanceof Player player && EnchantmentHelper.getEnchantmentLevel(enchantment, player) > 0) {
            cir.setReturnValue(1.0f);
        }
    }

    /**
     * {@link AEEnchantments#MOMENTUM} が付いている場合はソウルサンドなどによる移動速度低下を無効化する。
     */
    @Inject(method = "getBlockSpeedFactor", at = @At("HEAD"), cancellable = true)
    private void astralEnchant$modifySpeedFactor(CallbackInfoReturnable<Float> cir) {
        if (!Config.MOMENTUM.isTrue()) return;
        Entity self = (Entity)(Object)this;
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.MOMENTUM, self.level());
        if (self instanceof Player player && EnchantmentHelper.getEnchantmentLevel(enchantment, player) > 0) {
            cir.setReturnValue(1.0f);
        }
    }
}
