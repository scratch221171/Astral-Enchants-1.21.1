package net.scratch221171.astralenchant.enchantment.itemprotection;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.scratch221171.astralenchant.enchantment.ModEnchantments;

public record ItemProtectionEnchEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<ItemProtectionEnchEffect> CODEC = MapCodec.unit(ItemProtectionEnchEffect::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (entity instanceof Player player) {
            Holder<Enchantment> itemProtEnch = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.ITEM_PROTECTION);
            ItemProtectionRestoreQueue.tickInv(player, itemProtEnch);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
