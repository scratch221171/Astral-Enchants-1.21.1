package net.scratch221171.astralenchant.common.mixin.compat.l2hostility;

import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "dev.xkmc.l2hostility.content.traits.legendary.DispellTrait")
public abstract class DispellTraitMixin {
    @Redirect(
            method = "postHurtImpl",
            at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 0))
    private boolean preventEnchantmentsFromBeingDisabled(List<ItemStack> list, Object obj) {
        ItemStack stack = (ItemStack) obj;
        if (AEUtils.getEnchantmentLevel(stack, AEEnchantments.ITEM_PROTECTION) > 0) {
            return false;
        }
        return list.add(stack);
    }
}
