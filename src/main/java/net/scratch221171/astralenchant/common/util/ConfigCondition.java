package net.scratch221171.astralenchant.common.util;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public record ConfigCondition(String key) implements ICondition {
    public static final MapCodec<ConfigCondition> CODEC =
            RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Codec.STRING.fieldOf("path").forGetter(ConfigCondition::key)
            ).apply(inst, ConfigCondition::new));

    public static ConfigCondition of(ResourceKey<Enchantment> enchantment) {
        return of(enchantment.location().getPath());
    }

    public static ConfigCondition of(String key) {
        return new ConfigCondition(key);
    }

    private static final Path path = FMLPaths.CONFIGDIR.get().resolve(AstralEnchant.MOD_ID + "-server.toml");
    private static final CommentedFileConfig configFile = CommentedFileConfig.builder(path).build();

    @Override
    public boolean test(@NotNull IContext context) {
        return test();
    }

    public boolean test() {
        var config = Config.TOGGLING_CONFIG_DICT.get(key);
        configFile.load();
        // 設定ファイル生成時(ワールド作成時など)に参照できない時はデフォルト値を返す
        return configFile.getOrElse(config.getPath(), config.getDefault());
    }

    @Override
    public @NotNull MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}

