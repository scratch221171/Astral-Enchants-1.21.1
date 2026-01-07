package net.scratch221171.astralenchant.enchantment.handler;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.registries.ModDataComponents;
import net.scratch221171.astralenchant.util.ItemProtectionUtil;

import java.util.UUID;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class ItemProtectionHandler {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        // クリエイティブタブ下部のホットバーでなぜか問題が起きる
        if (player.level().isClientSide && player.isCreative()) return;
        for (EquipmentSlot equipmentslot : EquipmentSlot.values()) {
            ItemStack stack = player.getItemBySlot(equipmentslot);
            UUID uuid = stack.get(ModDataComponents.UUID);
            if (uuid != null) {
                if (!ItemProtectionUtil.isContained(uuid)) {
                    ItemProtectionUtil.add(uuid, stack.copy());
                }
                int damage = stack.getOrDefault(DataComponents.DAMAGE, -1);
                for (DataComponentType<?> component : stack.getComponents().keySet()) { stack.remove(component); }
                stack.applyComponents(ItemProtectionUtil.get(uuid).getComponents());
                if (damage > 0) stack.set(DataComponents.DAMAGE, damage);
            }
        }
    }

    @SubscribeEvent
    public static void onItemEntitySpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof ItemEntity itemEntity) || itemEntity.getItem().get(ModDataComponents.UUID) == null) return;
        itemEntity.setUnlimitedLifetime();
        itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().multiply(0.1, 0.1, 0.1));
        itemEntity.setNoGravity(true);
        itemEntity.setInvulnerable(true);
    }
}
