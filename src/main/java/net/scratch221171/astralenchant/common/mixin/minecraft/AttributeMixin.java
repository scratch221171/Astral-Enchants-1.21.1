package net.scratch221171.astralenchant.common.mixin.minecraft;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.scratch221171.astralenchant.common.util.IAttributeSentimentExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Attribute.class)
public abstract class AttributeMixin implements IAttributeSentimentExtension {
    @Shadow
    private Attribute.Sentiment sentiment;

    @Override
    public Attribute.Sentiment astralenchant$getSentiment() {
        return sentiment;
    }
}
