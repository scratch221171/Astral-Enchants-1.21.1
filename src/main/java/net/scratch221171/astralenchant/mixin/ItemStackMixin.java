package net.scratch221171.astralenchant.mixin;

import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.util.ProtectedItemUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin{
    @Inject(
            method = "applyComponents*",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onApplyComponents(CallbackInfo ci) {
        ItemStack self = (ItemStack)(Object)this;
        if (ProtectedItemUtil.isProtected(self)) {
            ci.cancel();
        }
    }

    @Inject(
            method = "set",
            at = @At("HEAD"),
            cancellable = true
    )
    private <T> void onSet(DataComponentType<? super T> component, T value, CallbackInfoReturnable<T> cir) {
        ItemStack self = (ItemStack)(Object)this;

        if (ProtectedItemUtil.isProtected(self)) {
            cir.setReturnValue(value);
        }
    }
}
