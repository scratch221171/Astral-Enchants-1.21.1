package net.scratch221171.astralenchant.common.mixin.minecraft;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {
    /**
     * {@link AEEnchantments#ENDLESS_APPETITE} が付いている場合は常に食事可能になる。
     */
    @Inject(method = "canEat", at = @At("RETURN"), cancellable = true)
    private void astralenchant$alwaysEdible(CallbackInfoReturnable<Boolean> cir) {
        if (!RuntimeConfigState.get(AEConfig.ENDLESS_APPETITE)) return;
        Player player = (Player)(Object)this;
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.ENDLESS_APPETITE, player.level());
        if (EnchantmentHelper.getEnchantmentLevel(enchantment, player) > 0) {
            cir.setReturnValue(true);
        }
    }

    /**
     * {@link AEEnchantments#MOMENTUM} が付いている場合はクモの巣などの効果を無効化する。
     */
    @Inject(method = "makeStuckInBlock", at = @At("HEAD"), cancellable = true)
    private void astralenchant$disableStuckInBlock(CallbackInfo ci) {
        if (!RuntimeConfigState.get(AEConfig.MOMENTUM)) return;
        Player player = (Player)(Object)this;
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.MOMENTUM, player.level());
        if (EnchantmentHelper.getEnchantmentLevel(enchantment, player) > 0) {
            ci.cancel();
        }
    }
}
