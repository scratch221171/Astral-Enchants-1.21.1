package net.scratch221171.astralenchant.compat.accessories;

import io.wispforest.accessories.api.events.AdjustAttributeModifierCallback;
import net.neoforged.bus.api.IEventBus;

public class AccessoriesCompat {
    public static void register(IEventBus bus) {
        AdjustAttributeModifierCallback.EVENT.register(SlotExpansionHandler::onAdjustAttributeModifier);
        AdjustAttributeModifierCallback.EVENT.register(EssenceOfEnchantmentAccessoriesCompatHandler::onAdjustAttributeModifier);
    }
}
