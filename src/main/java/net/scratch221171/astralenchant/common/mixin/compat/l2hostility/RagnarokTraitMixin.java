package net.scratch221171.astralenchant.common.mixin.compat.l2hostility;

import dev.xkmc.l2hostility.compat.curios.EntitySlotAccess;
import dev.xkmc.l2hostility.content.traits.legendary.RagnarokTrait;
import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.common.config.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RagnarokTrait.class)
public class RagnarokTraitMixin {

    @Inject(
            method = "allowSeal",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void preventBeingSealed(EntitySlotAccess access, CallbackInfoReturnable<Boolean> cir) {
        if (Config.ITEM_PROTECTION.isFalse()) return;
        ItemStack stack = access.get();
        if (AEUtils.getEnchantmentLevelFromNBT(stack, AEEnchantments.ITEM_PROTECTION) > 0) {
            cir.setReturnValue(false);
        }
    }
}
