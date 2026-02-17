package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class AdventurersLoreHandler {

    @SubscribeEvent
    private static void onTick(PlayerTickEvent.Pre event) {
        if (Config.ADVENTURERS_LORE.isFalse()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        int level = getLevel(player);
        if (level <= 0) return;
        int count = getAdvancementNumber(player, serverLevel);
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "al_bonus");
        AttributeModifier modifier = new AttributeModifier(id, 0.1 * count * level, AttributeModifier.Operation.ADD_VALUE);
        AttributeInstance instance = player.getAttributes().getInstance(Attributes.LUCK);
        if (instance != null) instance.addOrReplacePermanentModifier(modifier);
    }

    @SubscribeEvent
    private static void onDrops(BlockDropsEvent event) {
        if (Config.ADVENTURERS_LORE.isFalse()) return;
        if (!(event.getBreaker() instanceof ServerPlayer player)) return;
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        int level = getLevel(player);
        if (level <= 0) return;
        event.setDroppedExperience(modifyExperience(player, serverLevel, level, event.getDroppedExperience()));
    }

    @SubscribeEvent
    private static void onLoot(LivingExperienceDropEvent event) {
        if (Config.ADVENTURERS_LORE.isFalse()) return;
        if (!(event.getAttackingPlayer() instanceof ServerPlayer player)) return;
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        int level = getLevel(player);
        if (level <= 0) return;
        event.setDroppedExperience(modifyExperience(player, serverLevel, level, event.getDroppedExperience()));
    }

    private static int modifyExperience(ServerPlayer player, ServerLevel serverLevel, int level, int original) {
        int count = getAdvancementNumber(player, serverLevel);
        return (int)Math.floor(original * (1 + 0.1f * count * level));
    }

    private static int getAdvancementNumber(ServerPlayer player, ServerLevel serverLevel) {
        ServerAdvancementManager advancements = serverLevel.getServer().getAdvancements();

        int count = 0;
        for (AdvancementHolder holder : advancements.getAllAdvancements()) {
            AdvancementProgress progress = player.getAdvancements().getOrStartProgress(holder);
            if (holder.value().display().isPresent() && progress.isDone()) {
                count++;
            }
        }

        return  count;
    }

    private static int getLevel(Player player) {
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.ADVENTURERS_LORE, player.level());
        return EnchantmentHelper.getEnchantmentLevel(enchantment, player);
    }
}
