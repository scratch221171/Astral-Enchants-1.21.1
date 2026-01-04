package net.scratch221171.astralenchant.enchantment.compatible;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.scratch221171.astralenchant.registries.ModDataComponents;
import org.jetbrains.annotations.NotNull;

public record CompatibleEnchEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<CompatibleEnchEffect> CODEC = MapCodec.unit(CompatibleEnchEffect::new);

    @Override
    public void apply(@NotNull ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, @NotNull Entity entity, @NotNull Vec3 origin) {}

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
