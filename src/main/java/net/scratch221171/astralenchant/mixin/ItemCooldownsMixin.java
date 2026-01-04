package net.scratch221171.astralenchant.mixin;

import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.scratch221171.astralenchant.util.IItemCooldownsExtention;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemCooldowns.class)
public abstract class ItemCooldownsMixin implements IItemCooldownsExtention {
    @Shadow
    private int tickCount;

    @Shadow
    public abstract void tick();

    @Unique
    private float astralenchant$cooldownReductionMultiplier = 1;

    @Override
    public void astralenchant$setCooldownReductionMultiplier(float f) {
        this.astralenchant$cooldownReductionMultiplier = f;
    }

    @Override
    public float astralenchant$getCooldownReductionMultiplier() {
        return this.astralenchant$cooldownReductionMultiplier;
    }

    @ModifyArg(
            method = "addCooldown",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns$CooldownInstance;<init>(II)V"),
            index = 1
    )
    private int modifyEndTick(int endTick) {
        int start = this.tickCount;
        int ticks = endTick - start;
        return start + (int)(ticks * this.astralenchant$cooldownReductionMultiplier);
    }

    @ModifyArg(
            method = "addCooldown",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns;onCooldownStarted(Lnet/minecraft/world/item/Item;I)V"),
            index = 1
    )
    private int modifyStartedTicks(int ticks) {
        return (int)(ticks * this.astralenchant$cooldownReductionMultiplier);
    }
}