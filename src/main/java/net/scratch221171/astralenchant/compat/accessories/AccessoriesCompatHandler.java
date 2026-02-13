package net.scratch221171.astralenchant.compat.accessories;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.events.SlotStateChange;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;

public class AccessoriesCompatHandler {
    /**
     * {@link AEEnchantments#SLOT_EXPANSION} が付いていた場合スロット数を増やす。
     */
    public static void onAccessoryChanged(ItemStack prevStack, ItemStack currentStack, SlotReference reference, SlotStateChange stateChange
    ) {
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.SLOT_EXPANSION, reference.entity().level());

        int curr = currentStack.getEnchantmentLevel(enchantment);
        int prev = prevStack.getEnchantmentLevel(enchantment);

        AccessoriesCapability capability = reference.capability();
        if (capability == null) return;

        if (curr > 0) {
            // オフのとき、取り外した時にSlotModifierが永続しないように、新しく付ける箇所だけ弾く
            if (Config.SLOT_EXPANSION.isFalse()) return;
            capability.addPersistentSlotModifiers(createMap(reference.slotName(), curr));
        } else if (prev > 0) {
            capability.removeSlotModifiers(createMap(reference.slotName(), prev));
        }
    }

    private static Multimap<String, AttributeModifier> createMap(String slotName, int value) {
        Multimap<String, AttributeModifier> map = HashMultimap.create();
        map.put(
                slotName,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "_slot_bonus"),
                        value,
                        AttributeModifier.Operation.ADD_VALUE
                )
        );
        return map;
    }
}
