package net.scratch221171.astralenchant.datagen.providers;

import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;

public abstract class AELangProvider extends LanguageProvider {
    public AELangProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    protected void addAttribute(Holder<Attribute> holder, String name, String desc) {
        add(holder.unwrapKey().orElseThrow().location().toLanguageKey("attribute"), name);
        add(holder.unwrapKey().orElseThrow().location().toLanguageKey("attribute", "desc"), desc);
    }

    protected void addEnchantWithDesc(ResourceKey<Enchantment> key, String name, String desc) {
        add(key.location().toLanguageKey("enchantment"), name);
        add(key.location().toLanguageKey("enchantment", "desc"), desc);
        // configのidはエンチャントpath参照なのでここもpath参照でええやろ
        add(AstralEnchant.MOD_ID + ".configuration." + key.location().getPath(), name);
    }

    protected void addConfigWithDesc(ModConfigSpec.ConfigValue<?> key, String name, String desc) {
        add(AstralEnchant.MOD_ID + ".configuration." + key.getPath().getLast(), name);
        add(AstralEnchant.MOD_ID + ".configuration." + key.getPath().getLast() + ".tooltip", desc);
    }
}
