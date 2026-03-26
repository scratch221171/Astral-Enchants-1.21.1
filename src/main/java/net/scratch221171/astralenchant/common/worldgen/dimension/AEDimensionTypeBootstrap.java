package net.scratch221171.astralenchant.common.worldgen.dimension;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.OptionalLong;

public class AEDimensionTypeBootstrap {

    public static void bootstrap(BootstrapContext<DimensionType> context) {
        context.register(
                AEDimensionTypes.LIBRARY_OF_BABEL,
                new DimensionType(
                        OptionalLong.empty(),
                        true,
                        true,
                        false,
                        false,
                        1.0,
                        true,
                        false,
                        0,
                        256,
                        256,
                        BlockTags.INFINIBURN_OVERWORLD,
                        BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                        0.0f,
                        new DimensionType.MonsterSettings(
                                true, false, UniformInt.of(0, 7), 0
                        )
                )
        );
    }

}
