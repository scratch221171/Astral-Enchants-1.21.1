package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class OverloadHandler {
    @SubscribeEvent
    private static void modifyTooltip(ItemTooltipEvent event) {
        if (!Config.OVERLOAD.isTrue()) return;
        ItemStack stack = event.getItemStack();
        int level = stack.getOrDefault(AEDataComponents.OVERLOAD, 0);
        if (level > 0) {
            event.getToolTip().add(Component.translatable("enchantment.astralenchant.overload.tooltip.text", level).withColor(getRainbowColor()));
        }
    }

    // Shiny!!
    private static int getRainbowColor() {
        float hue = System.currentTimeMillis() % 1800 / 1800f;
        return java.awt.Color.HSBtoRGB(hue, 1.0f, 1.0f) & 0xFFFFFF;
    }
}
