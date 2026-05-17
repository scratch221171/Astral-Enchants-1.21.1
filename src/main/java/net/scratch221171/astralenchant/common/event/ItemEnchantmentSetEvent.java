package net.scratch221171.astralenchant.common.event;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

/**
 * {@link ItemStack#set(DataComponentType, Object)}によってアイテムのエンチャントが変更される時に呼ばれます。 <br>
 * このイベントをキャンセルすると、エンチャントは変更されません。 <br>
 * このイベントは{@link net.scratch221171.astralenchant.common.mixin.minecraft.ItemStackMixin}を通して呼ばれます <br>
 */
public class ItemEnchantmentSetEvent extends Event implements ICancellableEvent {

    private final ItemStack stack;
    private final ItemEnchantments enchantments;

    public ItemEnchantmentSetEvent(ItemStack stack, ItemEnchantments enchantments) {
        this.stack = stack;
        this.enchantments = enchantments;
    }

    public ItemStack getStack() {
        return stack;
    }

    public ItemEnchantments getEnchantments() {
        return enchantments;
    }
}
