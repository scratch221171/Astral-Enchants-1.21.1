package net.scratch221171.astralenchant.common.mixin.compat.l2hostility;

import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.common.config.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(targets = "dev.xkmc.l2hostility.content.traits.legendary.DispellTrait")
public class DispellTraitMixin {

    @Redirect(
            method = "postHurtImpl",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    ordinal = 0
            )
    )
    private boolean preventEnchantmentsFromBeingDisabled(List<ItemStack> list, Object obj) {
        ItemStack stack = (ItemStack) obj;
        if (Config.ITEM_PROTECTION.isTrue()) {

            if (AEUtils.getEnchantmentLevelFromNBT(stack, AEEnchantments.ITEM_PROTECTION) > 0) {
                return false;
            }
        }

        return list.add(stack);
    }
}
