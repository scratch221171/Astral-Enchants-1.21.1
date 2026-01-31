package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.registries.AEAttributes;
import net.scratch221171.astralenchant.common.util.IItemCooldownsExtention;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class CooldownAttributeHandler {

    @SubscribeEvent
    public static void modifyDefaultAttributes(EntityAttributeModificationEvent event) {
        if (!event.has(EntityType.PLAYER, AEAttributes.COOLDOWN_REDUCTION)) {
            event.add(EntityType.PLAYER, AEAttributes.COOLDOWN_REDUCTION, 0.0);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide) return;
        Player player = event.getEntity();
        float value = 1.0F - (float) player.getAttributeValue(AEAttributes.COOLDOWN_REDUCTION);
        ((IItemCooldownsExtention) player.getCooldowns()).astralEnchant$setCooldownReductionMultiplier(value);
    }
}
