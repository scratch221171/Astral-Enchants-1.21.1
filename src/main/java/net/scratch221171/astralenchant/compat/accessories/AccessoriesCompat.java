package net.scratch221171.astralenchant.compat.accessories;

import io.wispforest.accessories.api.attributes.AccessoryAttributeBuilder;
import io.wispforest.accessories.api.attributes.SlotAttribute;
import io.wispforest.accessories.api.events.AdjustAttributeModifierCallback;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;

public class AccessoriesCompat {
    public static void register(IEventBus eventBus) {
        AdjustAttributeModifierCallback.EVENT.register(AccessoriesCompat::expandSlot);
        AdjustAttributeModifierCallback.EVENT.register(
                EssenceOfEnchantmentAccessoriesCompatHandler::onAdjustAttributeModifier);
    }

    private static void expandSlot(ItemStack stack, SlotReference reference, AccessoryAttributeBuilder builder) {
        AEUtils.getEnchantmentHolder(AEEnchantments.SLOT_EXPANSION, reference.entity())
                .map(stack::getEnchantmentLevel)
                .filter(level -> !stack.isEmpty() && level > 0)
                .ifPresent(level -> builder.addStackable(
                        SlotAttribute.getAttributeHolder(reference.slotName()),
                        AstralEnchant.id("se_bonus"),
                        level,
                        AttributeModifier.Operation.ADD_VALUE));
    }
}
