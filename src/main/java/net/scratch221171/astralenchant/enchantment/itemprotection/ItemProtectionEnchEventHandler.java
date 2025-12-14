package net.scratch221171.astralenchant.enchantment.itemprotection;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.enchantment.ModEnchantments;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class ItemProtectionEnchEventHandler {

    @SubscribeEvent
    public static void checkItem(ServerTickEvent.Post event) {
        MinecraftServer server = event.getServer();
        Holder<Enchantment> itemProtEnch = server.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.ITEM_PROTECTION);

        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (player.level().isClientSide()) return;
            ItemProtectionRestoreQueue.tickInv(player, itemProtEnch);
        }
    }
}
