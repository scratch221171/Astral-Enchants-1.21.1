package net.scratch221171.astralenchant.util;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;

import java.util.UUID;

public record ProtectedItemState(UUID id, Item item, int slot, DataComponentMap components) {
}
