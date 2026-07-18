package net.scratch221171.astralenchant.common.registries;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.common.AstralEnchant;

public class AEBlocks {
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(AstralEnchant.MOD_ID);

    public static final DeferredBlock<Block> ARCANIUM_BLOCK = registerSimpleBlock(
            "arcanium_block",
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL));

    private static DeferredBlock<Block> registerSimpleBlock(String name, BlockBehaviour.Properties props) {
        var holder = REGISTER.registerSimpleBlock(name, props);
        AEItems.REGISTER.registerSimpleBlockItem(holder);
        return holder;
    }

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
