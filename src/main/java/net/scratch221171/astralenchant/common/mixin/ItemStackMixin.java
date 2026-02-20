package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.scratch221171.astralenchant.common.Config;
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
    private <T> void astralEnchant$onEnchanted(
            DataComponentType<? super T> component,
            T value,
            CallbackInfoReturnable<T> cir
    ) {
        if (!astralEnchant$isEnchantmentComponent(component, value)) {
            return;
        }

        ItemStack stack = (ItemStack) (Object) this;
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) {
            return;
        }

        ItemEnchantments enchantments = (ItemEnchantments) value;

        if (astralEnchant$tryHandleBundle(stack, enchantments, server, cir)) return;
        if (astralEnchant$tryHandleOverload(stack, enchantments, server, cir)) return;
        if (astralEnchant$tryHandleItemProtection(stack, server, cir)) return;
    }

    @Unique
    private static boolean astralEnchant$isEnchantmentComponent(
            DataComponentType<?> component,
            Object value
    ) {
        return component == DataComponents.ENCHANTMENTS
                && value instanceof ItemEnchantments;
    }

    @Unique
    private static boolean astralEnchant$tryHandleBundle(
            ItemStack stack,
            ItemEnchantments enchantments,
            MinecraftServer server,
            CallbackInfoReturnable<?> cir
    ) {
        if (!Config.COMPATIBILITY.isTrue() || !stack.is(Items.BUNDLE)) {
            return false;
        }

        Holder<Enchantment> compatibility =
                AEUtils.getEnchantmentHolderFromServer(AEEnchantments.COMPATIBILITY, server);

        if (stack.getEnchantmentLevel(compatibility) <= 0) {
            return false;
        }

        BundleContents contents =
                stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        if (contents.isEmpty()) {
            return false;
        }

        ItemEnchantments.Mutable added = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        enchantments.entrySet().stream()
                .filter(e ->
                        !e.getKey().is(AEEnchantments.COMPATIBILITY)
                                && stack.getEnchantmentLevel(e.getKey()) <= 0
                )
                .forEach(e -> added.set(e.getKey(), e.getIntValue()));

        List<ItemStack> newItems = new ArrayList<>();
        for (ItemStack item : contents.items()) {
            ItemStack copy = item.copy();
            ItemEnchantments current = copy.get(DataComponents.ENCHANTMENTS);
            if (current != null) {
                copy.set(
                        DataComponents.ENCHANTMENTS,
                        AEUtils.mergeItemEnchants(added.toImmutable(), current)
                );
            }
            newItems.add(copy);
        }

        stack.set(DataComponents.BUNDLE_CONTENTS, new BundleContents(newItems));
        cir.setReturnValue(null);
        return true;
    }

    @Unique
    private static boolean astralEnchant$tryHandleOverload(
            ItemStack stack,
            ItemEnchantments enchantments,
            MinecraftServer server,
            CallbackInfoReturnable<?> cir
    ) {
        if (!Config.OVERLOAD.isTrue()) {
            return false;
        }

        Holder<Enchantment> overload =
                AEUtils.getEnchantmentHolderFromServer(AEEnchantments.OVERLOAD, server);
        int level = enchantments.getLevel(overload);
        if (level <= 0) {
            return false;
        }

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
    private static boolean astralEnchant$tryHandleItemProtection(
            ItemStack stack,
            MinecraftServer server,
            CallbackInfoReturnable<?> cir
    ) {
        if (!Config.ITEM_PROTECTION.isTrue()) {
            return false;
        }

        Holder<Enchantment> protection =
                AEUtils.getEnchantmentHolderFromServer(AEEnchantments.ITEM_PROTECTION, server);
        if (stack.getEnchantmentLevel(protection) <= 0) {
            return false;
        }

        cir.setReturnValue(null);
        return true;
    }
}