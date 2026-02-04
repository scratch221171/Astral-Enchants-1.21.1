package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;
import net.scratch221171.astralenchant.common.util.AEUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class AdventurersLoreHandler {
    @SubscribeEvent
    private static void onDrops(BlockDropsEvent event) {
        if (!Config.ADVENTURERS_LORE.isTrue()) return;
        if (!(event.getBreaker() instanceof Player player)) return;

        int level = getLevel(player);
        if (level <= 0) return;
        event.setDroppedExperience(modifyExperience(player, level, event.getDroppedExperience()));
    }

    @SubscribeEvent
    private static void onLoot(LivingExperienceDropEvent event) {
        if (!Config.ADVENTURERS_LORE.isTrue()) return;
        if (!(event.getAttackingPlayer() instanceof Player player)) return;

        int level = getLevel(player);
        if (level <= 0) return;
        event.setDroppedExperience(modifyExperience(player, level, event.getDroppedExperience()));
    }

    private static int modifyExperience(Player player, int level, int original) {
        int count = player.getItemBySlot(EquipmentSlot.FEET).getOrDefault(AEDataComponents.ADVANCEMENTS, 0);
        return (int)Math.floor(original * (1 + 0.1f * count * level));
    }

    private static int getLevel(Player player) {
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.ADVENTURERS_LORE, player.level());
        ItemStack foot = player.getItemBySlot(EquipmentSlot.FEET);
        return foot.getEnchantmentLevel(enchantment);
    }
}
