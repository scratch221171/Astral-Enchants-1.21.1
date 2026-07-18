package net.scratch221171.astralenchant.common;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.scratch221171.astralenchant.common.condition.ConfigCondition;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.enchantment.effect.OverMendingEffect;
import net.scratch221171.astralenchant.common.loot.MysticRemnantsLootModifier;
import net.scratch221171.astralenchant.common.registries.*;
import net.scratch221171.astralenchant.compat.accessories.AccessoriesCompat;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AstralEnchant.MOD_ID)
public class AstralEnchant {
    public static final String MOD_ID = "astralenchant";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static ResourceLocation id(String... path) {
        return id(String.join("/", path));
    }

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public AstralEnchant(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::registerMisc);
        modEventBus.addListener(this::commonSetup);

        // registriesを呼ぶ
        AEItems.register(modEventBus);
        AEBlocks.register(modEventBus);
        AEAttributes.register(modEventBus);
        AEDataComponents.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.SERVER, AEConfig.CONFIG_SPEC);

        // 連携
        if (ModList.get().isLoaded("accessories")) {
            AccessoriesCompat.register(modEventBus);
        }
    }

    private void registerMisc(RegisterEvent event) {
        event.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, helper -> {
            helper.register(id("over_mending"), OverMendingEffect.CODEC);
        });

        event.register(NeoForgeRegistries.Keys.CONDITION_CODECS, helper -> {
            helper.register(id("config_condition"), ConfigCondition.CODEC);
        });
        event.register(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> {
            helper.register(id("mystic_remnants_loot_modifier"), MysticRemnantsLootModifier.CODEC);
        });
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

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
