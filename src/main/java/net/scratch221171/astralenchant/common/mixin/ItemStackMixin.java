package net.scratch221171.astralenchant.common.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.scratch221171.astralenchant.common.datagen.ModEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    /**
     * {@link ModEnchantments#COMPATIBILITY} が付いている場合は、バンドルにつけられようとしたエンチャントをバンドル内の各アイテムに付け、バンドル自体へのエンチャントをキャンセルする。。
     */
    @Inject(method = "set", at = @At("HEAD"), cancellable = true)
    public <T> void astralEnchant$onBundleEnchanted(DataComponentType<? super T> component, T value, CallbackInfoReturnable<T> cir) {
        if (component != DataComponents.ENCHANTMENTS || !(value instanceof ItemEnchantments itemEnchantments)) return;
        ItemStack bundle = (ItemStack)(Object)this;
        for (Object2IntMap.Entry<Holder<Enchantment>> enchantment : itemEnchantments.entrySet()) {
            if (bundle.is(Items.BUNDLE) && !enchantment.getKey().is(ModEnchantments.COMPATIBILITY)) {
                MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
                if (server == null) return;
                Holder<Enchantment> compatible = server.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.COMPATIBILITY);
                if (bundle.getEnchantmentLevel(compatible) > 0) {
                    BundleContents contents = bundle.get(DataComponents.BUNDLE_CONTENTS);
                    if (contents == null) return;
                    List<ItemStack> newItems = new ArrayList<>();

                    for (ItemStack stack : contents.items()) {
                        ItemStack copy = stack.copy();
                        copy.enchant(enchantment.getKey(), enchantment.getIntValue());
                        newItems.add(copy);
                    }

                    bundle.set(DataComponents.BUNDLE_CONTENTS, new BundleContents(newItems));
                    cir.setReturnValue(null);
                }
            }
        }
    }
}