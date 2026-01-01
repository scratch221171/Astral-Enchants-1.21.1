package net.scratch221171.astralenchant.datagen;

import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.predicates.EnchantmentActiveCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.predicates.WeatherCheck;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.enchantment.ModEnchantments;
import net.scratch221171.astralenchant.enchantment.feathertouch.FeatherTouchLootModifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static net.minecraft.advancements.critereon.ItemEnchantmentsPredicate.enchantments;
import static net.minecraft.world.item.enchantment.Enchantment.enchantment;

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
