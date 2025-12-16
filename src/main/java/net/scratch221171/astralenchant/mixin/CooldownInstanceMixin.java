package net.scratch221171.astralenchant.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.minecraft.world.item.ItemCooldowns$CooldownInstance")
public interface CooldownInstanceMixin {
    @Accessor("startTime")
    int getStartTime();

    @Accessor("endTime")
    int getEndTime();
}
