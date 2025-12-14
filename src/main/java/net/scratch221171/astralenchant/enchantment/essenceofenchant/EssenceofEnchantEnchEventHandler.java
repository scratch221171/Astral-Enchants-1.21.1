package net.scratch221171.astralenchant.enchantment.essenceofenchant;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.scratch221171.astralenchant.AstralEnchant;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class EssenceofEnchantEnchEventHandler {

//    static Holder<Enchantment> essenceofEnchant = ModEnchantments.GetRegistry().getHolder(ModEnchantments.ESSENCE_OF_ENCHANT).orElse(null);

    @SubscribeEvent
    public static void ApplyAttributeModifier(ItemAttributeModifierEvent event) {
//        ItemStack itemStack = event.getItemStack();
//
//        int enchLvl = itemStack.getEnchantmentLevel(essenceofEnchant);
//        if (enchLvl == 0) return;
//
//        ItemAttributeModifiers attributeModifiers = event.getDefaultModifiers();
//
//        for (ItemAttributeModifiers.Entry entry : attributeModifiers.modifiers()) {
//            String id = entry.modifier().id().getPath();
//
//            if (!id.matches(".*_eoe_bonus|base_attack_damage|base_attack_speed")){
//                ResourceLocation newId = ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, id + "_eoe_bonus");
//                AttributeModifier newBonusModifier = new AttributeModifier(
//                        newId,
//                        entry.modifier().amount() * enchLvl / 10f,
//                        entry.modifier().operation()
//                );
//                event.addModifier(entry.attribute(), newBonusModifier, entry.slot());
//                AstralEnchant.LOGGER.info("replaced: " + entry);
//            }
//        }
    }
}
