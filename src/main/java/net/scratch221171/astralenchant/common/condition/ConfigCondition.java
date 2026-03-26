package net.scratch221171.astralenchant.common.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;
import org.jetbrains.annotations.NotNull;

public record ConfigCondition(String key) implements ICondition {
    public static final MapCodec<ConfigCondition> CODEC =
            RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Codec.STRING.fieldOf("key").forGetter(ConfigCondition::key)
            ).apply(inst, ConfigCondition::new));

    public static ConfigCondition of(ResourceKey<Enchantment> enchantment) {
        return of(enchantment.location().getPath());
    }

    public static ConfigCondition of(String key) {
        return new ConfigCondition(key);
    }

    @Override
    public boolean test(@NotNull IContext context) {
        return test();
    }

    public boolean test() {
        return Boolean.TRUE.equals(RuntimeConfigState.get(key));
    }

    @Override
    public @NotNull MapCodec<? extends ICondition> codec() {
        return CODEC;
    }

}

