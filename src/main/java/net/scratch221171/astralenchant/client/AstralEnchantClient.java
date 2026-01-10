package net.scratch221171.astralenchant.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.scratch221171.astralenchant.common.AstralEnchant;

@Mod(value = AstralEnchant.MOD_ID, dist = Dist.CLIENT)
public class AstralEnchantClient {
    public AstralEnchantClient(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory .class, ConfigurationScreen::new);
    }

}
