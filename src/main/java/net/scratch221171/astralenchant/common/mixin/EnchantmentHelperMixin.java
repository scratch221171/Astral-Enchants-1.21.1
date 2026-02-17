package net.scratch221171.astralenchant.common.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    /***
     * {@link AEEnchantments#OVERLOAD} が付いている場合にパッシブエフェクト（ダメージ増加など）のエンチャントのレベルを変更する、
     */
    @Inject(method = "runIterationOnItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/enchantment/EnchantmentHelper$EnchantmentVisitor;)V", at = @At("HEAD"), cancellable = true)
    private static void astralEnchant$modifyLevel(ItemStack stack, EnchantmentHelper.EnchantmentVisitor visitor, CallbackInfo ci) {
        if (Config.OVERLOAD.isFalse()) return;
        ItemEnchantments itemenchantments = stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);

        var lookup = net.neoforged.neoforge.common.CommonHooks.resolveLookup(net.minecraft.core.registries.Registries.ENCHANTMENT);
        int level = stack.getOrDefault(AEDataComponents.OVERLOAD, 0);
        if (lookup != null) {
            itemenchantments = stack.getAllEnchantments(lookup);
        }

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : itemenchantments.entrySet()) {
            visitor.accept(entry.getKey(), entry.getIntValue() + level);
        }
        ci.cancel();
    }

    /***
     * {@link AEEnchantments#OVERLOAD} が付いている場合にパッシブエフェクト（ダメージ増加など）のエンチャントのレベルを変更する、
     */
    @Inject(method = "runIterationOnItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/enchantment/EnchantmentHelper$EnchantmentInSlotVisitor;)V", at = @At("HEAD"), cancellable = true)
    private static void astralEnchant$modifyLevel(ItemStack stack, EquipmentSlot slot, LivingEntity entity, EnchantmentHelper.EnchantmentInSlotVisitor visitor, CallbackInfo ci) {
        if (Config.OVERLOAD.isFalse()) return;
        if (!stack.isEmpty()) {
            ItemEnchantments itemenchantments = stack.getAllEnchantments(entity.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT));

            if (!itemenchantments.isEmpty()) {
                EnchantedItemInUse enchantediteminuse = new EnchantedItemInUse(stack, slot, entity);
                int level = stack.getOrDefault(AEDataComponents.OVERLOAD, 0);

                for (Object2IntMap.Entry<Holder<Enchantment>> entry : itemenchantments.entrySet()) {
                    Holder<Enchantment> holder = entry.getKey();
                    if (holder.value().matchingSlot(slot)) {
                        visitor.accept(holder, entry.getIntValue() + level, enchantediteminuse);
                    }
                }
            }
        }
        ci.cancel();
    }
}
