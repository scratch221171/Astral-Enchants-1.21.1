package net.scratch221171.astralenchant.compat.accessories;

import com.google.common.collect.Multimap;
import io.wispforest.accessories.api.attributes.AccessoryAttributeBuilder;
import io.wispforest.accessories.api.attributes.SlotAttribute;
import io.wispforest.accessories.api.slot.SlotReference;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;
import net.scratch221171.astralenchant.common.util.AEUtils;

import java.util.Map;
import java.util.Set;

public class EssenceOfEnchantmentAccessoriesCompatHandler {

    public static void onAdjustAttributeModifier(ItemStack stack, SlotReference reference, AccessoryAttributeBuilder builder) {
        if (Config.ESSENCE_OF_ENCHANTMENT.isFalse()) return;
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.ESSENCE_OF_ENCHANTMENT, reference.entity().level());
        int level = stack.getEnchantmentLevel(enchantment);
        if (!stack.isEmpty() && level > 0) {
            int totalLevel = 0;
            Set<Object2IntMap.Entry<Holder<Enchantment>>> enchantments = stack.getAllEnchantments(reference.entity().level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT)).entrySet();
            for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments) {
                if (!entry.getKey().is(AEEnchantments.ESSENCE_OF_ENCHANTMENT)) totalLevel += entry.getIntValue();
            }
            if (Config.ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION.isTrue())
                totalLevel += stack.getOrDefault(AEDataComponents.OVERLOAD, 0) * (enchantments.size() - 1);

            Multimap<Holder<Attribute>, AttributeModifier> attributeModifiers = builder.getAttributeModifiers(false);
            double multiplier = Config.ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER.getAsDouble();

            for (Map.Entry<Holder<Attribute>, AttributeModifier> entry : attributeModifiers.entries()
                    // スロット数増加のモディファイアは除外
                    .stream().filter(entry -> !(entry.getKey().value() instanceof SlotAttribute)).toList()) {
                ResourceLocation id = entry.getValue().id();
                ResourceLocation newId = ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "eoe_bonus_" + id.getPath());
                AttributeModifier newBonusModifier = new AttributeModifier(newId, totalLevel * level * multiplier / 100f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
                builder.addStackable(entry.getKey(), newBonusModifier);
            }
        }
    }
}
