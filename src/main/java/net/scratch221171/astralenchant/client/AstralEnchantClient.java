package net.scratch221171.astralenchant.client;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.scratch221171.astralenchant.client.itemRenderer.XPBarDecorator;
import net.scratch221171.astralenchant.common.AstralEnchant;

@Mod(value = AstralEnchant.MOD_ID, dist = Dist.CLIENT)
public class AstralEnchantClient {
    public AstralEnchantClient(IEventBus eventBus, ModContainer modContainer) {
        eventBus.addListener((RegisterItemDecorationsEvent event) -> {
            // BuiltInRegistriesやRegistries経由で全アイテムを取得して登録
            for (Item item : BuiltInRegistries.ITEM) {
                if (item != Items.AIR) {
                    event.register(item, new XPBarDecorator());
                }
            }
        });

        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
