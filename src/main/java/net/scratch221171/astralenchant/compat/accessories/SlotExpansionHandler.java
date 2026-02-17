package net.scratch221171.astralenchant.compat.accessories;

import io.wispforest.accessories.api.attributes.AccessoryAttributeBuilder;
import io.wispforest.accessories.api.attributes.SlotAttribute;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;

public class SlotExpansionHandler {
    /**
     * {@link AEEnchantments#SLOT_EXPANSION} が付いていた場合スロット数を増やす。
     */
    public static void onAdjustAttributeModifier(ItemStack stack, SlotReference reference, AccessoryAttributeBuilder builder) {
        if (Config.SLOT_EXPANSION.isFalse()) return;
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.SLOT_EXPANSION, reference.entity().level());
        int  level = stack.getEnchantmentLevel(enchantment);
        if (!stack.isEmpty() && level > 0) {
            builder.addStackable(
                    SlotAttribute.getAttributeHolder(reference.slotName()),
                    ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "sp_bonus"),
                    level,
                    AttributeModifier.Operation.ADD_VALUE);
        }
    }
}
