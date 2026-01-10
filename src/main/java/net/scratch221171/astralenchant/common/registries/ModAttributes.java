package net.scratch221171.astralenchant.common.registries;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.common.AstralEnchant;


public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(Registries.ATTRIBUTE, AstralEnchant.MOD_ID);

    public static final Holder<Attribute> COOLDOWN_REDUCTION = ATTRIBUTES.register("cooldown_reduction",
            () -> new RangedAttribute("astralenchant:cooldown_reduction", 1, 0, 1024).setSyncable(true));

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
