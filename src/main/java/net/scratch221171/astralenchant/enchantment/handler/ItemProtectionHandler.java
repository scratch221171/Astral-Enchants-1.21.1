package net.scratch221171.astralenchant.enchantment.handler;

import net.minecraft.core.component.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.registries.ModDataComponents;
import net.scratch221171.astralenchant.util.ItemProtectionUtil;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class ItemProtectionHandler {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        for (EquipmentSlot equipmentslot : EquipmentSlot.values()) {
            ItemStack stack = player.getItemBySlot(equipmentslot);
            if (stack.getOrDefault(ModDataComponents.IS_PROTECTED, false)) {
                if (!ItemProtectionUtil.isContained(stack)) { ItemProtectionUtil.add(stack); }
                int damage = stack.getOrDefault(DataComponents.DAMAGE, -1);
                for (DataComponentType<?> component : stack.getComponents().keySet()) { stack.remove(component); }
                stack.applyComponents(ItemProtectionUtil.get(stack));
                if (damage > 0) stack.set(DataComponents.DAMAGE, damage);
            }
        }
    }
}
