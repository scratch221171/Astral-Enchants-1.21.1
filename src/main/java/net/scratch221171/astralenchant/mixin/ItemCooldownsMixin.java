package net.scratch221171.astralenchant.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ItemCooldowns.class)
public interface ItemCooldownsMixin {
    @Accessor("cooldowns")
    Map<Item, ?>  getCooldowns();

    @Accessor("tickCount")
    int getTickCount();
}
//@Mixin(ItemCooldowns.class)
//public abstract class ItemCooldownsMixin {
//
//    @Shadow @Final
//    private Player player;
//
//    @Inject(
//            method = "addCooldown",
//            at = @At("HEAD"),
//            cancellable = true
//    )
//    private void modifyCooldown(Item item, int originalTicks, CallbackInfo ci) {
//        ItemStack stack = player.getMainHandItem();
//
//        int reduced = applyEnchantmentReduction(player, originalTicks);
//
//        if (reduced != originalTicks) {
//            this.addCooldown(item, reduced);
//            ci.cancel();
//        }
//    }
//}