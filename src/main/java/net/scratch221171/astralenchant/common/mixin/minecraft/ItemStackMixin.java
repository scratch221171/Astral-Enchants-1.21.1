package net.scratch221171.astralenchant.common.mixin.minecraft;

import java.util.function.BiConsumer;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.common.NeoForge;
import net.scratch221171.astralenchant.common.enchantment.handler.EssenceOfEnchantmentHandler;
import net.scratch221171.astralenchant.common.event.ItemEnchantmentSetEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    // 実際のステータス計算に関わる方
    @ModifyVariable(
            method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V",
            at = @At("HEAD"),
            argsOnly = true)
    private BiConsumer<Holder<Attribute>, AttributeModifier> wrap(
            BiConsumer<Holder<Attribute>, AttributeModifier> original, EquipmentSlot slot) {
        return (attr, mod) -> {
            EssenceOfEnchantmentHandler.handle(
                    (ItemStack) (Object) this, attr, original, mod.id(), slot.getSerializedName());
            original.accept(attr, mod);
        };
    }

    // Tooltipなど、見た目だけ
    @ModifyVariable(
            method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlotGroup;Ljava/util/function/BiConsumer;)V",
            at = @At("HEAD"),
            argsOnly = true)
    private BiConsumer<Holder<Attribute>, AttributeModifier> wrap(
            BiConsumer<Holder<Attribute>, AttributeModifier> original, EquipmentSlotGroup slotGroup) {
        return (attr, mod) -> {
            EssenceOfEnchantmentHandler.handle(
                    (ItemStack) (Object) this, attr, original, mod.id(), slotGroup.getSerializedName());
            original.accept(attr, mod);
        };
    }

    @SuppressWarnings("unchecked")
    @Inject(method = "set", at = @At("HEAD"), cancellable = true)
    private <T> void astralenchant$onEnchanted(
            DataComponentType<? super T> component, T value, CallbackInfoReturnable<T> cir) {
        if (!(component == DataComponents.ENCHANTMENTS && value instanceof ItemEnchantments enchantments)) return;

        ItemStack stack = (ItemStack) (Object) this;

        if (NeoForge.EVENT_BUS
                .post(new ItemEnchantmentSetEvent(stack, enchantments))
                .isCanceled()) {
            cir.setReturnValue((T) stack.get(DataComponents.ENCHANTMENTS));
        }
    }
}
