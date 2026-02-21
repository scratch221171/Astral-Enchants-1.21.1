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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class DisabledEnchantmentTooltip {

    @SubscribeEvent
    private static void onTooltip(ItemTooltipEvent event) {
        List<Component> tooltip = event.getToolTip();
        Set<String> cachedDisabled = getDisabledEnchantments();

        for (int i = 0; i < tooltip.size(); i++) {
            Component entry = tooltip.get(i);

            if (!(entry.getContents() instanceof TranslatableContents contents)) {
                continue;
            }
            if (!cachedDisabled.contains(contents.getKey())) {
                continue;
            }

            tooltip.set(i, createDisabledComponent(entry));
        }
    }

    private static Set<String> getDisabledEnchantments() {
        Set<String> hashSet = new HashSet<>();
        for (ResourceKey<Enchantment> key : AEEnchantments.ENCHANTMENTS) {
            if (Config.TOGGLING_CONFIG_DICT.get(key.location().getPath()).isFalse()) {
                hashSet.add(key.location().toLanguageKey("enchantment"));
            }
        }
        return hashSet;
    }

    private static MutableComponent createDisabledComponent(Component original) {
        MutableComponent component = original.copy();
        component.setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withStrikethrough(true));
        component.append(Component.translatable("astralenchant.enchantment.disabled").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY).withStrikethrough(false)));
        return component;
    }
}
