package net.scratch221171.astralenchant.common.enchantment.handler;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class CurseOfIgnoranceHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    private static void onItemToolTip(ItemTooltipEvent event) {
        if (!AEConfig.isEnabled(AEEnchantments.CURSE_OF_IGNORANCE)) return;

        Entity entity = event.getEntity();
        if (entity == null) return;
        AEUtils.getEnchantmentHolder(AEEnchantments.CURSE_OF_IGNORANCE, entity).ifPresent(holder -> {
            if (event.getItemStack().getEnchantmentLevel(holder) > 0) {

                List<Component> tooltip = new ArrayList<>();
                for (Component entry : event.getToolTip()) {
                    tooltip.add(
                            entry.getContents() instanceof TranslatableContents contents
                                            && (contents.getKey()
                                                            .equals(AEEnchantments.CURSE_OF_IGNORANCE
                                                                    .location()
                                                                    .toLanguageKey("enchantment"))
                                                    || contents.getKey()
                                                            .equals(AEEnchantments.CURSE_OF_IGNORANCE
                                                                    .location()
                                                                    .toLanguageKey("enchantment", "desc")))
                                    ? entry.copy()
                                    : entry.copy().setStyle(entry.getStyle().withObfuscated(true)));
                }
                event.getToolTip().clear();
                event.getToolTip().addAll(tooltip);
            }
        });
    }
}
