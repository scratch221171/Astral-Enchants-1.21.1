package net.scratch221171.astralenchant.common.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ConfigRegistry {

    private static final Map<String, ConfigEntry<?>> ENTRIES = new HashMap<>();

    public static <T> void register(String key, Supplier<T> forgeGetter, T defaultValue) {
        ENTRIES.put(key, new ConfigEntry<>(key, forgeGetter, defaultValue));
    }

    public static Collection<ConfigEntry<?>> entries() {
        return ENTRIES.values();
    }

    public static ConfigEntry<?> get(String key) {
        return ENTRIES.get(key);
    }
}
