package net.scratch221171.astralenchant.common.config;

import java.util.function.Supplier;

public record ConfigEntry<T>(String key, Supplier<T> forgeGetter, T defaultValue) {

    public T read() {
        try {
            return forgeGetter.get();
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
