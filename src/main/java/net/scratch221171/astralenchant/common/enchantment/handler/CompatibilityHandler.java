package net.scratch221171.astralenchant.common.enchantment.handler;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.event.ItemEnchantmentSetEvent;
import net.scratch221171.astralenchant.common.util.AEUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class CompatibilityHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void handleBundle(ItemEnchantmentSetEvent event) {
        if (!AEConfig.isEnabled(AEEnchantments.COMPATIBILITY)) return;

        ItemStack stack = event.getStack();
        ItemEnchantments enchantments = event.getEnchantments();

        if (!stack.is(Items.BUNDLE)) return;
        if (AEUtils.getEnchantmentLevel(stack, AEEnchantments.COMPATIBILITY) <= 0) return;

        BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        if (contents.isEmpty()) return;

        ItemEnchantments.Mutable added = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        // 現在のバンドルになければ、新しく付けられたものだと判断する
        enchantments.entrySet().stream()
                .filter(e -> !e.getKey().is(AEEnchantments.COMPATIBILITY) && stack.getEnchantmentLevel(e.getKey()) <= 0)
                .forEach(e -> added.set(e.getKey(), e.getIntValue()));

        List<ItemStack> newItems = new ArrayList<>();
        // 全ての内容物に対してそれぞれマージ
        for (ItemStack item : contents.items()) {
            ItemStack copy = item.copy();
            ItemEnchantments current = copy.get(DataComponents.ENCHANTMENTS);
            if (current != null) {
                copy.set(DataComponents.ENCHANTMENTS, AEUtils.mergeItemEnchants(added.toImmutable(), current));
            }
            newItems.add(copy);
        }

        // バンドルの内容物をまとめて書き換え
        stack.set(DataComponents.BUNDLE_CONTENTS, new BundleContents(newItems));
        event.setCanceled(true);
    }
}
