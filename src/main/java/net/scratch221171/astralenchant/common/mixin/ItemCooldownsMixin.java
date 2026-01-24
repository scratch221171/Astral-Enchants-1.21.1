package net.scratch221171.astralenchant.common.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.util.IItemCooldownsExtention;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemCooldowns.class)
public abstract class ItemCooldownsMixin implements IItemCooldownsExtention {
    @Shadow
    private int tickCount;

    @Unique
    private float astralenchant$cooldownReductionMultiplier = 1;

    @Override
    public void astralenchant$setCooldownReductionMultiplier(float f) { this.astralenchant$cooldownReductionMultiplier = f; }

    @Override
    public float astralenchant$getCooldownReductionMultiplier() { return this.astralenchant$cooldownReductionMultiplier; }

    /**
     * {@link net.scratch221171.astralenchant.common.datagen.ModEnchantments#COOLDOWN_REDUCTION} が付いている場合はクールダウンの終了時間を早める。
     */
    @ModifyArg(method = "addCooldown", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns$CooldownInstance;<init>(II)V"), index = 1)
    private int astralEnchant$modifyEndTick(int endTick) {
        if (!Config.COOLDOWN_REDUCTION.isTrue()) return endTick;
        int start = this.tickCount;
        int ticks = endTick - start;
        return start + (int)(ticks * this.astralenchant$cooldownReductionMultiplier);
    }

    /**
     * {@link net.scratch221171.astralenchant.common.datagen.ModEnchantments#COOLDOWN_REDUCTION} が付いている場合はクールダウンの終了時間を早める。
     */
    @ModifyArg(method = "addCooldown", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns;onCooldownStarted(Lnet/minecraft/world/item/Item;I)V"), index = 1)
    private int astralEnchant$modifyStartedTicks(int ticks) {
        if (!Config.COOLDOWN_REDUCTION.isTrue()) return ticks;
        return (int)(ticks * this.astralenchant$cooldownReductionMultiplier);
    }
}