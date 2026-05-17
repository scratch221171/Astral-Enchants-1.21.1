package net.scratch221171.astralenchant.common.condition;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.AEConfig;
import org.jetbrains.annotations.NotNull;

public record ConfigCondition(ModConfigSpec.BooleanValue config) implements ICondition {
    public static final MapCodec<ConfigCondition> CODEC =
            AEConfig.BOOL_CONFIG_CODEC.fieldOf("key").xmap(ConfigCondition::new, ConfigCondition::config);

    @Override
    public boolean test(@NotNull IContext context) {
        try {
            return config.getAsBoolean();
        } catch (Exception e) {
            AstralEnchant.LOGGER.error(e.getLocalizedMessage());
            return true;
        }
    }

    @Override
    public @NotNull MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}
