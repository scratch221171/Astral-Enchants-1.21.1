package net.scratch221171.astralenchant.enchantment.itemprotection;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.enchantment.ModEnchantmentComponents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemProtectionRestoreQueue {
    private static final List<QueuedRestore> QUEUE = new ArrayList<>();

    public static void tickInv(Player player, Holder<Enchantment> itemProtection) {
        Inventory inv = player.getInventory();

        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getEnchantmentLevel(itemProtection) > 0 && !ItemProtectionRestoreQueue.isContained(stack)) {
                stack.set(ModEnchantmentComponents.IS_PROTECTED, true);
                ItemProtectionRestoreQueue.add(stack, stack.copy(), player);
            }
            if (!isProtected(stack)) continue;

            ItemProtectionRestoreQueue.process();
        }
    }

    private static void add(ItemStack original, ItemStack saved, Player player) {
        QUEUE.removeIf(r -> r.original == original);
        QUEUE.add(new QueuedRestore(original, saved, player));
    }

    private static void process() {
        Iterator<QueuedRestore> it = QUEUE.iterator();

        while(it.hasNext()) {
            QueuedRestore r = it.next();

            ItemStack original = r.original;
            ItemStack saved = r.saved;

            if (!doesExistInInv(r.original, r.player)) { it.remove(); continue; }
            if (ItemStack.isSameItemSameComponents(r.original, r.saved)) continue;

            // originalのコンポーネントをsavedで上書きする
            original.applyComponents(saved.getComponents());
        }
    }

    private static boolean isProtected(ItemStack stack) {
        return stack.getOrDefault(ModEnchantmentComponents.IS_PROTECTED, false);
    }

    private static boolean isContained(ItemStack stack) {
        for (QueuedRestore r : QUEUE) {
            if (r.original == stack) return true;
        }
        return false;
    }

    private static boolean doesExistInInv(ItemStack stack, Player player) {
        Inventory inv = player.getInventory();
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack invStack = inv.getItem(i);
            if (invStack.isEmpty()) continue;
            if (invStack == stack) return true;
        }
        return false;
    }

    private record QueuedRestore(ItemStack original, ItemStack saved, Player player) {}
}
