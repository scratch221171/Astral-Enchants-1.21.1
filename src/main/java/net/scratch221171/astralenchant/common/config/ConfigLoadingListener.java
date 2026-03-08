package net.scratch221171.astralenchant.common.config;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class ConfigLoadingListener {

    @SubscribeEvent
    private static void onLoading(ModConfigEvent.Loading event) {
        refresh(event);
    }

    @SubscribeEvent
    private static void onReloading(ModConfigEvent.Reloading event) {
        refresh(event);
    }

    static void refresh(ModConfigEvent event) {
        if (event.getConfig().getSpec() == AEConfig.CONFIG_SPEC) {
            RuntimeConfigState.refresh();
        }
    }
}
