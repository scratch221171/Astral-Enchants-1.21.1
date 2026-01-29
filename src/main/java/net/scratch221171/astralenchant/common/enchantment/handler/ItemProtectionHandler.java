package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AstralEnchantUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class ItemProtectionHandler {

    @SubscribeEvent
    public static void onItemEntitySpawn(EntityJoinLevelEvent event) {
        if (!Config.ITEM_PROTECTION.isTrue()) return;
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;
        Holder<Enchantment> enchantment = AstralEnchantUtils.getEnchantmentHolder(AEEnchantments.ITEM_PROTECTION, event.getLevel());
        if (itemEntity.getItem().getEnchantmentLevel(enchantment) > 0) {
            itemEntity.setUnlimitedLifetime();
            itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().multiply(0.1, 0.1, 0.1));
            itemEntity.setNoGravity(true);
            itemEntity.setInvulnerable(true);
        }
    }
}
