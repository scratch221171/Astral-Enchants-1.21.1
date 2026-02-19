package net.scratch221171.astralenchant.common.registries;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.loot.predicates.LootItemConfigCondition;

public class AELootItemConditions {
    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPE =
            DeferredRegister.create(BuiltInRegistries.LOOT_CONDITION_TYPE, AstralEnchant.MOD_ID);

    public static final Holder<LootItemConditionType> CONFIG_CONDITION =
            LOOT_CONDITION_TYPE.register("config_condition", () -> new LootItemConditionType(LootItemConfigCondition.CODEC));

    public static void register(IEventBus eventBus) {
        LOOT_CONDITION_TYPE.register(eventBus);
    }
}
