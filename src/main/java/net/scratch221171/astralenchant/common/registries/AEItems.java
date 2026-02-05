package net.scratch221171.astralenchant.common.registries;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.common.AstralEnchant;

public class AEItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AstralEnchant.MOD_ID);

    public static final DeferredItem<Item> ENCHANTMENT_SHARD = ITEMS.register("enchantment_shard", () -> new Item(
            new Item.Properties().rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
    ));

    public static final DeferredItem<Item> ARCANIUM_INGOT = ITEMS.register("arcanium_ingot", () -> new Item(
            new Item.Properties()
    ));

    public static final DeferredItem<Item> SHARD_EMBEDDED_ARCANIUM_INGOT = ITEMS.register("shard_embedded_arcanium_ingot", () -> new Item(
            new Item.Properties().rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
    ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
