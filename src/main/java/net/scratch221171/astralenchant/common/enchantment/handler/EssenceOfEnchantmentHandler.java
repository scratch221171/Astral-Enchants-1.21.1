package net.scratch221171.astralenchant.common.enchantment.handler;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;
import net.scratch221171.astralenchant.common.util.AEUtils;

import java.util.Set;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class EssenceOfEnchantmentHandler {

    @SubscribeEvent
    private static void ApplyAttributeModifier(ItemAttributeModifierEvent event) {
        if (Config.ESSENCE_OF_ENCHANTMENT.isFalse()) return;
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) return;
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolderFromServer(AEEnchantments.ESSENCE_OF_ENCHANTMENT, server);

        ItemStack stack = event.getItemStack();
        int level = stack.getEnchantmentLevel(enchantment);
        if (stack.isEmpty() || level <= 0) return;

        int totalLevel = 0;
        Set<Object2IntMap.Entry<Holder<Enchantment>>> enchantments = stack.getAllEnchantments(server.registryAccess().lookupOrThrow(Registries.ENCHANTMENT)).entrySet();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments) {
            if (!entry.getKey().is(AEEnchantments.ESSENCE_OF_ENCHANTMENT)) totalLevel += entry.getIntValue();
        }
        if (Config.ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION.isTrue()) totalLevel += stack.getOrDefault(AEDataComponents.OVERLOAD, 0) * (enchantments.size() - 1);

        ItemAttributeModifiers attributeModifiers = event.getDefaultModifiers();
        double multiplier = Config.ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER.getAsDouble();

        for (ItemAttributeModifiers.Entry entry : attributeModifiers.modifiers()) {
            ResourceLocation id = entry.modifier().id();
                ResourceLocation newId = ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "eoe_bonus_" + id.getPath());
                AttributeModifier newBonusModifier = new AttributeModifier(newId, totalLevel * level * multiplier / 100f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
                event.addModifier(entry.attribute(), newBonusModifier, entry.slot());
        }
    }
}
