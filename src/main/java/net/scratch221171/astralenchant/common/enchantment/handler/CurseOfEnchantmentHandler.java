package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.event.ItemEnchantmentSetEvent;
import net.scratch221171.astralenchant.common.util.AEUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class CurseOfEnchantmentHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    private static void cancelModification(ItemEnchantmentSetEvent event) {
        if (!AEConfig.isEnabled(AEEnchantments.CURSE_OF_ENCHANTMENT)) return;

        ItemStack stack = event.getStack();
        if (AEUtils.getEnchantmentLevel(stack, AEEnchantments.CURSE_OF_ENCHANTMENT) <= 0) return;

        event.setCanceled(true);
    }

    @SubscribeEvent
    private static void onItemEntitySpawn(EntityJoinLevelEvent event) {
        if (!AEConfig.isEnabled(AEEnchantments.CURSE_OF_ENCHANTMENT)) return;
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;
        AEUtils.getEnchantmentHolder(AEEnchantments.CURSE_OF_ENCHANTMENT, event.getLevel())
                .ifPresent(holder -> {
                    if (itemEntity.getItem().getEnchantmentLevel(holder) > 0) {
                        itemEntity.setUnlimitedLifetime();
                        itemEntity.setDeltaMovement(
                                itemEntity.getDeltaMovement().multiply(0.1, 0.1, 0.1));
                        itemEntity.setNoGravity(true);
                        itemEntity.setInvulnerable(true);
                    }
                });
    }
}
