package net.scratch221171.astralenchant.enchantment.adventurepreparation;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.datagen.ModEnchantments;
import net.scratch221171.astralenchant.registries.ModDataComponents;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class AdventurersLoreEnchEventHandler {
    @SubscribeEvent
    public static void onDrops(BlockDropsEvent event) {
        if (!(event.getBreaker() instanceof Player player)) return;

        Holder<Enchantment> adventurePreparation = player.level().registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.ADVENTURERS_LORE);
        ItemStack foot = player.getItemBySlot(EquipmentSlot.FEET);
        int level = foot.getEnchantmentLevel(adventurePreparation);
        if (level <= 0) return;

        int count = player.getItemBySlot(EquipmentSlot.FEET).getOrDefault(ModDataComponents.ADVANCEMENTS, 0);
        int exp = event.getDroppedExperience();
        int newExp = (int)Math.floor(exp * (1 + 0.01f * count * level));
        event.setDroppedExperience(newExp);
    }

}
