package net.scratch221171.astralenchant.common.util;

import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemProtectionUtil {
    private static final Map<UUID, ItemStack> cache = new HashMap<>();

    public static void add(UUID uuid, ItemStack copy) { cache.put(uuid, copy); }

    public static boolean isContained(UUID uuid) { return cache.containsKey(uuid); }
    public static ItemStack get(UUID uuid) { return cache.get(uuid); }
}
