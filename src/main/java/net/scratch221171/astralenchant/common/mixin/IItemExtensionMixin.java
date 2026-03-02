package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IItemExtension.class)
public interface IItemExtensionMixin {
    /**
     * {@link AEEnchantments#OVERLOAD} が付いている場合は {@link net.scratch221171.astralenchant.common.registries.AEDataComponents#OVERLOAD} の分だけ他のエンチャントのレベルに上乗せする。
     */
    @Inject(method = "getEnchantmentLevel", at = @At("RETURN"), cancellable = true)
    private void astralEnchant$getEnchantmentLevel(ItemStack stack, Holder<Enchantment> enchantment, CallbackInfoReturnable<Integer> cir) {
        if (Config.OVERLOAD.isFalse()) return;
        if (enchantment.getKey() == AEEnchantments.OVERLOAD) return;
        int level = cir.getReturnValue();
        if (level > 0) {
            cir.setReturnValue(level + stack.getOrDefault(AEDataComponents.OVERLOAD, 0));
        }
    }
}
