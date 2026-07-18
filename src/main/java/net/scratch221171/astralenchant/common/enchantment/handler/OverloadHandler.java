package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.event.ItemEnchantmentSetEvent;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;
import net.scratch221171.astralenchant.common.util.AEUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class OverloadHandler {

    @SubscribeEvent
    private static void applyOverload(ItemEnchantmentSetEvent event) {
        if (!AEConfig.isEnabled(AEEnchantments.OVERLOAD)) return;

        ItemStack stack = event.getStack();
        ItemEnchantments enchantments = event.getEnchantments();

        int level = AEUtils.getEnchantmentLevel(enchantments, AEEnchantments.OVERLOAD);
        if (level <= 0) return;

        ItemEnchantments.Mutable filtered = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        enchantments.entrySet().stream()
                .filter(e -> !e.getKey().is(AEEnchantments.OVERLOAD))
                .forEach(e -> filtered.set(e.getKey(), e.getIntValue()));

        stack.set(DataComponents.ENCHANTMENTS, filtered.toImmutable());
        stack.set(AEDataComponents.OVERLOAD, stack.getOrDefault(AEDataComponents.OVERLOAD, 0) + level);
        event.setCanceled(true);
    }

    @SubscribeEvent
    private static void modifyTooltip(ItemTooltipEvent event) {
        if (!AEConfig.isEnabled(AEEnchantments.OVERLOAD)) return;

        ItemStack stack = event.getItemStack();
        int level = stack.getOrDefault(AEDataComponents.OVERLOAD, 0);

        if (level > 0) {
            String text = Component.translatable("enchantment.astralenchant.overload.tooltip.text", level)
                    .getString();

            event.getToolTip().add(createRainbowGradient(text));
        }
    }

    private static Component createRainbowGradient(String text) {

        MutableComponent result = Component.empty();

        long time = System.currentTimeMillis();
        float baseHue = (time % 4000) / 4000f;

        int length = text.length();

        for (int i = 0; i < length; i++) {

            float hue = (baseHue - (float) i / length) % 1f;

            int color = java.awt.Color.HSBtoRGB(hue, 1f, 1f) & 0xFFFFFF;

            result.append(Component.literal(String.valueOf(text.charAt(i))).withColor(color));
        }

        return result;
    }
}
