package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.loot.EBLootModifier;

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
                                        LevelBasedValue.perLevel(0.05f),
                                        this.registries.holderOrThrow(AEEnchantments.MYSTIC_REMNANTS)
                                )
                        },
                        LevelBasedValue.perLevel(5),
                        this.registries.holderOrThrow(AEEnchantments.MYSTIC_REMNANTS),
                        EnchantmentTags.IN_ENCHANTING_TABLE
                )
        );
    }
}
