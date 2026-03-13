package net.scratch221171.astralenchant.common.registries;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.common.AstralEnchant;


public class AEAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(Registries.ATTRIBUTE, AstralEnchant.MOD_ID);

    public static final Holder<Attribute> COOLDOWN_DURATION = ATTRIBUTES.register("cooldown_duration",
            () -> new RangedAttribute("attribute.astralenchant.cooldown_duration", 1.0, 0.0, 1024.0)
                    .setSentiment(Attribute.Sentiment.NEGATIVE).setSyncable(true));

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
