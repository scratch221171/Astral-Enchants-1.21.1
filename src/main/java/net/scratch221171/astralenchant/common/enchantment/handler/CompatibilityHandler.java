package net.scratch221171.astralenchant.common.enchantment.handler;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class CompatibilityHandler {

    @SubscribeEvent
    private static void onApplyEnchantment(AnvilUpdateEvent event) {
        if (Config.COMPATIBILITY.isFalse()) return;

        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        if(!left.is(Items.BUNDLE) || !right.is(Items.ENCHANTED_BOOK)) return;

        Set<Object2IntMap.Entry<Holder<Enchantment>>> added = right.getOrDefault(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY).entrySet();
        if (added.isEmpty()) return;

        Holder<Enchantment> compatibility = AEUtils.getEnchantmentHolder(AEEnchantments.COMPATIBILITY, event.getPlayer().level());
        if (left.getEnchantmentLevel(compatibility) <= 0) return;

        BundleContents contents = left.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        if (contents.isEmpty()) return;

        int totalCost = 0;
        List<ItemStack> newItems = new ArrayList<>();
        for (ItemStack item : contents.items()) {
            ItemStack copy = item.copy();
            Set<Object2IntMap.Entry<Holder<Enchantment>>> current = copy.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY).entrySet();
            totalCost += calculateCost(added, current, right.has(DataComponents.STORED_ENCHANTMENTS));
            copy.set(DataComponents.ENCHANTMENTS, merge(added, current));
            newItems.add(copy);
        }

        ItemStack output = left.copy();
        output.set(DataComponents.BUNDLE_CONTENTS, new BundleContents(newItems));
        event.setOutput(output);
        event.setCost(totalCost);
        event.setMaterialCost(1);
    }

    private static ItemEnchantments merge(
            Set<Object2IntMap.Entry<Holder<Enchantment>>> added,
            Set<Object2IntMap.Entry<Holder<Enchantment>>> current
    ) {
        ItemEnchantments.Mutable result = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        // 既存エンチャを先にコピー
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : current) {
            result.set(entry.getKey(), entry.getIntValue());
        }

        // 追加エンチャをマージ
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : added) {
            Holder<Enchantment> enchant = entry.getKey();
            int addedLevel = entry.getIntValue();
            int currentLevel = result.getLevel(enchant);
            int newLevel;

            if (currentLevel <= 0) {
                newLevel = addedLevel;
            } else if (currentLevel == addedLevel) {
                newLevel = Math.min(currentLevel + 1, enchant.value().getMaxLevel());
            } else {
                newLevel = Math.max(currentLevel, addedLevel);
            }

            result.set(enchant, newLevel);
        }

        return result.toImmutable();
    }

    private static int calculateCost(
            Set<Object2IntMap.Entry<Holder<Enchantment>>> added,
            Set<Object2IntMap.Entry<Holder<Enchantment>>> current,
            boolean isHalf
    ) {
        int cost = 0;

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : added) {
            Holder<Enchantment> enchant = entry.getKey();
            int addedLevel = entry.getIntValue();

            int currentLevel = current.stream()
                    .filter(e -> e.getKey().equals(enchant))
                    .mapToInt(Object2IntMap.Entry::getIntValue)
                    .findFirst()
                    .orElse(0);

            int resultLevel;

            if (currentLevel == 0) {
                resultLevel = addedLevel;
            } else if (currentLevel == addedLevel) {
                resultLevel = Math.min(
                        currentLevel + 1,
                        enchant.value().getMaxLevel()
                );
            } else {
                resultLevel = Math.max(currentLevel, addedLevel);
            }

            int rarityCost = Math.max(1, isHalf ? enchant.value().getAnvilCost() / 2 : enchant.value().getAnvilCost());
            cost += rarityCost * resultLevel;
        }

        return cost;
    }
}
