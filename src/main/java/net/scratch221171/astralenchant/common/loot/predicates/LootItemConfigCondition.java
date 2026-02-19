package net.scratch221171.astralenchant.common.loot.predicates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.scratch221171.astralenchant.common.registries.AELootItemConditions;
import net.scratch221171.astralenchant.common.util.ConfigCondition;
import org.jetbrains.annotations.NotNull;

public record LootItemConfigCondition(String config) implements LootItemCondition {
    public static final MapCodec<LootItemConfigCondition> CODEC = RecordCodecBuilder.mapCodec(
            inst -> inst.group(
                            Codec.STRING.fieldOf("config").forGetter(LootItemConfigCondition::config)
                    )
                    .apply(inst, LootItemConfigCondition::new)
    );

    @Override
    public @NotNull LootItemConditionType getType() {
        return AELootItemConditions.CONFIG_CONDITION.value();
    }

    @Override
    public boolean test(LootContext lootContext) {
        return ConfigCondition.of(config).test();
    }
}
