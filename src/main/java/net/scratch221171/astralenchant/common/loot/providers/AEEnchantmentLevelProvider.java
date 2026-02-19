package net.scratch221171.astralenchant.common.loot.providers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.providers.number.LootNumberProviderType;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.scratch221171.astralenchant.common.registries.AENumberProviders;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public record AEEnchantmentLevelProvider(Holder<Enchantment> enchantment, LevelBasedValue amount, EnchantmentTarget target) implements NumberProvider {
    public static final MapCodec<AEEnchantmentLevelProvider> CODEC = RecordCodecBuilder.mapCodec(
            p_345879_ -> p_345879_.group(
                    Enchantment.CODEC.fieldOf("enchantment").forGetter(AEEnchantmentLevelProvider::enchantment),
                    LevelBasedValue.CODEC.fieldOf("amount").forGetter(AEEnchantmentLevelProvider::amount),
                    EnchantmentTarget.CODEC.fieldOf("target").forGetter(AEEnchantmentLevelProvider::target)
                    ).apply(p_345879_, AEEnchantmentLevelProvider::new)
    );

    @Override
    public float getFloat(@NotNull LootContext lootContext) {
        int i = resolveEntity(lootContext, target) instanceof LivingEntity le ?
                EnchantmentHelper.getEnchantmentLevel(enchantment, le) : 0;
        return this.amount.calculate(i);
    }

    @Override
    public @NotNull LootNumberProviderType getType() {
        return AENumberProviders.AE_ENCHANTMENT_LEVEL.value();
    }

    public static AEEnchantmentLevelProvider of(Holder<Enchantment> enchantment, LevelBasedValue amount, EnchantmentTarget target) {
        return new AEEnchantmentLevelProvider(enchantment, amount, target);
    }

    private static @Nullable Entity resolveEntity(
            LootContext ctx,
            EnchantmentTarget target
    ) {
        return switch (target) {
            case ATTACKER -> ctx.getParamOrNull(LootContextParams.ATTACKING_ENTITY);
            case DAMAGING_ENTITY -> ctx.getParamOrNull(LootContextParams.DIRECT_ATTACKING_ENTITY);
            case VICTIM -> ctx.getParamOrNull(LootContextParams.THIS_ENTITY);
        };

    }
}
