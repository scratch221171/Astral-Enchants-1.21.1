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
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;
import net.scratch221171.astralenchant.common.util.AstralEnchantUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    /**
     * {@link AEEnchantments#COMPATIBILITY} が付いている場合は、バンドルにつけられようとしたエンチャントをバンドル内の各アイテムに付け、バンドル自体へのエンチャントをキャンセルする。
     * {@link AEEnchantments#OVERLOAD} が付与されようとしたエンチャントに含まれていた場合は、このエンチャントを削除し、そのレベルだけ {@link AEDataComponents#OVERLOAD} を増やす。
     * {@link AEEnchantments#ITEM_PROTECTION} が付いている場合はエンチャントの編集を無効化する。
     */
    @Inject(method = "set", at = @At("HEAD"), cancellable = true)
    public <T> void astralEnchant$onEnchanted(DataComponentType<? super T> component, T value, CallbackInfoReturnable<T> cir) {
        if (component != DataComponents.ENCHANTMENTS || !(value instanceof ItemEnchantments itemEnchantments)) return;
        ItemStack stack = (ItemStack)(Object)this;
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) return;

        // バンドル
        if (Config.COMPATIBILITY.isTrue() && stack.is(Items.BUNDLE)) {
            Holder<Enchantment> compatible = AstralEnchantUtils.getEnchantmentHolderFromServer(AEEnchantments.COMPATIBILITY, server);
            BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            if (stack.getEnchantmentLevel(compatible) > 0 && !contents.isEmpty()) {
                ItemEnchantments.Mutable newEnchantments = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                itemEnchantments.entrySet().stream()
                        // Compatible自身および現在バンドルについているエンチャントは、中身に付与されないようにする(つまり新しく追加されたエンチャントのみ付与する)
                        // バンドル自身と合成されたエンチャント本に同じエンチャントが含まれていた場合は...知りません
                        .filter(entry -> !entry.getKey().is(AEEnchantments.COMPATIBILITY) && stack.getEnchantmentLevel(entry.getKey()) <= 0)
                        .forEach(entry -> newEnchantments.set(entry.getKey(), entry.getIntValue()));

                List<ItemStack> newItems = new ArrayList<>();
                for (ItemStack s : contents.items()) {
                    ItemStack copy = s.copy();
                    copy.set(DataComponents.ENCHANTMENTS, newEnchantments.toImmutable());
                    newItems.add(copy);
                }

                stack.set(DataComponents.BUNDLE_CONTENTS, new BundleContents(newItems));
                cir.setReturnValue(null);
                // バンドルへのOverload付与&ItemProtectionを無視する
                return;
            }
        }

        // Overload コンポーネントを更新
        if (Config.OVERLOAD.isTrue()) {
            Holder<Enchantment> overload = AstralEnchantUtils.getEnchantmentHolderFromServer(AEEnchantments.OVERLOAD, server);
            int level = itemEnchantments.getLevel(overload);
            if (level > 0) {
                ItemEnchantments.Mutable newEnchantments = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                itemEnchantments.entrySet().stream()
                        .filter(entry -> !entry.getKey().is(AEEnchantments.OVERLOAD))
                        .forEach(entry -> newEnchantments.set(entry.getKey(), entry.getIntValue()));
                stack.set(DataComponents.ENCHANTMENTS, newEnchantments.toImmutable());
                stack.set(AEDataComponents.OVERLOAD, stack.getOrDefault(AEDataComponents.OVERLOAD, 0) + level);
                cir.setReturnValue(null);
            }
        }

        // エンチャントを変更不可にする
        if (Config.ITEM_PROTECTION.isTrue()) {
            Holder<Enchantment> enchantment = AstralEnchantUtils.getEnchantmentHolderFromServer(AEEnchantments.ITEM_PROTECTION, server);
            if (stack.getEnchantmentLevel(enchantment) > 0) {
                cir.setReturnValue(null);
            }
        }
    }
}