package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.ModEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IItemExtension.class)
public interface IItemExtensionMixin {
    // Compatible Enchantment
    @Inject(
            method = "supportsEnchantment",
            at = @At("RETURN"),
            cancellable = true
    )
    private void injectSupportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.COMPATIBLE.isTrue()) return;
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) return;
        Holder<Enchantment> compatible = server.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.COMPATIBLE);

        if (stack.getEnchantmentLevel(compatible) > 0) {
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "getEnchantmentValue",
            at = @At("RETURN"),
            cancellable = true
    )
    private void injectGetEnchantmentValue(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (!Config.COMPATIBLE.isTrue()) return;
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) return;
        Holder<Enchantment> compatible = server.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.COMPATIBLE);

        if (stack.getEnchantmentLevel(compatible) > 0) {
            cir.setReturnValue(cir.getReturnValue() * 5);
        }
    }
}
