package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.scratch221171.astralenchant.common.datagen.ModEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {
    /**
     * {@link ModEnchantments#ENDLESS_APPETITE} が付いている場合は常に食事可能になる。
     */
    @Inject(method = "canEat", at = @At("RETURN"), cancellable = true)
    private void astralEnchant$alwaysEdible(CallbackInfoReturnable<Boolean> cir) {
        Player player = (Player)(Object)this;
        Holder<Enchantment> enchantment = player.level().registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.ENDLESS_APPETITE);
        if (EnchantmentHelper.getEnchantmentLevel(enchantment, player) > 0) {
            cir.setReturnValue(true);
        }
    }

    /**
     * {@link ModEnchantments#MOMENTUM} が付いている場合はクモの巣などの効果を無効化する。
     */
    @Inject(method = "makeStuckInBlock", at = @At("HEAD"), cancellable = true)
    private void astralEnchant$disableStuckInBlock(CallbackInfo ci) {
        // Dexterity によってクモの巣を無効化
        Player player = (Player)(Object)this;
        Holder<Enchantment> enchantment = player.level().registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.MOMENTUM);
        if (EnchantmentHelper.getEnchantmentLevel(enchantment, player) > 0) {
            ci.cancel();
        }
    }
}
