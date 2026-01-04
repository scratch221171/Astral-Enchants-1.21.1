package net.scratch221171.astralenchant.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.util.ProtectedItemState;
import net.scratch221171.astralenchant.util.ProtectedItemUtil;
import net.scratch221171.astralenchant.util.ServerPlayerMixinAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin implements ServerPlayerMixinAccess {
    @Unique
    private final List<ProtectedItemState> astralenchant$protectedItems = new ArrayList<>();

    @Override
    public List<ProtectedItemState> astralenchant$getProtectedItems() {
        return astralenchant$protectedItems;
    }

    @Override
    public void astralenchant$addProtectedItem(ProtectedItemState state) {
        astralenchant$protectedItems.add(state);
    }

    @Inject(
            method = "tick",
            at = @At("RETURN")
    )
    private void tick(CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer)(Object)this;
        ProtectedItemUtil.restoreAll(player, player.getInventory());
        AstralEnchant.LOGGER.info(player.getName() + " ticked!");
    }
}
