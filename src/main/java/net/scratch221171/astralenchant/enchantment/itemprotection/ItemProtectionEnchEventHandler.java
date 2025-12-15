package net.scratch221171.astralenchant.enchantment.itemprotection;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.enchantment.ModEnchantments;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class ItemProtectionEnchEventHandler {

    @SubscribeEvent
    public static void checkItem(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;

        MinecraftServer server = player.getServer();
        if (server == null) return;

        Holder<Enchantment> itemProtEnch = server.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.ITEM_PROTECTION);
        ItemProtectionRestoreQueue.tickInv(player, itemProtEnch);
    }
}
