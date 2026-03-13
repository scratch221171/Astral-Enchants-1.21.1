package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class OverloadHandler {

//    @SubscribeEvent
//    private static void modifyTooltip(ItemTooltipEvent event) {
//        if (!RuntimeConfigState.get(AEConfig.OVERLOAD)) return;
//        ItemStack stack = event.getItemStack();
//        int level = stack.getOrDefault(AEDataComponents.OVERLOAD, 0);
//        if (level > 0) {
//            event.getToolTip().add(Component.translatable("enchantment.astralenchant.overload.tooltip.text", level).withColor(getRainbowColor()));
//        }
//    }
//
//    // Shiny!!
//    private static int getRainbowColor() {
//        float hue = System.currentTimeMillis() % 1800 / 1800f;
//        return java.awt.Color.HSBtoRGB(hue, 1.0f, 1.0f) & 0xFFFFFF;
//    }

    @SubscribeEvent
    private static void modifyTooltip(ItemTooltipEvent event) {
        if (!RuntimeConfigState.get(AEConfig.OVERLOAD)) return;

        ItemStack stack = event.getItemStack();
        int level = stack.getOrDefault(AEDataComponents.OVERLOAD, 0);

        if (level > 0) {
            String text = Component.translatable(
                    "enchantment.astralenchant.overload.tooltip.text", level
            ).getString();

            event.getToolTip().add(createRainbowGradient(text));
        }
    }

    private static Component createRainbowGradient(String text) {

        MutableComponent result = Component.empty();

        long time = System.currentTimeMillis();
        float baseHue = (time % 4000) / 4000f;

        int length = text.length();

        for (int i = 0; i < length; i++) {

            float hue = (baseHue - (float)i / length) % 1f;

            int color = java.awt.Color.HSBtoRGB(hue, 1f, 1f) & 0xFFFFFF;

            result.append(
                    Component.literal(String.valueOf(text.charAt(i)))
                            .withColor(color)
            );
        }

        return result;
    }
}
