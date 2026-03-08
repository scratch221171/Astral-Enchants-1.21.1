package net.scratch221171.astralenchant.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ConfigRegistry {

    private static final Map<String, ConfigEntry<?>> ENTRIES = new HashMap<>();

    public static <T> void register(String key, ModConfigSpec.ConfigValue<T> configValue, T defaultValue) {
        ENTRIES.put(key, new ConfigEntry<>(key, configValue::get, defaultValue));
        AEConfig.put(configValue, key);
    }

    public static Collection<ConfigEntry<?>> entries() {
        return ENTRIES.values();
    }

    public static ConfigEntry<?> get(String key) {
        return ENTRIES.get(key);
    }
}
