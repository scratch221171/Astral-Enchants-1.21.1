package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class CurseOfIgnoranceHandler {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    private static void onItemToolTip(ItemTooltipEvent event) {
        if (Config.CURSE_OF_IGNORANCE.isFalse()) return;

        Entity entity = event.getEntity();
        if (entity == null) return;
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.CURSE_OF_IGNORANCE, entity.level());
        if (event.getItemStack().getEnchantmentLevel(enchantment) > 0) {

            List<Component> tooltip = new ArrayList<>();
            for (Component entry : event.getToolTip()) {
                tooltip.add(entry.copy().setStyle(entry.getStyle().withObfuscated(true)));
            }
            event.getToolTip().clear();
            event.getToolTip().addAll(tooltip);
        }
    }
}
