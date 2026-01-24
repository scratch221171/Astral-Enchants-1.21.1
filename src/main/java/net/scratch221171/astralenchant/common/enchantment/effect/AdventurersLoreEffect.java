package net.scratch221171.astralenchant.common.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.registries.ModDataComponents;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record AdventurersLoreEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<AdventurersLoreEffect> CODEC = MapCodec.unit(AdventurersLoreEffect::new);

    @Override
    public void apply(@NotNull ServerLevel level, int enchantmentLevel, @NotNull EnchantedItemInUse item, @NotNull Entity entity, @NotNull Vec3 origin) {
        if (!Config.ADVENTURERS_LORE.isTrue()) return;
        if (!(entity instanceof ServerPlayer player)) return;
        ServerAdvancementManager advancements = level.getServer().getAdvancements();

        int count = 0;
        for (AdvancementHolder holder : advancements.getAllAdvancements()) {
            AdvancementProgress progress = player.getAdvancements().getOrStartProgress(holder);
            if (holder.value().display().isPresent() && progress.isDone()) {
                count++;
            }
        }
        item.itemStack().set(ModDataComponents.ADVANCEMENTS, count);

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "ap_bonus");
        AttributeModifier modifier = new AttributeModifier(id, 0.1 * count * enchantmentLevel, AttributeModifier.Operation.ADD_VALUE);
        Objects.requireNonNull(player.getAttributes().getInstance(Attributes.LUCK)).addOrReplacePermanentModifier(modifier);
    }

    @Override
    public @NotNull MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
