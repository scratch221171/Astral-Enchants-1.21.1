package net.scratch221171.astralenchant.common.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

// We cannot use a record because records cannot extend other classes.
public class EBLootModifier extends LootModifier{
    // See below for how the codec works.
    public static final MapCodec<EBLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            // LootModifier#codecStart adds the conditions field.
            LootModifier.codecStart(inst).and(inst.group(
                    NumberProviders.CODEC.fieldOf("cost").forGetter(e -> e.cost),
                    TagKey.codec(Registries.ENCHANTMENT).fieldOf("enchantment_tag").forGetter(e -> e.enchantmentTag)
            )).apply(inst, EBLootModifier::new)
    );

    private final NumberProvider cost;
    private final TagKey<Enchantment> enchantmentTag;

    // First constructor parameter is the list of conditions. The rest is our extra properties.
    public EBLootModifier(LootItemCondition[] conditions, NumberProvider cost, TagKey<Enchantment> enchantmentTag) {
        super(conditions);
        this.cost = cost;
        this.enchantmentTag = enchantmentTag;
    }

    // Return our codec here.
    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    // This is where the magic happens. Use your extra properties here if needed.
    // Parameters are the existing loot, and the loot context.
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> generatedLoot, @NotNull LootContext context) {
        Optional<HolderSet.Named<Enchantment>> optional = context.getLevel().registryAccess().registryOrThrow(Registries.ENCHANTMENT).getTag(this.enchantmentTag);
        List<EnchantmentInstance> list = EnchantmentHelper.selectEnchantment(
                context.getRandom(),
                new ItemStack(Items.BOOK),
                cost.getInt(context),
                optional.orElseThrow().stream());

        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
        stack.getItem().applyEnchantments(stack, list);
        generatedLoot.add(stack);
        return generatedLoot;
    }
}