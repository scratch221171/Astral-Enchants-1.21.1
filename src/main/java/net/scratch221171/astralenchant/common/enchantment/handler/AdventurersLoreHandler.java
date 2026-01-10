package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.ModEnchantments;
import net.scratch221171.astralenchant.common.registries.ModDataComponents;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class AdventurersLoreHandler {
    @SubscribeEvent
    public static void onDrops(BlockDropsEvent event) {
        if (!Config.ADVENTURERS_LORE.isTrue()) return;
        if (!(event.getBreaker() instanceof Player player)) return;

        Holder<Enchantment> adventurePreparation = player.level().registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.ADVENTURERS_LORE);
        ItemStack foot = player.getItemBySlot(EquipmentSlot.FEET);
        int level = foot.getEnchantmentLevel(adventurePreparation);
        if (level <= 0) return;

        int count = player.getItemBySlot(EquipmentSlot.FEET).getOrDefault(ModDataComponents.ADVANCEMENTS, 0);
        int exp = event.getDroppedExperience();
        int newExp = (int)Math.floor(exp * (1 + 0.1f * count * level));
        event.setDroppedExperience(newExp);
    }

}
