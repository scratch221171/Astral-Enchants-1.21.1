package net.scratch221171.astralenchant.common.enchantment.handler;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.Set;
import java.util.function.BiConsumer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
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

public class EssenceOfEnchantmentHandler {

    public static void handle(
            ItemStack stack,
            Holder<Attribute> attribute,
            BiConsumer<Holder<Attribute>, AttributeModifier> consumer,
            ResourceLocation id,
            String slotName) {
        if (!AEConfig.isEnabled(AEEnchantments.ESSENCE_OF_ENCHANTMENT)) return;

        int level = AEUtils.getEnchantmentLevel(stack, AEEnchantments.ESSENCE_OF_ENCHANTMENT);
        if (stack.isEmpty() || level <= 0) return;

        int totalLevel = 0;
        Set<Object2IntMap.Entry<Holder<Enchantment>>> enchantments =
                stack.getTagEnchantments().entrySet();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments) {
            if (!entry.getKey().is(AEEnchantments.ESSENCE_OF_ENCHANTMENT)) totalLevel += entry.getIntValue();
        }
        if (AEConfig.ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION.getAsBoolean())
            totalLevel += stack.getOrDefault(AEDataComponents.OVERLOAD, 0) * (enchantments.size() - 1);

        double multiplier = AEConfig.ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER.getAsDouble();

        ResourceLocation newId = AstralEnchant.id("eoe_bonus_" + id.getPath() + "_" + slotName);

        Attribute.Sentiment sentiment = ((IAttributeSentimentExtension) attribute.value()).astralenchant$getSentiment();
        AttributeModifier newModifier;

        switch (sentiment) {
            // *(1 + a)
            case POSITIVE ->
                newModifier = new AttributeModifier(
                        newId, totalLevel * level * multiplier / 100f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
            // /(1 + a)
            case NEGATIVE ->
                newModifier = new AttributeModifier(
                        newId,
                        -1 + 1 / (totalLevel * level * multiplier / 100f + 1),
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
            default -> {
                return;
            }
        }

        consumer.accept(attribute, newModifier);
    }
}
