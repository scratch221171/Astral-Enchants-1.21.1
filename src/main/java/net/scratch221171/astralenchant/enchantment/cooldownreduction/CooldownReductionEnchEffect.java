package net.scratch221171.astralenchant.enchantment.cooldownreduction;

import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.mixin.CooldownInstanceMixin;
import net.scratch221171.astralenchant.mixin.ItemCooldownsMixin;

import java.util.Map;

public record CooldownReductionEnchEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<CooldownReductionEnchEffect> CODEC = MapCodec.unit(CooldownReductionEnchEffect::new);
//    private static Map<Item, ?> queue = Maps.newHashMap();
    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (level.isClientSide) return;
        if (entity instanceof Player player) {
            ItemCooldownsMixin cooldownsMixin = (ItemCooldownsMixin) player.getCooldowns();
            Map<Item, ?> cooldowns = cooldownsMixin.getCooldowns();

            for (Map.Entry<Item, ?> entry : cooldowns.entrySet()) {
                CooldownInstanceMixin cooldownInstMixin = (CooldownInstanceMixin) entry.getValue();
                if (cooldownInstMixin.getStartTime() + 1 >= cooldownsMixin.getTickCount()) {
                    if (enchantmentLevel < 10) {
                        float ticks = (float) (cooldownInstMixin.getEndTime() - cooldownInstMixin.getStartTime());
                        int newTicks = (int) Math.ceil(ticks * (1 - enchantmentLevel * 0.1f));

                        player.getCooldowns().addCooldown(entry.getKey(), newTicks);
                        player.getCooldowns().tick();
                        player.getCooldowns().tick();
                    } else {
                        player.getCooldowns().removeCooldown(entry.getKey());
                    }
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
