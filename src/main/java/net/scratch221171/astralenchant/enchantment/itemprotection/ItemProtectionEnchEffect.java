package net.scratch221171.astralenchant.enchantment.itemprotection;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.scratch221171.astralenchant.registries.ModDataComponents;
import net.scratch221171.astralenchant.util.ProtectedItemState;
import net.scratch221171.astralenchant.util.ProtectedItemUtil;
import net.scratch221171.astralenchant.util.ServerPlayerMixinAccess;

import java.util.UUID;

public record ItemProtectionEnchEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<ItemProtectionEnchEffect> CODEC = MapCodec.unit(ItemProtectionEnchEffect::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
//        if (entity instanceof Player player) {
//            Holder<Enchantment> itemProtEnch = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.ITEM_PROTECTION);
//            ItemProtectionRestoreQueue.tickInv(player, itemProtEnch);
//        }
        if (!(entity instanceof ServerPlayer player)) return;
        if (item.itemStack().get(ModDataComponents.UUID) == null) {
            ProtectedItemState state = new ProtectedItemState(UUID.randomUUID(), item.itemStack().getItem(), player.getInventory().findSlotMatchingItem(item.itemStack()) , item.itemStack().getComponents());
            ((ServerPlayerMixinAccess)player).astralenchant$addProtectedItem(state);
        }
        ProtectedItemUtil.restoreAll(player, player.getInventory());
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
