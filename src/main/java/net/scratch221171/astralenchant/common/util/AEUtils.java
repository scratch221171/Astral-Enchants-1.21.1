package net.scratch221171.astralenchant.common.util;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;

public class AEUtils {

    public static Holder<Enchantment> getEnchantmentHolder(ResourceKey<Enchantment> enchantment, Level level) {
        return level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(enchantment);
    }

    public static int getEnchantmentLevelFromNBT(ItemStack stack, ResourceKey<Enchantment> enchantment) {
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : stack.getTagEnchantments().entrySet()) {
            if (entry.getKey().is(enchantment)) {
                // 専用メソッドを尊重
                return stack.getEnchantmentLevel(entry.getKey());
            }
        }
        return 0;
    }

    public static int getEnchantmentLevelFromNBT(ItemEnchantments enchantments, ResourceKey<Enchantment> enchantment) {
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
            if (entry.getKey().is(enchantment)) {
                return entry.getIntValue();
            }
        }
        return 0;
    }

    public static ItemEnchantments mergeItemEnchants(ItemEnchantments a, ItemEnchantments b) {
        ItemEnchantments.Mutable result = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);

        a.entrySet().forEach(entry -> result.set(entry.getKey(), entry.getIntValue()));

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : b.entrySet()) {
            Holder<Enchantment> enchant = entry.getKey();
            int levelB = entry.getIntValue();

            if (!result.keySet().contains(enchant)) {
                result.set(enchant, levelB);
                continue;
            }

            int levelA = result.getLevel(enchant);
            if (levelB > levelA) {
                result.set(enchant, levelB);
            } else if (levelA == levelB) {
                if (levelA < enchant.value().getMaxLevel()) {
                    result.set(enchant, levelA + 1);
                }
            }
        }

        return result.toImmutable();
    }
}
