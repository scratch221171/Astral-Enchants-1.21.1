package net.scratch221171.astralenchant.compat.accessories;

import com.google.common.collect.Multimap;
import io.wispforest.accessories.api.attributes.AccessoryAttributeBuilder;
import io.wispforest.accessories.api.attributes.SlotAttribute;
import io.wispforest.accessories.api.slot.SlotReference;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;
import net.scratch221171.astralenchant.common.util.AEUtils;
import net.scratch221171.astralenchant.common.util.IAttributeSentimentExtension;

public class EssenceOfEnchantmentAccessoriesCompatHandler {

    public static void onAdjustAttributeModifier(
            ItemStack stack, SlotReference reference, AccessoryAttributeBuilder builder) {
        LivingEntity entity = reference.entity();
        AEUtils.getEnchantmentHolder(AEEnchantments.ESSENCE_OF_ENCHANTMENT, entity)
                .map(stack::getEnchantmentLevel)
                .ifPresent(level -> {
                    if (!stack.isEmpty() && level > 0) {
                        int totalLevel = 0;
                        Set<Object2IntMap.Entry<Holder<Enchantment>>> enchantments = stack.getAllEnchantments(
                                        entity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT))
                                .entrySet();
                        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments) {
                            if (!entry.getKey().is(AEEnchantments.ESSENCE_OF_ENCHANTMENT))
                                totalLevel += entry.getIntValue();
                        }
                        if (AEConfig.ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION.getAsBoolean())
                            totalLevel += stack.getOrDefault(AEDataComponents.OVERLOAD, 0) * (enchantments.size() - 1);

                        Multimap<Holder<Attribute>, AttributeModifier> attributeModifiers =
                                builder.getAttributeModifiers(false);
                        double multiplier = AEConfig.ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER.getAsDouble();

                        for (Map.Entry<Holder<Attribute>, AttributeModifier> entry : attributeModifiers
                                .entries()
                                // スロット数増加のモディファイアは除外
                                .stream()
                                .filter(entry -> !(entry.getKey().value() instanceof SlotAttribute))
                                .toList()) {
                            ResourceLocation id = entry.getValue().id();
                            ResourceLocation newId =
                                    AstralEnchant.id("eoe_bonus_" + id.getPath() + reference.slotName());

                            Attribute.Sentiment sentiment = ((IAttributeSentimentExtension)
                                            entry.getKey().value())
                                    .astralenchant$getSentiment();
                            AttributeModifier newBonusModifier;

                            switch (sentiment) {
                                // *(1 + a)
                                case POSITIVE ->
                                    newBonusModifier = new AttributeModifier(
                                            newId,
                                            totalLevel * level * multiplier / 100f,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
                                // /(1 + a)
                                case NEGATIVE ->
                                    newBonusModifier = new AttributeModifier(
                                            newId,
                                            -1 + 1 / (totalLevel * level * multiplier / 100f + 1),
                                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
                                default -> {
                                    continue;
                                }
                            }

                            builder.addStackable(entry.getKey(), newBonusModifier);
                        }
                    }
                });
    }
}
