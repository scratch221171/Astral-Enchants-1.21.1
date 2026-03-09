package net.scratch221171.astralenchant.common.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.scratch221171.astralenchant.common.AstralEnchant;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class RuntimeConfigState {

    private static final Map<String, Object> VALUES = new HashMap<>();

    static Path path = FMLPaths.CONFIGDIR.get().resolve(AstralEnchant.MOD_ID + "-server.toml");
    static CommentedFileConfig config = CommentedFileConfig.builder(path).build();

    public static void bootstrap() {

        config.load();

        for (ConfigEntry<?> entry : ConfigRegistry.entries()) {

            Object value = config.get(entry.key());

            if (value == null) {
                value = entry.defaultValue();
            }

            VALUES.put(entry.key(), value);
        }
    }

    public static void refresh() {

        for (ConfigEntry<?> entry : ConfigRegistry.entries()) {
            VALUES.put(entry.key(), entry.read());
        }
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T get(String key) {
        return (T) VALUES.get(key);
    }

    public static <T> T get(ModConfigSpec.ConfigValue<T> configValue) {
        return get(AEConfig.get(configValue));
    }
}