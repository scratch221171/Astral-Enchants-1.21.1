package net.scratch221171.astralenchant.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.AstralEnchant;
import net.scratch221171.astralenchant.enchantment.essenceofenchant.EssenceofEnchantEnchEffect;
import net.scratch221171.astralenchant.enchantment.execution.ExecutionEnchEffect;
import net.scratch221171.astralenchant.enchantment.itemprotection.ItemProtectionEnchEffect;
import net.scratch221171.astralenchant.enchantment.laststand.LastStandEnchEffect;

import java.util.function.Supplier;

public class ModEnchantmentEffect {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_ENTITY_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, AstralEnchant.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> EXECUTION =
            ENCHANTMENT_ENTITY_EFFECTS.register("execution", () -> ExecutionEnchEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> LAST_STAND =
            ENCHANTMENT_ENTITY_EFFECTS.register("last_stand", () -> LastStandEnchEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> ITEM_PROTECTION =
            ENCHANTMENT_ENTITY_EFFECTS.register("item_protection", () -> ItemProtectionEnchEffect.CODEC);

//    public static final Codec<TestRecord> BASIC_CODEC = RecordCodecBuilder.create(instance ->
//            instance.group(
//                    Codec.INT.fieldOf("value1").forGetter(TestRecord::val1),
//                    Codec.INT.fieldOf("value2").forGetter(TestRecord::val2)
//            ).apply(instance, TestRecord::new)
//    );
//
//    public static final DeferredRegister.DataComponents TEST = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, AstralEnchant.MOD_ID);
//
//    public static final Supplier<DataComponentType<TestRecord>> BASIC_EXAMPLE = TEST.registerComponentType(
//            "test",
//            builder -> builder
//                    // The codec to read/write the data to disk
//                    .persistent(BASIC_CODEC)
//    );

    public static void register(IEventBus eventBus) {
        ENCHANTMENT_ENTITY_EFFECTS.register(eventBus);
    }
}
