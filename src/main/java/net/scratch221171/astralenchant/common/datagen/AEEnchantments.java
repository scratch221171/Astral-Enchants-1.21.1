package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import net.neoforged.neoforge.registries.holdersets.AnyHolderSet;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.registries.AEAttributes;

public class AEEnchantments {
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
     * Handler : {@link net.scratch221171.astralenchant.common.enchantment.handler.EssenceOfEnchantmentHandler}
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
     * Handler : none
     * <p>
     * Mixin : {@link net.scratch221171.astralenchant.common.mixin.EnderPearlItemMixin}
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
     * Handler : {@link net.scratch221171.astralenchant.compat.accessories.AccessoriesCompatHandler}
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

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var items = context.registryLookup(Registries.ITEM);

        HolderSet<Item> any = new AnyHolderSet<>(items.orElseThrow());
        HolderSet<Item> armor = items.get().getOrThrow(ItemTags.ARMOR_ENCHANTABLE);
        HolderSet<Item> head = items.get().getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE);
        HolderSet<Item> chest = items.get().getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE);
        HolderSet<Item> foot = items.get().getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE);
        HolderSet<Item> weapon = items.get().getOrThrow(ItemTags.WEAPON_ENCHANTABLE);
        HolderSet<Item> mining = items.get().getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE);
        HolderSet<Item> bundle = items.get().getOrThrow(AEItemTagsProvider.BUNDLE);

        register(context, MITIGATION_PIERCING, Enchantment.enchantment(Enchantment.definition(
                weapon,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                32,
                EquipmentSlotGroup.MAINHAND)));

        register(context, LAST_STAND, Enchantment.enchantment(Enchantment.definition(
                armor,
                1,
                3,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                32,
                EquipmentSlotGroup.ARMOR)));

        register(context, ITEM_PROTECTION, Enchantment.enchantment(Enchantment.definition(
                any,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.ANY)));

        register(context, ESSENCE_OF_ENCHANTMENT, Enchantment.enchantment(Enchantment.definition(
                any,
                1,
                5,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                32,
                EquipmentSlotGroup.ANY)));

        register(context, COOLDOWN_REDUCTION, Enchantment.enchantment(Enchantment.definition(
                chest,
                1,
                3,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.CHEST))
                .withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                        ResourceLocation.fromNamespaceAndPath(AstralEnchant.MOD_ID, "cr_bonus"),
                        AEAttributes.COOLDOWN_REDUCTION,
                        LevelBasedValue.perLevel(0.1F),
                        AttributeModifier.Operation.ADD_VALUE
                )));

        register(context, FEATHER_TOUCH, Enchantment.enchantment(Enchantment.definition(
                mining,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.MAINHAND)));

        register(context, ADVENTURERS_LORE, Enchantment.enchantment(Enchantment.definition(
                foot,
                1,
                3,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                8,
                EquipmentSlotGroup.FEET)));

        register(context, COMPATIBILITY, Enchantment.enchantment(Enchantment.definition(
                bundle,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                32,
                EquipmentSlotGroup.ANY)));

        register(context, ENDLESS_APPETITE, Enchantment.enchantment(Enchantment.definition(
                chest,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                32,
                EquipmentSlotGroup.CHEST)));

        register(context, MOMENTUM, Enchantment.enchantment(Enchantment.definition(
                chest,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.CHEST)));

        register(context, INSTANT_TELEPORT, Enchantment.enchantment(Enchantment.definition(
                head,
                1,
                4,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.HEAD)));
        
        register(context, OVERLOAD, Enchantment.enchantment(Enchantment.definition(
                any,
                1,
                5,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                32,
                EquipmentSlotGroup.ANY)));
        
//        register(context, SLOT_EXPANSION, Enchantment.enchantment(Enchantment.definition(
//                any,
//                1,
//                3,
//                Enchantment.dynamicCost(100,10),
//                Enchantment.dynamicCost(150,10),
//                16,
//                EquipmentSlotGroup.ANY)));
        
        register(context, REACTIVE_ARMOR,  Enchantment.enchantment(Enchantment.definition(
                chest,
                1,
                1,
                Enchantment.dynamicCost(100,10),
                Enchantment.dynamicCost(150,10),
                16,
                EquipmentSlotGroup.CHEST)));
    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}
