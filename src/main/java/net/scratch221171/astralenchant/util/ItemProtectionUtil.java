package net.scratch221171.astralenchant.util;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemProtectionUtil {
    private static final Map<ItemStack, DataComponentMap> cache = new HashMap<>();

    public static void add(ItemStack stack) { cache.put(stack, ((PatchedDataComponentMap)stack.copy().getComponents()).copy()); }
    public static void remove(ItemStack stack) { cache.remove(stack); }

    public static boolean isContained(ItemStack itemStack) { return cache.containsKey(itemStack); }
    public static DataComponentMap get(ItemStack itemStack) { return cache.get(itemStack); }
}
