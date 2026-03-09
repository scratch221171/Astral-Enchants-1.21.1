package net.scratch221171.astralenchant.common.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;

import java.util.List;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class DisabledEnchantmentTooltip {

    @SubscribeEvent
    private static void onTooltip(ItemTooltipEvent event) {
        List<Component> tooltip = event.getToolTip();

        for (int i = 0; i < tooltip.size(); i++) {
            Component entry = tooltip.get(i);

            if (!(entry.getContents() instanceof TranslatableContents contents)) continue;
            String key = contents.getKey();

            if (key.startsWith("enchantment.")) {

                String[] split = key.split("\\.", 3);

                String path = split[2];

                Object value = RuntimeConfigState.get(path);
                if (value instanceof Boolean enabled && !enabled) {
                    tooltip.set(i, createDisabledComponent(entry));
                }
            }
        }
    }

    private static MutableComponent createDisabledComponent(Component original) {
        MutableComponent component = original.copy();
        component.setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withStrikethrough(true));
        component.append(Component.translatable("astralenchant.enchantment.disabled").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY).withStrikethrough(false)));
        return component;
    }
}
