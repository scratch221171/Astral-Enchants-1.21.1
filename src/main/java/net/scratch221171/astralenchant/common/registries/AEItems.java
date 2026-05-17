package net.scratch221171.astralenchant.common.registries;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.common.AstralEnchant;

public class AEItems {
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(AstralEnchant.MOD_ID);

    public static final DeferredItem<Item> ENCHANTMENT_SHARD = REGISTER.registerSimpleItem(
            "enchantment_shard",
            new Item.Properties().rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));

    public static final DeferredItem<Item> ARCANE_QUARTZ = REGISTER.registerSimpleItem("arcane_quartz");

    public static final DeferredItem<Item> ARCANIUM_INGOT = REGISTER.registerSimpleItem("arcanium_ingot");

    public static final DeferredItem<Item> BUDDING_ARCANIUM_INGOT =
            REGISTER.registerSimpleItem("budding_arcanium_ingot");

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
