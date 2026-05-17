package net.scratch221171.astralenchant.common.mixin.minecraft;

import net.minecraft.world.item.ItemCooldowns;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import net.scratch221171.astralenchant.common.util.IItemCooldownsExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemCooldowns.class)
public abstract class ItemCooldownsMixin implements IItemCooldownsExtension {

    @Unique private float astralenchant$cooldownDurationMultiplier = 1;

    @Override
    public void astralenchant$setMultiplier(float f) {
        this.astralenchant$cooldownDurationMultiplier = f;
    }

    @Override
    public float astralenchant$getMultiplier() {
        return this.astralenchant$cooldownDurationMultiplier;
    }

    // astralenchant$cooldownDurationMultiplier更新時にConfigチェック済みだが、付けたまま設定を切るとmultiplierが固定されて大変なので設定チェック
    /** {@link AEEnchantments#COOLDOWN_REDUCTION} が付いている場合はクールダウンの終了時間を早める。 */
    @ModifyVariable(method = "addCooldown", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int astralenchant$modifyTicks(int ticks) {
        if (AEUtils.getEnchantmentHolder(AEEnchantments.COOLDOWN_REDUCTION).isEmpty()) return ticks;

        float m = astralenchant$getMultiplier();
        if (m <= 0f) return 0;

        return (int) (ticks * m);
    }
}
