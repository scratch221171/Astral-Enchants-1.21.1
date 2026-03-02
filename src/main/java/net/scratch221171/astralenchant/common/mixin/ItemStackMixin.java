package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
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

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "set", at = @At("RETURN"), cancellable = true)
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