package net.scratch221171.astralenchant.common;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.scratch221171.astralenchant.common.registries.*;
import net.scratch221171.astralenchant.compat.accessories.AccessoriesCompat;
import net.scratch221171.astralenchant.compat.apotheosis.ApotheosisCompat;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AstralEnchant.MOD_ID)
public class AstralEnchant {
    public static final String MOD_ID = "astralenchant";
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public AstralEnchant(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        AEItems.register(modEventBus);
        AEBlocks.register(modEventBus);
        AEAttributes.register(modEventBus);
        AEEnchantmentEffects.register(modEventBus);
        AEDataComponents.register(modEventBus);
        AELootModifiers.register(modEventBus);
        AEConfigCondition.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.SERVER, Config.CONFIG_SPEC);

        if (ModList.get().isLoaded("accessories")) {
            AccessoriesCompat.register(modEventBus);
        }

        if (ModList.get().isLoaded("apotheosis")) {
            ApotheosisCompat.register(modEventBus);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(AEItems.ENCHANTMENT_SHARD);
            event.accept(AEItems.ARCANE_QUARTZ);
            event.accept(AEItems.ARCANIUM_INGOT);
            event.accept(AEItems.BUDDING_ARCANIUM_INGOT);
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(AEBlocks.ARCANIUM_BLOCK);
        }
    }
}
