package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;
import net.scratch221171.astralenchant.common.registries.AEAttributes;
import net.scratch221171.astralenchant.common.util.IItemCooldownsExtension;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class CooldownDurationAttributeHandler {

    // attribute関連、エンチャントではないのでConfigで無効化されない
    @SubscribeEvent
    private static void modifyDefaultAttributes(EntityAttributeModificationEvent event) {
        if (!event.has(EntityType.PLAYER, AEAttributes.COOLDOWN_DURATION)) {
            event.add(EntityType.PLAYER, AEAttributes.COOLDOWN_DURATION, 1.0);
        }
    }

    // ここで一旦チェック
    @SubscribeEvent
    private static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!RuntimeConfigState.get(AEConfig.COOLDOWN_REDUCTION)) return;
        if (event.getEntity().level().isClientSide) return;
        Player player = event.getEntity();
        float value = (float) player.getAttributeValue(AEAttributes.COOLDOWN_DURATION);
        ((IItemCooldownsExtension) player.getCooldowns()).astralenchant$setCooldownDurationMultiplier(value);
    }
}
