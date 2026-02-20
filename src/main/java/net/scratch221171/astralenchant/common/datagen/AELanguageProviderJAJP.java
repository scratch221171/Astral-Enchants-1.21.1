package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
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

        addEnchantWithDesc(AEEnchantments.MITIGATION_PIERCING, "軽減貫通", "攻撃が様々なダメージ軽減を貫通します。");
        addEnchantWithDesc(AEEnchantments.LAST_STAND, "ラストスタンド", "経験値を消費して死を回避します。");
        addEnchantWithDesc(AEEnchantments.ITEM_PROTECTION, "アイテム保護", "ドロップしたアイテムがいかなる手段でも消えなくなります。代償としてエンチャントが変更できなくなります。");
        addEnchantWithDesc(AEEnchantments.ESSENCE_OF_ENCHANTMENT, "エンチャントの極意", "このエンチャント以外のエンチャントのレベルの合計に応じて、アイテムの全ステータスに補正がかかります");
        addEnchantWithDesc(AEEnchantments.COOLDOWN_REDUCTION, "クールダウン削減", "盾やエンダーパールを含む、すべてのアイテムの使用クールダウンを短縮します。");
        addEnchantWithDesc(AEEnchantments.FEATHER_TOUCH, "フェザータッチ", "破壊可能なブロックが全てアイテムとして回収できます。スニークした場合はブロックステート及びコンポーネントが保存されます。");
        addEnchantWithDesc(AEEnchantments.ADVENTURERS_LORE, "冒険者の心得", "達成した進捗の数に応じて運が良くなり、モブ・ブロックからの経験値の量が増加します。");
        addEnchantWithDesc(AEEnchantments.COMPATIBILITY, "適合", "このエンチャントが付与されたバンドルに対し、競合などを無視して内部アイテムにエンチャントを付与できるようになります。");
        addEnchantWithDesc(AEEnchantments.ENDLESS_APPETITE, "果てしない食欲", "自然再生を促進し、上限を超過した満腹度と隠し満腹度の量に応じて直接回復します。");
        addEnchantWithDesc(AEEnchantments.MOMENTUM, "速度維持", "アイテム使用やブロックによる速度低下を無効化します。");
        addEnchantWithDesc(AEEnchantments.INSTANT_TELEPORT, "即時テレポート", "エンダーパール使用時、視線の先にテレポートします。スニークしている場合はブロックの貫通し、その向こう側までテレポートします。");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "message.fail"), "距離内に有効なブロックが存在しません！");
        addEnchantWithDesc(AEEnchantments.OVERLOAD, "オーバーロード", "アイテムにオーバーロードが付与され、既存のエンチャントのレベルがオーバーロードの値だけ上昇します。");
        add(AEEnchantments.OVERLOAD.location().toLanguageKey("enchantment", "tooltip.text"), "オーバーロード：%s レベル");
        addEnchantWithDesc(AEEnchantments.SLOT_EXPANSION, "スロット拡張", "アイテムが現在装着されているアクセサリースロットの数を、エンチャントのレベル分だけ増加させます。");
        addEnchantWithDesc(AEEnchantments.REACTIVE_ARMOR, "反応装甲", "一部のダメージタイプのアーマーおよびエンチャントを貫通する効果を無効化します。");
        addEnchantWithDesc(AEEnchantments.MYSTIC_REMNANTS, "神秘の残滓", "倒されたmobが稀にエンチャントの本をドロップするようになります。");
        addEnchantWithDesc(AEEnchantments.CURSE_OF_IGNORANCE, "無知の呪い", "ツールチップを呪われた文字に置き換えます。");

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
    }

    private void addEnchantWithDesc(ResourceKey<Enchantment> key, String name, String desc) {
        add(key.location().toLanguageKey("enchantment"), name);
        add(key.location().toLanguageKey("enchantment", "desc"), desc);
        add(AstralEnchant.MOD_ID + ".configuration." + Config.TOGGLING_CONFIG_DICT.get(key.location().getPath()).getPath().getLast(), name);
    }
}