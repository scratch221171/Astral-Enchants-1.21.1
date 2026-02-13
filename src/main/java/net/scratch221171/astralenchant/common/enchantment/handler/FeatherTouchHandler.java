package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
import net.scratch221171.astralenchant.common.datagen.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import net.scratch221171.astralenchant.common.util.FeatherTouchCache;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class FeatherTouchHandler {
    @SubscribeEvent
    private static void onBreak(BlockEvent.BreakEvent event) {
        if (Config.FEATHER_TOUCH.isFalse()) return;
        Player player = event.getPlayer();

        Level level = event.getPlayer().level();
        BlockState state = event.getState();
        BlockPos pos = event.getPos();
        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.FEATHER_TOUCH, level);
        if (player.getMainHandItem().getEnchantmentLevel(enchantment) <= 0) return;

        // 複数ブロックのもの(ドアやベッド)を除外する
        if (checkBlockState(state, BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER) ||
            checkBlockState(state, BlockStateProperties.BED_PART, BedPart.FOOT)) return;

        ItemStack stack;
        BlockEntity be = level.getBlockEntity(pos);
        if (player.isCrouching()) {
            if (be != null) {
                BlockHitResult hitResult = new BlockHitResult(Vec3.atCenterOf(pos), Direction.UP, pos, false);
                stack = state.getCloneItemStack(hitResult, level, pos, player);
                be.saveToItem(stack, level.registryAccess());
                be.setRemoved();
            } else {
                stack = new ItemStack(state.getBlock());
                BlockItemStateProperties properties = BlockItemStateProperties.EMPTY;
                for (Property<?> property : state.getProperties()) {
                    properties = properties.with(property, state);
                }
                stack.set(DataComponents.BLOCK_STATE, properties);
            }
            ((ServerLevel)level).sendParticles(ParticleTypes.ENCHANT, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, 20, 0.5f, 0.5f, 0.5f, 1f);
        } else {
            stack = new ItemStack(state.getBlock());
        }

        FeatherTouchCache.CACHE.put(pos, stack);
    }

    @SubscribeEvent
    private static void onDrops(BlockDropsEvent event) {
        if (Config.FEATHER_TOUCH.isFalse()) return;
        ItemStack cached = FeatherTouchCache.CACHE.remove(event.getPos());

        if (cached == null) return;

        event.getDrops().clear();
        event.setDroppedExperience(0);

        event.getDrops().add(new ItemEntity(event.getLevel(), event.getPos().getX() + 0.5, event.getPos().getY() + 0.5, event.getPos().getZ() + 0.5, cached));
    }

    @SubscribeEvent
    public static void onTick(ServerTickEvent.Post event) {
        FeatherTouchCache.CACHE.clear();
    }

    static <T extends Comparable<T>> boolean checkBlockState(BlockState state, Property<T> properties, T value) {
        return state.hasProperty(properties) && state.getValue(properties) == value;
    }
}
