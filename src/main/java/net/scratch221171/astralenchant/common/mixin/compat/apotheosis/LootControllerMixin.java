package net.scratch221171.astralenchant.common.mixin.compat.apotheosis;

import dev.shadowsoffire.apotheosis.affix.ItemAffixes;
import dev.shadowsoffire.apotheosis.loot.LootCategory;
import dev.shadowsoffire.apotheosis.loot.LootController;
import dev.shadowsoffire.apotheosis.loot.LootRarity;
import dev.shadowsoffire.apotheosis.tiers.GenContext;
import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.common.AstralEnchant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LootController.class)
public class LootControllerMixin {
    @ModifyVariable(
            method = "createLootItem(Lnet/minecraft/world/item/ItemStack;Ldev/shadowsoffire/apotheosis/loot/LootCategory;Ldev/shadowsoffire/apotheosis/loot/LootRarity;Ldev/shadowsoffire/apotheosis/tiers/GenContext;)Lnet/minecraft/world/item/ItemStack;",
            at = @At(value = "STORE", ordinal = 0),
            name = "loaded")
    private static ItemAffixes modifyLoaded(ItemAffixes loaded, ItemStack stack, LootCategory cat, LootRarity rarity, GenContext ctx) {
//        if (loaded.isEmpty()) return loaded;
//        int level = AEUtils.getEnchantmentLevelFromNBT(stack, AEEnchantments.MYSTIC_REMNANTS);
//        if (level <= 0) return loaded;
//        ItemAffixes.Builder modified = loaded.toBuilder();
//
//        AstralEnchant.LOGGER.info("loaded: {}", loaded);
//
//        AffixType[] values = AffixType.values();
//        for (int i = 0; i <= level; i++) {
//            new LootRule.AffixLootRule(values[ThreadLocalRandom.current().nextInt(values.length)]).execute(stack, rarity, ctx);
//        }
//        new LootRule.SocketLootRule(1,level).execute(stack, rarity, ctx);
//
//        AstralEnchant.LOGGER.info("modified: {}", modified.build());
//        return modified.build();
        AstralEnchant.LOGGER.info("loaded: {}\nstack: {}\ncat: {}\nrarity: {}\nctx: {}", loaded,  stack, cat, rarity, ctx);
        return loaded;
    }
}
