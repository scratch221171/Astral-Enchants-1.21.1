package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.loot.MysticRemnantsLootModifier;

import java.util.concurrent.CompletableFuture;

public class AELootModifierProvider extends GlobalLootModifierProvider {
    public AELootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AstralEnchant.MOD_ID);
    }

    @Override
    protected void start() {
        add("mystic_remnants", new MysticRemnantsLootModifier(new LootItemCondition[]{}));
    }
}
