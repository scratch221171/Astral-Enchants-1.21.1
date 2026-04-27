package net.scratch221171.astralenchant.common.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.client.mixin.MinecraftMixin;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.enchantment.handler.*;
import net.scratch221171.astralenchant.common.mixin.compat.l2hostility.DispellTraitMixin;
import net.scratch221171.astralenchant.common.mixin.compat.l2hostility.RagnarokTraitMixin;
import net.scratch221171.astralenchant.common.mixin.minecraft.*;
import net.scratch221171.astralenchant.compat.accessories.EssenceOfEnchantmentAccessoriesCompatHandler;
import net.scratch221171.astralenchant.compat.accessories.SlotExpansionHandler;

public class AEEnchantments {

    /**
     * 与えた攻撃に様々なダメージタイプタグを付与し、ダメージ軽減を貫通する。
     * <p>
     * Handler : {@link MitigationPiercingHandler}
     * <p>
     * Mixin : {@link DamageSourceMixin}
     */
    public static final ResourceKey<Enchantment> MITIGATION_PIERCING = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "mitigation_piercing"));

    /**
     * 経験値を消費して死亡イベントをキャンセルする。
     * <p>
     * Handler : {@link LastStandHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> LAST_STAND = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "last_stand"));

    /**
     * L2のDispellによるエンチャント無効化及びRagnarokによる封印を防ぐ
     * <p>
     * Handler : none
     * <p>
     * Mixin : {@link DispellTraitMixin}, {@link RagnarokTraitMixin}
     */
    public static final ResourceKey<Enchantment> ITEM_PROTECTION = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "item_protection"));

    /**
     * アイテムの合計エンチャントレベル(自身を除く)に応じて全てのAttributeModifierを上昇させる。
     * <p>
     * Handler : {@link EssenceOfEnchantmentHandler}, {@link EssenceOfEnchantmentAccessoriesCompatHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> ESSENCE_OF_ENCHANTMENT = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "essence_of_enchantment"));

    /**
     * アイテム使用のクールダウンを短縮する。
     * <p>
     * Handler : {@link CooldownDurationAttributeHandler}
     * <p>
     * Mixin : {@link ItemCooldownsMixin}
     */
    public static final ResourceKey<Enchantment> COOLDOWN_REDUCTION = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "cooldown_reduction"));

    /**
     * ブロック破壊時に必ずドロップするようにし、スニーク時はBlockStateやBlockEntityのコンポーネントを保持したままアイテム化させる。
     * <p>
     * Handler : {@link FeatherTouchHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> FEATHER_TOUCH = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "feather_touch"));

    /**
     * 総実績数に応じて運とブロック由来の経験値を増加させる。
     * <p>
     * Handler : {@link AdventurersLoreHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> ADVENTURERS_LORE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "adventurers_lore"));

    /**
     * このエンチャントがついたバンドルに付けられたエンチャントが、競合などを無視して内部アイテムに付与される。
     * <p>
     * Handler : {@link CompatibilityHandler}
     * <p>
     * Mixin : {@link IItemExtensionMixin}
     */
    public static final ResourceKey<Enchantment> COMPATIBILITY = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "compatibility"));

    /**
     * 溢れた満腹度分だけ回復し、自然治癒を加速し、常時食事可能にする。
     * <p>
     * Handler : none
     * <p>
     * Mixin : {@link FoodDataMixin}, {@link PlayerMixin}
     */
    public static final ResourceKey<Enchantment> ENDLESS_APPETITE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "endless_appetite"));

    /**
     * 様々な移動速度低下効果を無効化する。
     * <p>
     * Handler : none
     * <p>
     * Mixin : {@link LocalPlayerMixin}, {@link PlayerMixin}, {@link EntityMixin}
     */
    public static final ResourceKey<Enchantment> MOMENTUM = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "momentum"));

    /**
     * エンダーパール使用時、瞬時に視線の先に真っ直ぐテレポートする。スニーク時はブロックを貫通する。
     * <p>
     * Handler : {@link InstantTeleportHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> INSTANT_TELEPORT = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "instant_teleport"));

    /**
     * {@link net.scratch221171.astralenchant.common.registries.AEDataComponents#OVERLOAD} の値だけ全てのエンチャントのレベルを上昇させる。
     * <p>
     * Handler : {@link OverloadHandler}
     * <p>
     * Mixin : {@link ItemStackMixin}, {@link IItemExtensionMixin}, {@link EnchantmentHelperMixin}
     */
    public static final ResourceKey<Enchantment> OVERLOAD = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "overload"));

    /**
     * Accessories連携：アイテムが装着されたスロットの数をエンチャントのレベルだけ増やす。
     * <p>
     * Handler : {@link SlotExpansionHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> SLOT_EXPANSION = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "slot_expansion"));

    /**
     * 与えられた攻撃から防具貫通ダメージタイプタグを削除し、ダメージ貫通を無効化する。{@link #MITIGATION_PIERCING} より権限が低い。
     * <p>
     * Handler : {@link ReactiveArmorHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> REACTIVE_ARMOR = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "reactive_armor"));

    /**
     * 敵を倒した際に稀にエンチャントの本をドロップするようになる。
     * <p>
     * Handler : AELootModifierProvider
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> MYSTIC_REMNANTS = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "mystic_remnants"));

    /**
     * ツールチップが呪われた文字に置き換わる。
     * <p>
     * Handler : {@link CurseOfIgnoranceHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> CURSE_OF_IGNORANCE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "curse_of_ignorance"));

    /**
     * そのアイテムのエンチャントが変更できなくなる。
     * <p>
     * Handler : {@link CurseOfEnchantmentHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> CURSE_OF_ENCHANTMENT = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "curse_of_enchantment"));

    /**
     * 攻撃時より大きい当たり判定を作り直し、照準があっていなくても攻撃が当たるようになる。
     * <p>
     * Handler : none
     * <p>
     * Mixin : {@link MinecraftMixin}
     */
    public static final ResourceKey<Enchantment> DISTORTION = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "distortion"));

    /**
     * アイテムに経験値を注ぎ込み、最後に雷にあたり事でツールが不可壊になる。雷のダメージがデフォ16倍。
     * <p>
     * Handler : {@link OverloadHandler}
     * <p>
     * Mixin : none
     */
    public static final ResourceKey<Enchantment> OVER_MENDING = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "over_mending"));
}
