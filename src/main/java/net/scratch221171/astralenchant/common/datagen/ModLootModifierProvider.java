package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;

import java.util.concurrent.CompletableFuture;

public class ModLootModifierProvider extends GlobalLootModifierProvider {
    public ModLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AstralEnchant.MOD_ID);
    }

    @Override
    protected void start() {
//        add(
//            "feather_touch_loot_modifier_instance",
//            new FeatherTouchLootModifier(new LootItemCondition[] {
//                MatchTool.toolMatches(
//                    ItemPredicate.Builder.item()
//                        .withSubPredicate(
//                            ItemSubPredicates.ENCHANTMENTS,
//                            ItemEnchantmentsPredicate.enchantments(
//                                List.of(new EnchantmentPredicate(this.registries.holderOrThrow(ModEnchantments.FEATHER_TOUCH),
//                                MinMaxBounds.Ints.atLeast(1)))))
//                ).build()
//            })
//        );
    }
}
