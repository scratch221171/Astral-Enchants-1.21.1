package net.scratch221171.astralenchant.enchantment.itemprotection;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.enchantment.ModEnchantmentComponents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemProtectionRestoreQueue {
    private static final List<QueuedRestore> QUEUE = new ArrayList<>();

    public static void checkInv(Player player, Holder<Enchantment> itemProtection) {
        Inventory inv = player.getInventory(); // プレイヤーのインベントリ参照

        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getEnchantmentLevel(itemProtection) > 0 && !ItemProtectionRestoreQueue.isContained(stack)) {
                stack.set(ModEnchantmentComponents.IS_PROTECTED, true);
                ItemProtectionRestoreQueue.add(stack, stack.copy());
            }
            if (!isProtected(stack)) continue;

            // 現在のRestoreQueueに含まれている場合は保護しない
//            if (ItemProtectionRestoreQueue.isContained(stack)) continue;
//            ItemProtectionRestoreQueue.add(stack, stack.copy());
            ItemProtectionRestoreQueue.process();
        }
    }

    public static void add(ItemStack original, ItemStack saved) {
        AstralEnchant.LOGGER.info("added");
        QUEUE.removeIf(r -> r.original == original);
        QUEUE.add(new QueuedRestore(original, saved));
    }

    public static void process() {
        Iterator<QueuedRestore> it = QUEUE.iterator();
        AstralEnchant.LOGGER.info(String.valueOf(QUEUE.size()));

        while(it.hasNext()) {
            QueuedRestore r = it.next();

            ItemStack original = r.original;
            ItemStack saved = r.saved;

            AstralEnchant.LOGGER.info("restore");
            AstralEnchant.LOGGER.info(String.valueOf(original.isEmpty()));
            AstralEnchant.LOGGER.info(r.original.getComponents().toString());
            AstralEnchant.LOGGER.info(r.saved.getComponents().toString());

            if (r.original.isEmpty()) { it.remove(); continue; }
            if (ItemStack.isSameItemSameComponents(r.original, r.saved)) continue;

            AstralEnchant.LOGGER.info("different");
            // originalのコンポーネントをsavedで上書きする
            original.applyComponents(saved.getComponents());
            AstralEnchant.LOGGER.info(r.original.toString());
        }
        AstralEnchant.LOGGER.info("finish");
    }

    private static boolean isProtected(ItemStack stack) {
        return stack.getOrDefault(ModEnchantmentComponents.IS_PROTECTED, false);
    }

    public static boolean isContained(ItemStack stack) {
        for (QueuedRestore r : QUEUE) {
            if (r.original == stack) return true;
        }
        return false;
    }

    public static boolean hasBeenModified(ItemStack stack) {
        for (QueuedRestore r : QUEUE) {
            if (r.original == stack && !ItemStack.isSameItemSameComponents(r.original, r.saved)) return true;
        }
        return false;
    }

    private record QueuedRestore(ItemStack original, ItemStack saved) {}
}
