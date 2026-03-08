package net.scratch221171.astralenchant.common.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;
import org.jetbrains.annotations.NotNull;

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

//    private static final Path path = FMLPaths.CONFIGDIR.get().resolve(AstralEnchant.MOD_ID + "-server.toml");
//    private static final CommentedFileConfig configFile = CommentedFileConfig.builder(path).build();
//
//    private static boolean loaded = false;

    @Override
    public boolean test(@NotNull IContext context) {
        return test();
    }

    public boolean test() {
//        var config = AEConfig.TOGGLING_CONFIG_DICT.get(key);
//        AstralEnchant.LOGGER.info(config.toString());
//        configFile.load();
//        // 設定ファイル生成時(ワールド作成時など)に参照できない時はデフォルト値を返す
//        AstralEnchant.LOGGER.info(configFile.getOrElse(config.getPath(), config.getDefault()).toString());
//        AstralEnchant.LOGGER.info(configFile.toString());
        return RuntimeConfigState.get(key);
    }

    @Override
    public @NotNull MapCodec<? extends ICondition> codec() {
        return CODEC;
    }

}

