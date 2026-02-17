package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.common.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Inject(method = "getFullname", at = @At("RETURN"), cancellable = true)
    private static void getFullname(Holder<Enchantment> enchantment, int level, CallbackInfoReturnable<Component> cir) {
        if (Config.ENCHANTMENT_CONFIG_DICT.containsKey(enchantment.getKey()) && Config.ENCHANTMENT_CONFIG_DICT.get(enchantment.getKey()).isFalse()) {
            MutableComponent component = cir.getReturnValue().copy();
            component.setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withStrikethrough(true));
            component.append(Component.translatable("astralenchant.enchantment.disabled").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY).withStrikethrough(false)));
            cir.setReturnValue(component);
        }
    }
}
