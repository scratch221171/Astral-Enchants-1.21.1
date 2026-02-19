package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.Config;
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

        add(AEEnchantments.MITIGATION_PIERCING.location().toLanguageKey("enchantment"), "軽減貫通");
        add(AEEnchantments.MITIGATION_PIERCING.location().toLanguageKey("enchantment", "desc"), "攻撃が様々なダメージ軽減を貫通します。");
        add(AEEnchantments.LAST_STAND.location().toLanguageKey("enchantment"), "ラストスタンド");
        add(AEEnchantments.LAST_STAND.location().toLanguageKey("enchantment", "desc"), "経験値を消費して死を回避します。");
        add(AEEnchantments.ITEM_PROTECTION.location().toLanguageKey("enchantment"), "アイテム保護");
        add(AEEnchantments.ITEM_PROTECTION.location().toLanguageKey("enchantment", "desc"), "ドロップしたアイテムがいかなる手段でも消えなくなります。代償としてエンチャントが変更できなくなります。");
        add(AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().toLanguageKey("enchantment"), "エンチャントの極意");
        add(AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().toLanguageKey("enchantment", "desc"), "このエンチャント以外のエンチャントのレベルの合計に応じて、アイテムの全ステータスに補正がかかります");
        add(AEEnchantments.COOLDOWN_REDUCTION.location().toLanguageKey("enchantment"), "クールダウン削減");
        add(AEEnchantments.COOLDOWN_REDUCTION.location().toLanguageKey("enchantment", "desc"), "盾やエンダーパールを含む、すべてのアイテムの使用クールダウンを短縮します。");
        add(AEEnchantments.FEATHER_TOUCH.location().toLanguageKey("enchantment"), "フェザータッチ");
        add(AEEnchantments.FEATHER_TOUCH.location().toLanguageKey("enchantment", "desc"), "破壊可能なブロックが全てアイテムとして回収できます。スニークした場合はブロックステート及びコンポーネントが保存されます。");
        add(AEEnchantments.ADVENTURERS_LORE.location().toLanguageKey("enchantment"), "冒険者の心得");
        add(AEEnchantments.ADVENTURERS_LORE.location().toLanguageKey("enchantment", "desc"), "達成した進捗の数に応じて運が良くなり、モブ・ブロックからの経験値の量が増加します。");
        add(AEEnchantments.COMPATIBILITY.location().toLanguageKey("enchantment"), "適合");
        add(AEEnchantments.COMPATIBILITY.location().toLanguageKey("enchantment", "desc"), "このエンチャントが付与されたバンドルに対し、競合などを無視して内部アイテムにエンチャントを付与できるようになります。");
        add(AEEnchantments.ENDLESS_APPETITE.location().toLanguageKey("enchantment"), "果てしない食欲");
        add(AEEnchantments.ENDLESS_APPETITE.location().toLanguageKey("enchantment", "desc"), "自然再生を促進し、上限を超過した満腹度と隠し満腹度の量に応じて直接回復します。");
        add(AEEnchantments.MOMENTUM.location().toLanguageKey("enchantment"), "速度維持");
        add(AEEnchantments.MOMENTUM.location().toLanguageKey("enchantment", "desc"), "アイテム使用やブロックによる速度低下を無効化します。");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment"), "即時テレポート");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "desc"), "エンダーパール使用時、視線の先にテレポートします。スニークしている場合はブロックの貫通し、その向こう側までテレポートします。");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "message.fail"), "距離内に有効なブロックが存在しません！");
        add(AEEnchantments.OVERLOAD.location().toLanguageKey("enchantment"), "オーバーロード");
        add(AEEnchantments.OVERLOAD.location().toLanguageKey("enchantment", "desc"), "アイテムにオーバーロードが付与され、既存のエンチャントのレベルがオーバーロードの値だけ上昇します。");
        add(AEEnchantments.OVERLOAD.location().toLanguageKey("enchantment", "tooltip.text"), "オーバーロード：%s レベル");
        add(AEEnchantments.SLOT_EXPANSION.location().toLanguageKey("enchantment"), "スロット拡張");
        add(AEEnchantments.SLOT_EXPANSION.location().toLanguageKey("enchantment", "desc"), "アイテムが現在装着されているアクセサリースロットの数を、エンチャントのレベル分だけ増加させます。");
        add(AEEnchantments.REACTIVE_ARMOR.location().toLanguageKey("enchantment"), "反応装甲");
        add(AEEnchantments.REACTIVE_ARMOR.location().toLanguageKey("enchantment", "desc"), "一部のダメージタイプのアーマーおよびエンチャントを貫通する効果を無効化します。");
        add(AEEnchantments.MYSTIC_REMNANTS.location().toLanguageKey("enchantment"), "神秘の残滓");
        add(AEEnchantments.MYSTIC_REMNANTS.location().toLanguageKey("enchantment", "desc"), "倒されたmobが稀にエンチャントの本をドロップするようになります。");
        add(AEEnchantments.CURSE_OF_IGNORANCE.location().toLanguageKey("enchantment"), "無知の呪い");
        add(AEEnchantments.CURSE_OF_IGNORANCE.location().toLanguageKey("enchantment", "desc"), "ツールチップを呪われた文字に置き換えます。");


        // attribute
        add(AEAttributes.COOLDOWN_REDUCTION.unwrapKey().orElseThrow().location().toLanguageKey("attribute"), "クールダウン短縮率");

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
        add(AstralEnchant.MOD_ID + ".configuration." + Config.LAST_STAND_REQUIRED_BASE_EXPERIENCE.getPath().getLast() , "ラストスタンドに必要な経験値ポイントの基本値");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.LAST_STAND_REQUIRED_BASE_EXPERIENCE.getPath().getLast() + ".tooltip", "レベルNのラストスタンドの消費量は（基本値/N）です。デフォルトではおおよそ34レベルです。");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG.getPath().getLast(), "ラストスタンドが無敵貫通を無視");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.LAST_STAND_IGNORE_BYPASSES_INVULNERABILITY_TAG.getPath().getLast() + ".tooltip", "この設定がオフのとき、ダメージソースに無敵貫通タグが付いている場合（/killや奈落など）は復活できません。");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION.getPath().getLast(), "オーバーロードレベルをエンチャントの極意の総レベル計算に適用");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ESSENCE_OF_ENCHANT_INCLUDE_OVERLOAD_IN_CALCULATION.getPath().getLast() + ".tooltip", "この設定に関係なく、エンチャントの極意自体のレベルには適用されます。");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER.getPath().getLast(), "エンチャントの極意のレベル倍率（％）");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ESSENCE_OF_ENCHANT_LEVEL_MULTIPLIER.getPath().getLast() + ".tooltip", "アイテムのエンチャントの合計レベルが1レベル増えるごとに、モディファイアの倍率が何％伸びるか指定します。");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ENABLE_VANILLA_ITEM_RECIPES.getPath().getLast(), "バニラアイテムのレシピを有効化");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ENABLE_VANILLA_ITEM_RECIPES.getPath().getLast() + ".tooltip", "バンドルなど、ゲーム進行上必要になるかもしれないバニラアイテムのレシピを有効化します。");



        add(AstralEnchant.MOD_ID + ".configuration.enchantment_toggling", "エンチャントの切り替え");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.MITIGATION_PIERCING.getPath().getLast(), "軽減貫通");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.LAST_STAND.getPath().getLast(), "ラストスタンド");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ITEM_PROTECTION.getPath().getLast(), "アイテム保護");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ESSENCE_OF_ENCHANTMENT.getPath().getLast(), "エンチャントの極意");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.COOLDOWN_REDUCTION.getPath().getLast(), "クールダウン削減");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.FEATHER_TOUCH.getPath().getLast(), "フェザータッチ");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ADVENTURERS_LORE.getPath().getLast(), "冒険者の心得");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.COMPATIBILITY.getPath().getLast(), "適合");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.ENDLESS_APPETITE.getPath().getLast(), "果てしない食欲");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.MOMENTUM.getPath().getLast(), "速度維持");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.INSTANT_TELEPORT.getPath().getLast(), "即時テレポート");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.OVERLOAD.getPath().getLast(), "オーバーロード");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.SLOT_EXPANSION.getPath().getLast(), "スロット拡張");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.REACTIVE_ARMOR.getPath().getLast(), "反応装甲");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.MYSTIC_REMNANTS.getPath().getLast(), "神秘の残滓");
        add(AstralEnchant.MOD_ID + ".configuration." + Config.CURSE_OF_IGNORANCE.getPath().getLast(), "無知の呪い");
    }
}