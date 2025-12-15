package net.scratch221171.astralenchant.enchantment.essenceofenchant;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.enchantment.ModEnchantmentComponents;
import net.scratch221171.astralenchant.enchantment.ModEnchantments;
import net.scratch221171.astralenchant.enchantment.itemprotection.ItemProtectionRestoreQueue;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class EssenceofEnchantEnchEventHandler {

//    @SubscribeEvent
//    private static void ApplyAttributeModifier(PlayerTickEvent.Pre event) {
//        Player player = event.getEntity();
//        if (player.level().isClientSide()) return;
//
//        MinecraftServer server = player.getServer();
//        if (server == null) return;
//
//        Holder<Enchantment> essenceOfEnchant = server.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.ITEM_PROTECTION);
//        tickInv(player, essenceOfEnchant);
//    }
//
//    private static void tickInv(Player player, Holder<Enchantment> essenceOfEnchant) {
//        Inventory inv = player.getInventory();
//
//        for (int i = 0; i < inv.getContainerSize(); i++) {
//            ItemStack stack = inv.getItem(i);
//            int enchLvl = stack.getEnchantmentLevel(essenceOfEnchant);
//            if (stack.isEmpty() && enchLvl <= 0) continue;
//
//            int totalEnchLvl = getTotalEnchLvl(stack);
//
//            applyModifier(stack, totalEnchLvl, enchLvl);
//        }
//    }
//
//    private static int getTotalEnchLvl(ItemStack stack) {
//        int totalLvl = 0;
//        for (Object2IntMap.Entry<Holder<Enchantment>> enchant : stack.getTagEnchantments().entrySet()) {
//            totalLvl += enchant.getIntValue();
//        }
//        return totalLvl;
//    }
//
//    private static void applyModifier(ItemStack stack, int enchLvl, int totalEnchLvl) {
//        ItemAttributeModifiers modifiers = stack.getAttributeModifiers();
//
//        for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
//            String id = entry.modifier().id().getPath();
//
//            if (!id.matches(".*_eoe_bonus")){
//                ResourceLocation newId = ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, id + "_eoe_bonus");
//                AttributeModifier newBonusModifier = new AttributeModifier(
//                        newId,
//                        totalEnchLvl * enchLvl / 100f,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
//                );
//
//                stack.getAttributeModifiers().modifiers().add(new ItemAttributeModifiers.Entry(entry.attribute(), newBonusModifier, entry.slot()));
//
//                AstralEnchant.LOGGER.info("processed: " + entry);
//            }
//        }
//    }
    @SubscribeEvent
    private static void ApplyAttributeModifier(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();

        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) return;
        Holder<Enchantment> essenceOfEnchant = server.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.ESSENCE_OF_ENCHANT);

        int enchLvl = stack.getEnchantmentLevel(essenceOfEnchant);
        if (stack.isEmpty() || enchLvl <= 0) return;

        int totalLvl = 0;
        for (Object2IntMap.Entry<Holder<Enchantment>> enchant : stack.getTagEnchantments().entrySet()) {
            if (!enchant.getKey().equals(essenceOfEnchant)) totalLvl += enchant.getIntValue();
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

//        ItemAttributeModifiers modifiers = stack.getAttributeModifiers();
//
//        for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
//            String id = entry.modifier().id().equals()
//
//            if (!id.matches(".*_eoe_bonus")){
//                ResourceLocation newId = ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, id + "_eoe_bonus");
//                AttributeModifier newBonusModifier = new AttributeModifier(
//                        newId,
//                        totalLvl * enchLvl / 100f,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
//                );
//
//                event.addModifier(entry.attribute(), newBonusModifier, entry.slot());
//
//                AstralEnchant.LOGGER.info("processed: " + entry);
//            }
//        }
    }
}
