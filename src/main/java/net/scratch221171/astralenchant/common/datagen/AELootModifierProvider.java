package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.loot.EBLootModifier;
import net.scratch221171.astralenchant.common.loot.predicates.LootItemConfigCondition;
import net.scratch221171.astralenchant.common.loot.providers.AEEnchantmentLevelProvider;
import net.scratch221171.astralenchant.common.util.ConfigCondition;

import java.util.concurrent.CompletableFuture;

public class AELootModifierProvider extends GlobalLootModifierProvider {
    public AELootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AstralEnchant.MOD_ID);
    }

    @Override
    protected void start() {
        add(
                "mystic_remnants",
                new EBLootModifier(
                        new LootItemCondition[]{
                                new LootItemRandomChanceWithEnchantedBonusCondition(
                                        0f,
                                        LevelBasedValue.perLevel(0.05f, 0.01f),
                                        this.registries.holderOrThrow(AEEnchantments.MYSTIC_REMNANTS)
                                ),
                                new LootItemConfigCondition(ConfigCondition.of(AEEnchantments.MYSTIC_REMNANTS).key())

                        },
                        AEEnchantmentLevelProvider.of(this.registries.holderOrThrow(AEEnchantments.MYSTIC_REMNANTS),
                                        new LevelBasedValue.Linear(4, 4),
                                        EnchantmentTarget.ATTACKER),
                        EnchantmentTags.IN_ENCHANTING_TABLE
                )
        );
    }
}
