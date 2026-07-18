package net.scratch221171.astralenchant.common.enchantment.handler;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Unbreakable;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;
import net.scratch221171.astralenchant.common.util.AEUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class OverMendingHandler {

    @SubscribeEvent
    private static void struckByLightning(EntityStruckByLightningEvent event) {
        if (!(AEConfig.isEnabled(AEEnchantments.OVER_MENDING))) return;
        if (!(event.getEntity() instanceof Player player)) return;
        ServerLevel level = (ServerLevel) player.level();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = player.getItemBySlot(slot);
            if (stack.getOrDefault(AEDataComponents.OVER_MENDING, 0) >= 100) {
                level.playSound(null, player.getOnPos(), SoundEvents.TRIDENT_THUNDER.value(), SoundSource.PLAYERS);
                double multiplier = AEConfig.OVER_MENDING_LIGHTNING_DAMAGE_MULTIPLIER.getAsDouble();
                event.getLightning().setDamage((float) (event.getLightning().getDamage() * multiplier));
                //                OverMendingCache.CACHE.put(player, slot);
                stack.set(DataComponents.UNBREAKABLE, new Unbreakable(true));
                stack.set(
                        DataComponents.ENCHANTMENTS,
                        AEUtils.removeEnchantment(stack.get(DataComponents.ENCHANTMENTS), AEEnchantments.OVER_MENDING));
                stack.remove(AEDataComponents.OVER_MENDING);
                level.sendParticles(
                        ParticleTypes.END_ROD, player.getX(), player.getY() + 1, player.getZ(), 1000, 0f, 0f, 0f, 0.5f);
                return;
            }
        }
    }

    @SubscribeEvent
    private static void tooltip(ItemTooltipEvent event) {
        if (!(AEConfig.isEnabled(AEEnchantments.OVER_MENDING))) return;
        ItemStack stack = event.getItemStack();

        HolderLookup.Provider provider = event.getContext().registries();
        int overMendingLevel;
        if (provider != null) {
            overMendingLevel = AEUtils.getEnchantmentLevel(stack, provider, AEEnchantments.OVER_MENDING);
        } else {
            overMendingLevel = AEUtils.getEnchantmentLevel(stack, AEEnchantments.OVER_MENDING);
        }
        if (overMendingLevel > 0) {
            int progress = stack.getOrDefault(AEDataComponents.OVER_MENDING, 0);
            List<Component> tooltip = event.getToolTip();

            for (int i = 0; i < tooltip.size(); i++) {
                Component entry = tooltip.get(i);

                if (!(entry.getContents() instanceof TranslatableContents contents)) continue;
                String key = contents.getKey();

                if (key.equals("enchantment.astralenchant.over_mending")) {
                    {
                        tooltip.add(
                                i + 1,
                                (progress < 100
                                                ? Component.translatable(
                                                        "enchantment.astralenchant.over_mending.tooltip.text", progress)
                                                : Component.translatable(
                                                        "enchantment.astralenchant.over_mending.tooltip.hint"))
                                        .setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_AQUA)));
                    }
                    return;
                }
            }
        }
    }

    //    @SubscribeEvent
    //    private static void survivedLightning(LivingDamageEvent.Post event) {
    //        if (!(event.getEntity() instanceof Player player)
    //            || !player.isAlive()
    //            || !(event.getSource() == player.damageSources().lightningBolt())) return;
    //        if (OverMendingCache.CACHE.containsKey(player)) {
    //            ServerLevel level = (ServerLevel) player.level();
    //            ItemStack stack = player.getItemBySlot(OverMendingCache.CACHE.get(player));
    //            stack.set(DataComponents.UNBREAKABLE, new Unbreakable(true));
    //            stack.set(DataComponents.ENCHANTMENTS,
    // AEUtils.removeEnchantment(stack.get(DataComponents.ENCHANTMENTS),
    // AEUtils.getEnchantmentHolder(AEEnchantments.OVER_MENDING, level)));
    //            stack.remove(AEDataComponents.OVER_MENDING);
    //            level.sendParticles(ParticleTypes.END_ROD, player.getX(), player.getY() + 1, player.getZ(), 1000, 0f,
    // 0f, 0f, 0.5f);
    //            OverMendingCache.CACHE.remove(player);}
    //    }

    //    @SubscribeEvent
    //    private static void onTick(ServerTickEvent.Post event) {
    //        OverMendingCache.CACHE.clear();
    //    }
    //
    //    static class OverMendingCache {
    //        public static final Map<Player, EquipmentSlot> CACHE = new HashMap<>();
    //    }
}
