package net.scratch221171.astralenchant.enchantment.feathertouch;

import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.datagen.ModEnchantments;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class FeatherTouchEnchEventHandler {
    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();

        Level level = event.getPlayer().level();
        BlockState state = event.getState();
        BlockPos pos = event.getPos();

        Holder<Enchantment> featherTouch = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ModEnchantments.FEATHER_TOUCH);

        if (player.getMainHandItem().getEnchantmentLevel(featherTouch) <= 0)
            return;

        ItemStack stack;

        BlockEntity be = level.getBlockEntity(pos);
        if (be != null) {
            BlockHitResult hitResult = new BlockHitResult(Vec3.atCenterOf(pos), Direction.UP, pos, false);
            stack = state.getCloneItemStack(hitResult, level, pos, player);

//            CompoundTag tag = be.saveCustomOnly(level.registryAccess());
//            be.removeComponentsFromTag(tag);
//            BlockItem.setBlockEntityData(stack, be.getType(), tag);
//            stack.applyComponents(be.collectComponents());
            be.saveToItem(stack, level.registryAccess());

            be.setRemoved();
        } else {
            stack = new ItemStack(state.getBlock());
        }

        FeatherTouchCache.CACHE.put(pos, stack);

    }

    @SubscribeEvent
    public static void onDrops(BlockDropsEvent event) {
        ItemStack cached = FeatherTouchCache.CACHE.remove(event.getPos());

        if (cached == null) return;

        event.getDrops().clear();
        event.setDroppedExperience(0);

        event.getDrops().add(
                new ItemEntity(
                        event.getLevel(),
                        event.getPos().getX() + 0.5,
                        event.getPos().getY() + 0.5,
                        event.getPos().getZ() + 0.5,
                        cached
                )
        );
    }

    @SubscribeEvent
    public static void onTick(ServerTickEvent.Post event) {
        FeatherTouchCache.CACHE.clear();
    }
}
