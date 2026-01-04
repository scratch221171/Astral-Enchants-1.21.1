package net.scratch221171.astralenchant.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.registries.ModDataComponents;
import net.scratch221171.astralenchant.util.ProtectedItemState;
import net.scratch221171.astralenchant.util.ProtectedItemUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Inventory.class)
public abstract class InventoryMixin {

    @Shadow
    @Final
    public Player player;

    @Inject(
            method = "setItem(ILnet/minecraft/world/item/ItemStack;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onSetItem(int slot, ItemStack stack, CallbackInfo ci) {
        if (!(player instanceof ServerPlayer sp)) return;

        ProtectedItemState state = ProtectedItemUtil.getStateForSlot(sp, slot);
        if (state != null) {
            ci.cancel();
            ((Inventory)(Object)this).items.set(slot, ProtectedItemUtil.createDummy(state));
        }
    }
}
