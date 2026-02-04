package net.scratch221171.astralenchant.compat.accessories;

import io.wispforest.accessories.api.events.AccessoryChangeCallback;
import net.neoforged.bus.api.IEventBus;

public class AccessoriesCompat {
    public static void register(IEventBus bus) {
        AccessoryChangeCallback.EVENT.register(AccessoriesCompatHandler::onAccessoryChanged);
    }
}
