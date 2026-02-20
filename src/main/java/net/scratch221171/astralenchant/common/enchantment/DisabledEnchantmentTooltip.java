package net.scratch221171.astralenchant.common.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;

import java.util.List;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class DisabledEnchantmentTooltip {

    @SubscribeEvent
    private static void onTooltip(ItemTooltipEvent event) {
        List<Component> tooltip = event.getToolTip();

        for (int i = 0; i < tooltip.size(); i++) {
            Component entry = tooltip.get(i);

            if (!(entry.getContents() instanceof TranslatableContents contents)) {
                continue;
            }

            if (!isDisabledEnchantment(contents.getKey())) {
                continue;
            }

            tooltip.set(i, createDisabledComponent(entry));
        }
    }

    private static boolean isDisabledEnchantment(String translationKey) {
        for (ResourceKey<Enchantment> key : AEEnchantments.ENCHANTMENTS) {
            if (Config.TOGGLING_CONFIG_DICT.get(key.location().getPath()).isFalse()
                    && key.location().toLanguageKey("enchantment").equals(translationKey)) {
                return true;
            }
        }
        return false;
    }

    private static MutableComponent createDisabledComponent(Component original) {
        MutableComponent component = original.copy();
        component.setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withStrikethrough(true));
        component.append(
                Component.translatable("astralenchant.enchantment.disabled")
                        .setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY).withStrikethrough(false))
        );
        return component;
    }
}
