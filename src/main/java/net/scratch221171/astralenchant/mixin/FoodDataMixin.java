package net.scratch221171.astralenchant.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.scratch221171.astralenchant.datagen.ModEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public abstract class FoodDataMixin {

    @Shadow
    private int tickTimer;

    @Shadow
    public abstract int getFoodLevel();

    @Unique
    private float astralenchant$overflowed = 0;

    @Inject(
            method = "add",
            at = @At("HEAD")
    )
    private void onAdd(int foodLevel, float saturationLevel, CallbackInfo ci) {
         FoodData self = (FoodData)(Object)this;
         int newFoodLevel = Math.clamp(foodLevel + self.getFoodLevel(), 0, 20);
         astralenchant$overflowed += Math.max(0, foodLevel + self.getFoodLevel() - 20);
         astralenchant$overflowed += Math.max(0, saturationLevel + self.getSaturationLevel() - newFoodLevel);
    }

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void onTick(Player player, CallbackInfo ci) {
        Holder<Enchantment> enchantment = player.level().registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.ENDLESS_APPETITE);
        if (player.getItemBySlot(EquipmentSlot.CHEST).getEnchantmentLevel(enchantment) > 0) {
            if (this.getFoodLevel() > 0) this.tickTimer = 80;
            player.heal(this.astralenchant$overflowed);
            astralenchant$overflowed = 0;
        }
    }
}
