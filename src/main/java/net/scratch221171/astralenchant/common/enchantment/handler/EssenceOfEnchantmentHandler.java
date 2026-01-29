package net.scratch221171.astralenchant.common.enchantment.handler;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
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
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AstralEnchantUtils;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class EssenceOfEnchantmentHandler {

    @SubscribeEvent
    private static void ApplyAttributeModifier(ItemAttributeModifierEvent event) {
        if (!Config.ESSENCE_OF_ENCHANTMENT.isTrue()) return;
        ItemStack stack = event.getItemStack();

        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) return;
        Holder<Enchantment> enchantment = AstralEnchantUtils.getEnchantmentHolderFromServer(AEEnchantments.ESSENCE_OF_ENCHANTMENT, server);

        int enchLvl = stack.getEnchantmentLevel(enchantment);
        if (stack.isEmpty() || enchLvl <= 0) return;

        int totalLvl = 0;
        for (Object2IntMap.Entry<Holder<Enchantment>> enchant : stack.getTagEnchantments().entrySet()) {
            if (!enchant.getKey().equals(enchantment)) totalLvl += enchant.getIntValue();
        }

        ItemAttributeModifiers attributeModifiers = event.getDefaultModifiers();

        for (ItemAttributeModifiers.Entry entry : attributeModifiers.modifiers()) {
            ResourceLocation id = entry.modifier().id();

            if (!id.getNamespace().equals(AstralEnchant.MOD_ID) || !id.getPath().matches("eoe_bonus_.*")){
                ResourceLocation newId = ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "eoe_bonus_" + id.getPath());
                AttributeModifier newBonusModifier = new AttributeModifier(
                        newId,
                        totalLvl * enchLvl / 100f,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                );
                event.addModifier(entry.attribute(), newBonusModifier, entry.slot());
            }
        }
    }
}
