package net.scratch221171.astralenchant.enchantment.enchantmenteffect;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.scratch221171.astralenchant.registries.ModDataComponents;
import org.jetbrains.annotations.NotNull;

public record ItemProtectionEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<ItemProtectionEffect> CODEC = MapCodec.unit(ItemProtectionEffect::new);

    @Override
    public void apply(@NotNull ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, @NotNull Entity entity, @NotNull Vec3 origin) {
        item.itemStack().set(ModDataComponents.IS_PROTECTED,  true);
    }

    @Override
    public @NotNull MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
