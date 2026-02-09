package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.Holder;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import net.scratch221171.astralenchant.common.util.IDamageSourceExtension;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class ReactiveArmorHandler {
    @SubscribeEvent
    private static void addDisabledDamageTag(EntityInvulnerabilityCheckEvent event) {
        if (!Config.REACTIVE_ARMOR.isTrue()) return;
        DamageSource source = event.getSource();
        if (event.getEntity() instanceof LivingEntity entity) {
            Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.REACTIVE_ARMOR, entity.level());
            if (EnchantmentHelper.getEnchantmentLevel(enchantment, entity) > 0) {
                IDamageSourceExtension acc = (IDamageSourceExtension) source;
                acc.astralEnchant$addDisabledTag(DamageTypeTags.BYPASSES_ARMOR);
                acc.astralEnchant$addDisabledTag(DamageTypeTags.BYPASSES_ENCHANTMENTS);
            }
        }
    }
}
