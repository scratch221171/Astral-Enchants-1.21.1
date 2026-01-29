package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AstralEnchantUtils;
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
    private void astralEnchant$alwaysEdible(CallbackInfoReturnable<Boolean> cir) {
        Player player = (Player)(Object)this;
        Holder<Enchantment> enchantment = AstralEnchantUtils.getEnchantmentHolder(AEEnchantments.ENDLESS_APPETITE, player.level());
        if (EnchantmentHelper.getEnchantmentLevel(enchantment, player) > 0) {
            cir.setReturnValue(true);
        }
    }

    /**
     * {@link AEEnchantments#MOMENTUM} が付いている場合はクモの巣などの効果を無効化する。
     */
    @Inject(method = "makeStuckInBlock", at = @At("HEAD"), cancellable = true)
    private void astralEnchant$disableStuckInBlock(CallbackInfo ci) {
        // Dexterity によってクモの巣を無効化
        Player player = (Player)(Object)this;
        Holder<Enchantment> enchantment = AstralEnchantUtils.getEnchantmentHolder(AEEnchantments.MOMENTUM, player.level());
        if (EnchantmentHelper.getEnchantmentLevel(enchantment, player) > 0) {
            ci.cancel();
        }
    }

    /**
     * {@link AEEnchantments#ITEM_PROTECTION} が付いている場合はそのアイテムがあるスロットの置き換えを無効化する。
     */
    @Inject(method = "setItemSlot", at = @At("HEAD"), cancellable = true)
    private void astralEnchant$disableSetItemSlot(EquipmentSlot slot, ItemStack stack, CallbackInfo ci) {
        AstralEnchant.LOGGER.info("setItemSlot {} {}", slot, stack);
        Player player = (Player)(Object)this;
        Holder<Enchantment> enchantment = AstralEnchantUtils.getEnchantmentHolder(AEEnchantments.ITEM_PROTECTION, player.level());
        if (player.getItemBySlot(slot).getEnchantmentLevel(enchantment) > 0) {
            ci.cancel();
        }
    }
}
