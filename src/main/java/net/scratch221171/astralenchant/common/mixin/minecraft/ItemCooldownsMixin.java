package net.scratch221171.astralenchant.common.mixin.minecraft;

import net.minecraft.world.item.ItemCooldowns;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.IItemCooldownsExtention;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemCooldowns.class)
public abstract class ItemCooldownsMixin implements IItemCooldownsExtention {
    @Shadow
    private int tickCount;

    @Unique
    private float astralEnchant$cooldownReductionMultiplier = 1;

    @Override
    public void astralEnchant$setCooldownReductionMultiplier(float f) { this.astralEnchant$cooldownReductionMultiplier = f; }

    @Override
    public float astralEnchant$getCooldownReductionMultiplier() { return this.astralEnchant$cooldownReductionMultiplier; }

    // astralEnchant$cooldownReductionMultiplier更新時にConfigチェック済みだが、付けたまま設定を切るとmultiplierが固定されて大変なので設定チェック
    /**
     * {@link AEEnchantments#COOLDOWN_REDUCTION} が付いている場合はクールダウンの終了時間を早める。
     */
    @ModifyArg(method = "addCooldown", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns$CooldownInstance;<init>(II)V"), index = 1)
    private int astralEnchant$modifyEndTick(int endTick) {
        if (!RuntimeConfigState.get(AEConfig.COOLDOWN_REDUCTION)) return endTick;
        int start = this.tickCount;
        int ticks = endTick - start;
        return start + (int)(ticks * this.astralEnchant$cooldownReductionMultiplier);
    }

    /**
     * {@link AEEnchantments#COOLDOWN_REDUCTION} が付いている場合はクールダウンの終了時間を早める。
     */
    @ModifyArg(method = "addCooldown", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns;onCooldownStarted(Lnet/minecraft/world/item/Item;I)V"), index = 1)
    private int astralEnchant$modifyStartedTicks(int ticks) {
        if (!RuntimeConfigState.get(AEConfig.COOLDOWN_REDUCTION)) return ticks;
        return (int)(ticks * this.astralEnchant$cooldownReductionMultiplier);
    }
}