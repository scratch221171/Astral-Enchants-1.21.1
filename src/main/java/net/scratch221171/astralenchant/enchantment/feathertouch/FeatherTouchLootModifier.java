package net.scratch221171.astralenchant.enchantment.feathertouch;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class FeatherTouchLootModifier extends LootModifier {

    public static final MapCodec<FeatherTouchLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).apply(inst, FeatherTouchLootModifier::new)
    );

    public FeatherTouchLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

//    @Override
//    protected @NotNull ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
//
//        AstralEnchant.LOGGER.info(generatedLoot.toString());
//
//        if (!(context.getParamOrNull(LootContextParams.BLOCK_STATE) instanceof BlockState state))
//            return generatedLoot;
//
//        if (!(context.getParamOrNull(LootContextParams.BLOCK_ENTITY) instanceof BlockEntity be))
//            return generatedLoot;
//
//        AstralEnchant.LOGGER.info("next");
//
//        Level level = context.getLevel();
//        Player player = context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player p ? p : null;
//        BlockPos pos = be.getBlockPos();
//
//        // 既存ドロップを破棄
//        generatedLoot.clear();
//
//        BlockHitResult hitResult = BlockHitResult.miss(Vec3.atCenterOf(pos), Direction.UP, pos);
//
//        ItemStack stack = state.getCloneItemStack(hitResult, level, pos, player);
//
//        // BlockEntity の完全な NBT を保存
//        CompoundTag tag = be.saveWithFullMetadata(level.registryAccess());
//        BlockItem.setBlockEntityData(stack, be.getType(), tag);
//
//        AstralEnchant.LOGGER.info(stack.toString());
//
//        generatedLoot.add(stack);
//        return generatedLoot;
//    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
//        if (!(context.getParamOrNull(LootContextParams.BLOCK_STATE) instanceof BlockState state)) return generatedLoot;
//
//        generatedLoot.clear();
//        ItemStack stack;
//
//        if (context.getParamOrNull(LootContextParams.BLOCK_ENTITY) instanceof BlockEntity be) {
//            BlockPos pos = be.getBlockPos();
//            BlockHitResult hitResult = new BlockHitResult(Vec3.atCenterOf(pos), Direction.UP, pos, false);
//            stack = state.getCloneItemStack(hitResult, context.getLevel(), pos, context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player p ? p : null);
//            AstralEnchant.LOGGER.info(String.valueOf(be.collectComponents().get(DataComponents.CONTAINER).getStackInSlot(0)));
//            CompoundTag compoundtag = be.saveCustomAndMetadata(context.getLevel().registryAccess());
//            be.removeComponentsFromTag(compoundtag);
//            BlockItem.setBlockEntityData(stack, be.getType(), compoundtag);
//            stack.applyComponents(be.collectComponents());
//        } else {
//            stack = state.getBlock().asItem().getDefaultInstance();
//        }
//
//        generatedLoot.add(stack);
        return generatedLoot;
    }
}

