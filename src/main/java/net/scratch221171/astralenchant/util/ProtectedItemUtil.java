package net.scratch221171.astralenchant.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.registries.ModDataComponents;

public final class ProtectedItemUtil {
    public static boolean isProtected(ItemStack stack) {
        return stack.has(ModDataComponents.IS_PROTECTED);
    }

    public static ProtectedItemState getStateForSlot(
            ServerPlayer sp, int slot
    ) {
        ServerPlayerMixinAccess access =
                (ServerPlayerMixinAccess) sp;

        for (ProtectedItemState state : access.astralenchant$getProtectedItems()) {
            if (state.slot() == slot) return state;
        }
        return null;
    }

    public static void restoreAll(ServerPlayer sp, Inventory inv) {
        for (ProtectedItemState state : ((ServerPlayerMixinAccess)sp).astralenchant$getProtectedItems()) {
            inv.items.set(state.slot(), ProtectedItemUtil.createDummy(state));
        }
        AstralEnchant.LOGGER.info(((ServerPlayerMixinAccess)sp).astralenchant$getProtectedItems().size() + " restored!");
    }

    public static ItemStack createDummy(ProtectedItemState state) {
        ItemStack stack = new ItemStack(state.item());
        stack.applyComponents(state.components());
        stack.set(ModDataComponents.UUID, state.id());
        return stack;
    }
}
