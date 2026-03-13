package net.scratch221171.astralenchant.common.mixin.minecraft;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "set", at = @At("HEAD"), cancellable = true)
    private <T> void astralenchant$onEnchanted(
            DataComponentType<? super T> component,
            T value,
            CallbackInfoReturnable<T> cir
    ) {
        if (!astralenchant$isEnchantmentComponent(component, value)) {
            return;
        }

        ItemStack stack = (ItemStack) (Object) this;

        ItemEnchantments enchantments = (ItemEnchantments) value;

        if (astralenchant$tryHandleBundle(stack, enchantments, cir)) return;
        if (astralenchant$tryHandleOverload(stack, enchantments, cir)) return;
        if (astralenchant$tryHandleItemProtection(stack, cir)) return;
    }

    @Unique
    private static boolean astralenchant$isEnchantmentComponent(
            DataComponentType<?> component,
            Object value
    ) {
        return component == DataComponents.ENCHANTMENTS
                && value instanceof ItemEnchantments;
    }

    @Unique
    private static boolean astralenchant$tryHandleBundle(
            ItemStack stack,
            ItemEnchantments enchantments,
            CallbackInfoReturnable<?> cir
    ) {
        AstralEnchant.LOGGER.info("1");
        if (!RuntimeConfigState.get(AEConfig.COMPATIBILITY) || !stack.is(Items.BUNDLE)) return false;
        if (AEUtils.getEnchantmentLevelFromNBT(stack, AEEnchantments.COMPATIBILITY) <= 0) return false;
        AstralEnchant.LOGGER.info("compatibility");
        AstralEnchant.LOGGER.info("stack: {}\nenchantments: {}", stack, enchantments);

        BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        if (contents.isEmpty()) return false;

        AstralEnchant.LOGGER.info("not empty");

        ItemEnchantments.Mutable added = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        enchantments.entrySet().stream()
                .filter(e ->
                        !e.getKey().is(AEEnchantments.COMPATIBILITY)
                                && stack.getEnchantmentLevel(e.getKey()) <= 0
                )
                .forEach(e -> added.set(e.getKey(), e.getIntValue()));

        AstralEnchant.LOGGER.info("added enchantments: {}", added);

        List<ItemStack> newItems = new ArrayList<>();
        for (ItemStack item : contents.items()) {
            ItemStack copy = item.copy();
            AstralEnchant.LOGGER.info("copy: {}", copy);
            ItemEnchantments current = copy.get(DataComponents.ENCHANTMENTS);
            AstralEnchant.LOGGER.info("current: {}", current);
            if (current != null) {
                copy.set(
                        DataComponents.ENCHANTMENTS,
                        AEUtils.mergeItemEnchants(added.toImmutable(), current)
                );
                AstralEnchant.LOGGER.info("after: {}", copy.get(DataComponents.ENCHANTMENTS));
            }
            newItems.add(copy);
        }

        stack.set(DataComponents.BUNDLE_CONTENTS, new BundleContents(newItems));
        cir.setReturnValue(null);
        AstralEnchant.LOGGER.info("finish");
        return true;
    }

    @Unique
    private static boolean astralenchant$tryHandleOverload(
            ItemStack stack,
            ItemEnchantments enchantments,
            CallbackInfoReturnable<?> cir
    ) {
        if (!RuntimeConfigState.get(AEConfig.OVERLOAD)) return false;

        int level = AEUtils.getEnchantmentLevelFromNBT(enchantments, AEEnchantments.OVERLOAD);
        if (level <= 0) return false;

        ItemEnchantments.Mutable filtered =
                new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        enchantments.entrySet().stream()
                .filter(e -> !e.getKey().is(AEEnchantments.OVERLOAD))
                .forEach(e -> filtered.set(e.getKey(), e.getIntValue()));

        stack.set(DataComponents.ENCHANTMENTS, filtered.toImmutable());
        stack.set(
                AEDataComponents.OVERLOAD,
                stack.getOrDefault(AEDataComponents.OVERLOAD, 0) + level
        );
        cir.setReturnValue(null);
        return true;
    }

    @Unique
    private static boolean astralenchant$tryHandleItemProtection(
            ItemStack stack,
            CallbackInfoReturnable<?> cir
    ) {
        if (!RuntimeConfigState.get(AEConfig.CURSE_OF_ENCHANTMENT)) return false;
        if (AEUtils.getEnchantmentLevelFromNBT(stack, AEEnchantments.CURSE_OF_ENCHANTMENT) <= 0) return false;

        cir.setReturnValue(null);
        return true;
    }
}