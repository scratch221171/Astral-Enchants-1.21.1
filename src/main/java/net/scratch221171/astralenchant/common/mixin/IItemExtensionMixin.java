package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AstralEnchantUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IItemExtension.class)
public interface IItemExtensionMixin {
    /**
     * {@link AEEnchantments#COMPATIBILITY} が付いている場合はバンドルに任意のエンチャントを付与可能できるようにする。
     */
    @Inject(method = "supportsEnchantment", at = @At("RETURN"), cancellable = true)
    private void astralEnchant$bundleSupportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.COMPATIBILITY.isTrue()) return;
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) return;
        Holder<Enchantment> compatible = AstralEnchantUtils.getEnchantmentHolderFromServer(AEEnchantments.COMPATIBILITY, server);
        if (stack.is(Items.BUNDLE) && stack.get(DataComponents.BUNDLE_CONTENTS) != BundleContents.EMPTY && stack.getEnchantmentLevel(compatible) > 0) {
            stack.set(DataComponents.REPAIR_COST, 0);
            cir.setReturnValue(true);
        }
    }
}
