package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.Holder;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AstralEnchantUtils;
import net.scratch221171.astralenchant.common.util.IDamageSourceExtension;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class MitigationPiercingHandler {
    @SubscribeEvent
    public static void addDamageTag(EntityInvulnerabilityCheckEvent event) {
        if (!Config.MITIGATION_PIERCING.isTrue()) return;
        DamageSource source = event.getSource();
        if (source.getEntity() instanceof LivingEntity attacker) {
            Holder<Enchantment> enchantment = AstralEnchantUtils.getEnchantmentHolder(AEEnchantments.MITIGATION_PIERCING, attacker.level());
            if ((source.getWeaponItem() instanceof ItemStack weapon) && weapon.getEnchantmentLevel(enchantment) > 0) {
                IDamageSourceExtension acc = (IDamageSourceExtension) source;
                acc.astralEnchant$addDamageTag(DamageTypeTags.BYPASSES_ARMOR);
                acc.astralEnchant$addDamageTag(DamageTypeTags.BYPASSES_COOLDOWN);
                acc.astralEnchant$addDamageTag(DamageTypeTags.BYPASSES_EFFECTS);
                acc.astralEnchant$addDamageTag(DamageTypeTags.BYPASSES_INVULNERABILITY);
            }
        }
    }
}
