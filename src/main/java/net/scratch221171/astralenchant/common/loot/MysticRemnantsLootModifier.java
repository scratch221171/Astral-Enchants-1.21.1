package net.scratch221171.astralenchant.common.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class MysticRemnantsLootModifier extends LootModifier{
    public static final MapCodec<MysticRemnantsLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).apply(inst, MysticRemnantsLootModifier::new)
    );

    public MysticRemnantsLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> generatedLoot, @NotNull LootContext context) {
        if (!RuntimeConfigState.get(AEConfig.MYSTIC_REMNANTS)) return generatedLoot;

        if (!(context.getParamOrNull(LootContextParams.ATTACKING_ENTITY) instanceof Player player)
            || !(context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof LivingEntity victim)
            || victim.getType().getCategory() != MobCategory.MONSTER) return generatedLoot;

        int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(AEUtils.getEnchantmentHolder(AEEnchantments.MYSTIC_REMNANTS, context.getLevel()), player);
        // (5 + level)%で成功
        if (context.getRandom().nextDouble() > 0.05 + (double) enchantmentLevel / 100) return generatedLoot;

        Optional<HolderSet.Named<Enchantment>> inEnchantingTable = context.getLevel().registryAccess().registryOrThrow(Registries.ENCHANTMENT)
                .getTag(EnchantmentTags.IN_ENCHANTING_TABLE);

        List<EnchantmentInstance> list = EnchantmentHelper.selectEnchantment(
                context.getRandom(),
                new ItemStack(Items.BOOK),
                enchantmentLevel * 4,
                inEnchantingTable.orElseThrow().stream());

        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
        stack.getItem().applyEnchantments(stack, list);
        generatedLoot.add(stack);
        return generatedLoot;
    }
}