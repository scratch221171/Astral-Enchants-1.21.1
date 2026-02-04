package net.scratch221171.astralenchant.common.util;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

public class AEUtils {
    public static Holder<Enchantment> getEnchantmentHolder(ResourceKey<Enchantment> enchantment, Level level) {
        return level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(enchantment);
    }

    public static Holder<Enchantment> getEnchantmentHolderFromServer(ResourceKey<Enchantment> enchantment, MinecraftServer server) {
        return server.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(enchantment);
    }
}
