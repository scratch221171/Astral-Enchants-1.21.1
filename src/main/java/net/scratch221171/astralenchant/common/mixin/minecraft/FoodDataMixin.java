package net.scratch221171.astralenchant.common.mixin.minecraft;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
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

    /**
     * {@link AEEnchantments#ENDLESS_APPETITE} が付いている場合は溢れた(隠し)満腹度分だけ回復する。
     */
    @Inject(method = "add", at = @At("HEAD"))
    private void astralenchant$onAdd(int foodLevel, float saturationLevel, CallbackInfo ci) {
        if (!RuntimeConfigState.get(AEConfig.ENDLESS_APPETITE)) return;
        FoodData self = (FoodData)(Object)this;
        int newFoodLevel = Math.clamp(foodLevel + self.getFoodLevel(), 0, 20);
        astralenchant$overflowed += Math.max(0, foodLevel + self.getFoodLevel() - 20);
        astralenchant$overflowed += Math.max(0, saturationLevel + self.getSaturationLevel() - newFoodLevel);
    }

    /**
     * {@link AEEnchantments#ENDLESS_APPETITE} が付いている場合は自然治癒を加速する。
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void astralenchant$onTick(Player player, CallbackInfo ci) {
        if (!RuntimeConfigState.get(AEConfig.ENDLESS_APPETITE)) return;
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.ENDLESS_APPETITE, player.level());
        if (EnchantmentHelper.getEnchantmentLevel(enchantment, player) > 0) {
            if (this.getFoodLevel() > 0) this.tickTimer = 80;
            player.heal(this.astralenchant$overflowed);
            astralenchant$overflowed = 0;
        }
    }
}
