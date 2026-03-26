package net.scratch221171.astralenchant.common.worldgen.biome;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;

public class AEBiomeBootstrap {

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(
                AEBiomes.BABEL,
                new Biome.BiomeBuilder()
                        .hasPrecipitation(false)
                        .temperature(0.5F)
                        .downfall(0.0F)
                        .specialEffects(
                                new BiomeSpecialEffects.Builder()
                                        .waterColor(4159204)
                                        .waterFogColor(329011)
                                        .fogColor(12638463)
                                        .skyColor(calculateSkyColor(0.5F))
                                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                                        .backgroundMusic(null)
                                        .build()
                        )
                        .mobSpawnSettings(
                                new MobSpawnSettings.Builder().build()
                        )
                        .generationSettings(
                                new BiomeGenerationSettings.PlainBuilder().build()
                        )
                        .build()
        );
    }

    static int calculateSkyColor(float temperature) {
        float $$1 = temperature / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }
}
