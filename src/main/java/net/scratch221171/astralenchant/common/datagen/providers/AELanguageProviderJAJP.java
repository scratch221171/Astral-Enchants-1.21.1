package net.scratch221171.astralenchant.common.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEAttributes;
import net.scratch221171.astralenchant.common.registries.AEBlocks;
import net.scratch221171.astralenchant.common.registries.AEItems;

public class AELanguageProviderJAJP extends LanguageProvider {
    public AELanguageProviderJAJP(PackOutput output) {
        super(output, AstralEnchant.MOD_ID, "ja_jp");
    }

    @Override
    protected void addTranslations() {
        // エンチャント
        add(AstralEnchant.MOD_ID + ".enchantment.disabled", "[無効化済み]");

        addEnchantWithDesc(AEEnchantments.MITIGATION_PIERCING, "軽減貫通", "攻撃が様々なダメージ軽減を貫通します。");
        addEnchantWithDesc(AEEnchantments.LAST_STAND, "ラストスタンド", "経験値を消費して死を回避します。");
        addEnchantWithDesc(AEEnchantments.ITEM_PROTECTION, "アイテム保護", "L2HostilityのDispell特性によるエンチャント無効化とRagnarok特性による封印を無効化します。");
        addEnchantWithDesc(AEEnchantments.ESSENCE_OF_ENCHANTMENT, "エンチャントの極意", "このエンチャント以外のエンチャントのレベルの合計に応じて、アイテムの全ステータスに補正がかかります");
        addEnchantWithDesc(AEEnchantments.COOLDOWN_REDUCTION, "クールダウン削減", "すべてのアイテムの使用クールダウンを短縮します。");
        addEnchantWithDesc(AEEnchantments.FEATHER_TOUCH, "フェザータッチ", "破壊可能なブロックが全てアイテムとして回収できます。スニーク時、ブロックステート及びコンポーネントが保存されます。");
        addEnchantWithDesc(AEEnchantments.ADVENTURERS_LORE, "冒険者の心得", "達成した進捗の数に応じて運が良くなり、モブ・ブロックからの経験値の量が増加します。");
        addEnchantWithDesc(AEEnchantments.COMPATIBILITY, "適合", "このエンチャントが付与されたバンドルに対し、競合などを無視して内部アイテムにエンチャントを付与できるようになります。");
        addEnchantWithDesc(AEEnchantments.ENDLESS_APPETITE, "果てしない食欲", "自然再生を促進し、上限を超過した満腹度と隠し満腹度の量に応じて直接回復します。");
        addEnchantWithDesc(AEEnchantments.MOMENTUM, "速度維持", "アイテム使用やブロックによる速度低下を無効化します。");
        addEnchantWithDesc(AEEnchantments.INSTANT_TELEPORT, "即時テレポート", "エンダーパール使用時、視線の先にテレポートします。スニーク時、ブロックを貫通しその向こう側までテレポートします。");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "message.fail"), "距離内に有効なブロックが存在しません！");
        addEnchantWithDesc(AEEnchantments.OVERLOAD, "オーバーロード", "アイテムにオーバーロードが付与され、既存のエンチャントのレベルがオーバーロードの値だけ上昇します。");
        add(AEEnchantments.OVERLOAD.location().toLanguageKey("enchantment", "tooltip.text"), "オーバーロード：%s レベル");
        addEnchantWithDesc(AEEnchantments.SLOT_EXPANSION, "スロット拡張", "アイテムが現在装着されているアクセサリースロットの数を、エンチャントのレベル分だけ増加させます。");
        addEnchantWithDesc(AEEnchantments.REACTIVE_ARMOR, "反応装甲", "一部のダメージタイプのアーマーおよびエンチャントを貫通する効果を無効化します。");
        addEnchantWithDesc(AEEnchantments.MYSTIC_REMNANTS, "神秘の残滓", "倒されたmobが稀にエンチャントの本をドロップするようになります。");
        addEnchantWithDesc(AEEnchantments.CURSE_OF_IGNORANCE, "無知の呪い", "ツールチップを呪われた文字に置き換えます。");
        addEnchantWithDesc(AEEnchantments.CURSE_OF_ENCHANTMENT, "エンチャントの呪い", "アイテムのエンチャントを変更できなくなります。");
        addEnchantWithDesc(AEEnchantments.DISTORTION, "歪曲", "攻撃が外れた場合、近くの敵へ判定が歪められます。");

        // attribute
        add(AEAttributes.COOLDOWN_DURATION.unwrapKey().orElseThrow().location().toLanguageKey("attribute"), "クールダウン時間");
        add(AEAttributes.COOLDOWN_DURATION.unwrapKey().orElseThrow().location().toLanguageKey("attribute", "desc"), "アイテム使用のクールダウン時間の倍率");

        // アイテム
        add(AEItems.ENCHANTMENT_SHARD.getKey().location().toLanguageKey("item"), "エンチャントの欠片");
        add(AEItems.ARCANE_QUARTZ.getKey().location().toLanguageKey("item"), "アーケインクォーツ");
        add(AEItems.ARCANIUM_INGOT.getKey().location().toLanguageKey("item"), "アルカニウムインゴット");
        add(AEItems.BUDDING_ARCANIUM_INGOT.getKey().location().toLanguageKey("item"), "芽生えたアルカニウムインゴット");

        // ブロック
        add(AEBlocks.ARCANIUM_BLOCK.getKey().location().toLanguageKey("block"), "アルカニウムブロック");

        // 設定画面
        add(AstralEnchant.MOD_ID + ".configuration.title", "設定");
        add(AstralEnchant.MOD_ID + ".configuration.section.astralenchant.server.toml.title", "Astral Enchant サーバー設定");

        add(AstralEnchant.MOD_ID + ".configuration.settings", "エンチャントの設定");
        addConfigWithDesc(AEConfig.LAST_STAND_REQUIRED_BASE_EXPERIENCE, "ラストスタンドの必要経験値ポイント（基本値）", "レベル N のラストスタンドの消費経験値は「基本値 ÷ N」です。デフォルトでは約 34 レベルです。");
        addConfigWithDesc(AEConfig.LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG, "ラストスタンドが無敵貫通を無視", "オフの場合、無敵貫通タグ（BYPASSES_INVULNERABILITY）を持つダメージ（/killや奈落など）ではラストスタンドは発動しません。");
        addConfigWithDesc(AEConfig.ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION, "オーバーロードレベルをエンチャントの極意の総レベル計算に含める", "この設定に関係なく、オーバーロードレベルはエンチャントの極意自身のレベルには適用されます。");
        addConfigWithDesc(AEConfig.ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER, "エンチャントの極意のレベル倍率（％）", "エンチャントの合計レベルが 1 増えるごとに、モディファイア倍率が何％増加するかを指定します。");
        addConfigWithDesc(AEConfig.INSTANT_TELEPORT_DISTANCE_INCREASE_PER_LEVEL, "即時テレポートのレベル毎の距離増加", "レベルが 1 上がるごとに、テレポート可能距離の上限がどれほど増加するかを指定します。");
        addConfigWithDesc(AEConfig.INSTANT_TELEPORT_MAX_DISTANCE, "即時テレポートの最大距離", "このエンチャントによるテレポートの距離の上限を指定します。");
        addConfigWithDesc(AEConfig.MITIGATION_PIERCING_ADDED_DAMAGE_TYPE_TAGS, "軽減貫通で付与されるダメージタイプタグ", "軽減貫通によって追加されるダメージタイプタグを設定します。反応装甲より優先されます。");
        addConfigWithDesc(AEConfig.REACTIVE_ARMOR_DISABLED_DAMAGE_TYPE_TAGS, "反応装甲で無効化されるダメージタイプタグ", "反応装甲によって無効化されるダメージタイプタグを設定します。");
        addConfigWithDesc(AEConfig.DISTORTION_ANGLE_PER_LEVEL, "歪曲のレベル毎の許容角度増加", "レベルが 1 上がるごとに、エンティティへの向きと視線の角度差の許容量が何度増加するか指定します。");

        add(AstralEnchant.MOD_ID + ".configuration.misc", "その他の設定");
        addConfigWithDesc(AEConfig.ENABLE_VANILLA_ITEM_RECIPES, "バニラアイテムのレシピを有効化", "ゲーム進行に必要となる可能性のあるバニラアイテムのレシピを有効化します（バンドルなど）。");

        add(AstralEnchant.MOD_ID + ".configuration.enchantment_toggling", "エンチャントの切り替え");
    }

    private void addEnchantWithDesc(ResourceKey<Enchantment> key, String name, String desc) {
        add(key.location().toLanguageKey("enchantment"), name);
        add(key.location().toLanguageKey("enchantment", "desc"), desc);
        // configのidはエンチャントpath参照なのでここもpath参照でええやろ
        add(AstralEnchant.MOD_ID + ".configuration." + key.location().getPath(), name);
    }

    private void addConfigWithDesc(ModConfigSpec.ConfigValue<?> key, String name, String desc) {
        add(AstralEnchant.MOD_ID + ".configuration." + key.getPath().getLast(), name);
        add(AstralEnchant.MOD_ID + ".configuration." + key.getPath().getLast() + ".tooltip", desc);
    }
}