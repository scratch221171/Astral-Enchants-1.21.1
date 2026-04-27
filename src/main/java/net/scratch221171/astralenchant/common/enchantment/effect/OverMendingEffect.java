package net.scratch221171.astralenchant.common.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.jetbrains.annotations.NotNull;

public record OverMendingEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<OverMendingEffect> CODEC = MapCodec.unit(OverMendingEffect::new);

    @Override
    public void apply(@NotNull ServerLevel level, int enchantmentLevel, @NotNull EnchantedItemInUse item, @NotNull Entity entity, @NotNull Vec3 origin) {
        if (!(RuntimeConfigState.get(AEConfig.OVER_MENDING))) return;
        if (!(entity instanceof Player player)) return;

        ItemStack stack = item.itemStack();

        // 100の倍数じゃない値に設定する天邪鬼に備えてround
        int XPPerTick = Math.round(RuntimeConfigState.get(AEConfig.OVER_MENDING_TOTAL_EXPERIENCE_REQUIRED) / 100f) ;

        if (stack.getOrDefault(AEDataComponents.OVER_MENDING, 0) >= 100) return;
        if (!AEUtils.hasEnoughXPPoint(player.experienceProgress, player.experienceLevel, XPPerTick)) return;
        player.giveExperiencePoints(-XPPerTick);

        stack.set(AEDataComponents.OVER_MENDING, stack.getOrDefault(AEDataComponents.OVER_MENDING, 0) + 1);
    }

    @Override
    public @NotNull MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
