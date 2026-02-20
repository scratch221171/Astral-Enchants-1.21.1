package net.scratch221171.astralenchant.common.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.common.AstralEnchant;

import java.util.ArrayList;
import java.util.List;

public class AEEnchantments {
    public static final List<ResourceKey<Enchantment>> ENCHANTMENTS = new ArrayList<>();

    /**
     * 与えた攻撃に様々なダメージタイプタグを付与し、ダメージ軽減を貫通する。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.common.enchantment.handler.MitigationPiercingHandler}
     * <p>
     * Mixin : {@link net.scratch221171.astralenchant.common.mixin.DamageSourceMixin}
     */
    public static final ResourceKey<Enchantment> MITIGATION_PIERCING = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "mitigation_piercing"));

    /**
     * 経験値を消費して死亡イベントをキャンセルする。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.common.enchantment.handler.LastStandHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> LAST_STAND = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "last_stand"));

    /**
     * 耐久力を除くアイテムのDataComponentを毎ティック置き換える。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.common.enchantment.handler.ItemProtectionHandler}
     * <p>
     * Mixin : {@link net.scratch221171.astralenchant.common.mixin.PlayerMixin}, {@link net.scratch221171.astralenchant.common.mixin.ItemStackMixin}
     */
    public static final ResourceKey<Enchantment> ITEM_PROTECTION = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "item_protection"));

    /**
     * アイテムの合計エンチャントレベル(自身を除く)に応じて全てのAttributeModifierを上昇させる。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.common.enchantment.handler.EssenceOfEnchantmentHandler}, {@link net.scratch221171.astralenchant.compat.accessories.EssenceOfEnchantmentAccessoriesCompatHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> ESSENCE_OF_ENCHANTMENT = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "essence_of_enchantment"));

    /**
     * アイテム使用のクールダウンを短縮する。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.common.enchantment.handler.CooldownAttributeHandler}
     * <p>
     * Mixin : {@link net.scratch221171.astralenchant.common.mixin.ItemCooldownsMixin}
     */
    public static final ResourceKey<Enchantment> COOLDOWN_REDUCTION = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "cooldown_reduction"));

    /**
     * ブロック破壊時に必ずドロップするようにし、スニーク時はBlockStateやBlockEntityのコンポーネントを保持したままアイテム化させる。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.common.enchantment.handler.FeatherTouchHandler}
     * <p>
     * Mixin : {@link net.scratch221171.astralenchant.common.mixin.LocalPlayerMixin}, {@link net.scratch221171.astralenchant.common.mixin.PlayerMixin}
     */
    public static final ResourceKey<Enchantment> FEATHER_TOUCH = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "feather_touch"));

    /**
     * 総実績数に応じて運とブロック由来の経験値を増加させる。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.common.enchantment.handler.AdventurersLoreHandler}
     * <p>
     * Mixin : none
     * */
    public static final ResourceKey<Enchantment> ADVENTURERS_LORE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "adventurers_lore"));

    /**
     * このエンチャントがついたバンドルに付けられたエンチャントが、競合などを無視して内部アイテムに付与される。
     * <p>
     * Handler : none
     * <p>
     * Mixin : {@link net.scratch221171.astralenchant.common.mixin.ItemStackMixin}, {@link net.scratch221171.astralenchant.common.mixin.IItemExtensionMixin}
     */
    public static final ResourceKey<Enchantment> COMPATIBILITY = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "compatibility"));

    /**
     * 溢れた満腹度分だけ回復し、自然治癒を加速し、常時食事可能にする。
     * <p>
     * Handler : none
     * <p>
     * Mixin : {@link net.scratch221171.astralenchant.common.mixin.FoodDataMixin}, {@link net.scratch221171.astralenchant.common.mixin.PlayerMixin}
     */
    public static final ResourceKey<Enchantment> ENDLESS_APPETITE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "endless_appetite"));

    /**
     * 様々な移動速度低下効果を無効化する。
     * <p>
     * Handler : none
     * <p>
     * Mixin : {@link net.scratch221171.astralenchant.common.mixin.LocalPlayerMixin}, {@link net.scratch221171.astralenchant.common.mixin.PlayerMixin}, {@link net.scratch221171.astralenchant.common.mixin.EntityMixin}
     */
    public static final ResourceKey<Enchantment> MOMENTUM = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "momentum"));

    /**
     * エンダーパール使用時、瞬時に視線の先に真っ直ぐテレポートする。スニーク時はブロックを貫通する。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.common.enchantment.handler.InstantTeleportHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> INSTANT_TELEPORT = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "instant_teleport"));

    /**
     * {@link net.scratch221171.astralenchant.common.registries.AEDataComponents#OVERLOAD} の値だけ全てのエンチャントのレベルを上昇させる。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.common.enchantment.handler.OverloadHandler}
     * <p>
     * Mixin : {@link net.scratch221171.astralenchant.common.mixin.ItemStackMixin}, {@link net.scratch221171.astralenchant.common.mixin.IItemExtensionMixin}, {@link net.scratch221171.astralenchant.common.mixin.EnchantmentHelperMixin}
     */
    public static final ResourceKey<Enchantment> OVERLOAD = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "overload"));

    /**
     * Accessories連携：アイテムが装着されたスロットの数をエンチャントのレベルだけ増やす。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.compat.accessories.SlotExpansionHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> SLOT_EXPANSION = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "slot_expansion"));

    /**
     * 与えられた攻撃から防具貫通ダメージタイプタグを削除し、ダメージ貫通を無効化する。{@link #MITIGATION_PIERCING} より権限が低い。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.common.enchantment.handler.ReactiveArmorHandler}
     * <p>
     * Mixin : {@link net.scratch221171.astralenchant.common.mixin.DamageSourceMixin}
     */
    public static final ResourceKey<Enchantment> REACTIVE_ARMOR = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "reactive_armor"));

    /**
     * 敵を倒した際に稀にエンチャントの本をドロップするようになる。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.common.datagen.AELootModifierProvider}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> MYSTIC_REMNANTS = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "mystic_remnants"));

    /**
     * ツールチップが呪われた文字に置き換わる。
     * <p>
     * Handler : {@link net.scratch221171.astralenchant.common.enchantment.handler.CurseOfIgnoranceHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> CURSE_OF_IGNORANCE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "curse_of_ignorance"));

    static {
        ENCHANTMENTS.add(MITIGATION_PIERCING);
        ENCHANTMENTS.add(LAST_STAND);
        ENCHANTMENTS.add(ITEM_PROTECTION);
        ENCHANTMENTS.add(ESSENCE_OF_ENCHANTMENT);
        ENCHANTMENTS.add(COOLDOWN_REDUCTION);
        ENCHANTMENTS.add(FEATHER_TOUCH);
        ENCHANTMENTS.add(ADVENTURERS_LORE);
        ENCHANTMENTS.add(COMPATIBILITY);
        ENCHANTMENTS.add(ENDLESS_APPETITE);
        ENCHANTMENTS.add(MOMENTUM);
        ENCHANTMENTS.add(INSTANT_TELEPORT);
        ENCHANTMENTS.add(OVERLOAD);
        ENCHANTMENTS.add(SLOT_EXPANSION);
        ENCHANTMENTS.add(REACTIVE_ARMOR);
        ENCHANTMENTS.add(MYSTIC_REMNANTS);
        ENCHANTMENTS.add(CURSE_OF_IGNORANCE);
    }
}
