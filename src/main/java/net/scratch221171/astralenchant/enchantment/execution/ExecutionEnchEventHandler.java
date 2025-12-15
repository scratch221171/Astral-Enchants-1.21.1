package net.scratch221171.astralenchant.enchantment.execution;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.enchantment.ModEnchantments;

import java.util.Arrays;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class ExecutionEnchEventHandler {

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        if (!(event.getSource().getEntity() instanceof LivingEntity attacker)) return;
        if (!(event.getSource().isDirect())) return;
        if (event.getSource().is(DamageTypes.GENERIC_KILL)) return;

        LivingEntity target = event.getEntity();
        ItemStack weapon = attacker.getMainHandItem();
        if (weapon.isEmpty()) return;

        Holder<Enchantment> executionEnchantment = attacker.level().registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.EXECUTION);

        if (weapon.getEnchantmentLevel(executionEnchantment) <= 0) return;

        target.hurt(new DamageSource(target.level().damageSources().damageTypes.getHolderOrThrow(DamageTypes.GENERIC_KILL), attacker), event.getAmount());
        event.setAmount(0f);
        target.invulnerableTime = 0;
    }

    private static boolean EnchantmentCheck() {
        return true;
    }
}
