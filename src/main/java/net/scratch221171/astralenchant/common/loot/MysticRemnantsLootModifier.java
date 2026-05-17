package net.scratch221171.astralenchant.common.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.stream.StreamSupport;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.Entity;
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
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import org.jetbrains.annotations.NotNull;

public class MysticRemnantsLootModifier extends LootModifier {
    public static final MapCodec<MysticRemnantsLootModifier> CODEC = RecordCodecBuilder.mapCodec(
            inst -> LootModifier.codecStart(inst).apply(inst, MysticRemnantsLootModifier::new));

    public MysticRemnantsLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(
            @NotNull ObjectArrayList<ItemStack> generatedLoot, @NotNull LootContext context) {
        Entity attacker = context.getParamOrNull(LootContextParams.ATTACKING_ENTITY);
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);

        if (attacker instanceof Player player && entity instanceof LivingEntity victim) {
            if (victim.getType().getCategory() == MobCategory.MONSTER) {
                ServerLevel level = context.getLevel();
                Registry<Enchantment> registry = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
                registry.getHolder(AEEnchantments.MYSTIC_REMNANTS).ifPresent(holder -> {
                    int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(holder, player);
                    // (5 + level)%で成功
                    if (enchantmentLevel > 0
                            && context.getRandom().nextDouble() <= 0.05 + (double) enchantmentLevel / 100) {
                        List<EnchantmentInstance> instances = EnchantmentHelper.selectEnchantment(
                                context.getRandom(),
                                new ItemStack(Items.BOOK),
                                enchantmentLevel * 4,
                                StreamSupport.stream(
                                        registry.getTagOrEmpty(EnchantmentTags.IN_ENCHANTING_TABLE)
                                                .spliterator(),
                                        false));
                        StreamSupport.stream(
                                registry.getTagOrEmpty(EnchantmentTags.IN_ENCHANTING_TABLE)
                                        .spliterator(),
                                false);
                        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
                        stack.getItem().applyEnchantments(stack, instances);
                        generatedLoot.add(stack);
                    }
                });
            }
        }
        return generatedLoot;
    }
}
