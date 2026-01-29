package net.scratch221171.astralenchant.common.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.scratch221171.astralenchant.common.AstralEnchant;
import net.scratch221171.astralenchant.common.registries.AEAttributes;

public class AELanguageProviderJAJP extends LanguageProvider {
    public AELanguageProviderJAJP(PackOutput output) {
        super(output, AstralEnchant.MOD_ID, "ja_jp");
    }

    @Override
    protected void addTranslations() {
        // エンチャント
        add(AEEnchantments.MITIGATION_PIERCING.location().toLanguageKey("enchantment"), "軽減貫通");
        add(AEEnchantments.MITIGATION_PIERCING.location().toLanguageKey("enchantment", "desc"), "このアイテムでの攻撃は様々なダメージ軽減を貫通します。");
        add(AEEnchantments.LAST_STAND.location().toLanguageKey("enchantment"), "ラストスタンド");
        add(AEEnchantments.LAST_STAND.location().toLanguageKey("enchantment", "desc"), "経験値を消費して死を回避します。");
        add(AEEnchantments.ITEM_PROTECTION.location().toLanguageKey("enchantment"), "アイテム保護");
        add(AEEnchantments.ITEM_PROTECTION.location().toLanguageKey("enchantment", "desc"), "ドロップしたアイテムがいかなる手段でも消えなくなります。代償としてエンチャントが変更できなくなります。");
        add(AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().toLanguageKey("enchantment"), "エンチャントの極意");
        add(AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().toLanguageKey("enchantment", "desc"), "このエンチャント以外のエンチャントのレベルの合計に応じて、アイテムの全てのステータスが上昇します。");
        add(AEEnchantments.COOLDOWN_REDUCTION.location().toLanguageKey("enchantment"), "クールダウン削減");
        add(AEEnchantments.COOLDOWN_REDUCTION.location().toLanguageKey("enchantment", "desc"), "盾やエンダーパールなどすべてのアイテムのクールダウンを短縮します。");
        add(AEEnchantments.FEATHER_TOUCH.location().toLanguageKey("enchantment"), "フェザータッチ");
        add(AEEnchantments.FEATHER_TOUCH.location().toLanguageKey("enchantment", "desc"), "破壊可能なブロックが全て回収できるようになります。コンポーネントも保存されます。");
        add(AEEnchantments.ADVENTURERS_LORE.location().toLanguageKey("enchantment"), "冒険者の心得");
        add(AEEnchantments.ADVENTURERS_LORE.location().toLanguageKey("enchantment", "desc"), "完了した実績の数に応じて運を上げます");
        add(AEEnchantments.COMPATIBILITY.location().toLanguageKey("enchantment"), "適合");
        add(AEEnchantments.COMPATIBILITY.location().toLanguageKey("enchantment", "desc"), "バンドルの代わりに、競合などを無視して内部アイテムにエンチャントが付与されるようになります。");
        add(AEEnchantments.ENDLESS_APPETITE.location().toLanguageKey("enchantment"), "果てしない食欲");
        add(AEEnchantments.ENDLESS_APPETITE.location().toLanguageKey("enchantment", "desc"), "自然再生を促進し、上限を超過した満腹度と隠し満腹度の分だけ直接回復します。");
        add(AEEnchantments.MOMENTUM.location().toLanguageKey("enchantment"), "速度維持");
        add(AEEnchantments.MOMENTUM.location().toLanguageKey("enchantment", "desc"), "アイテム使用やブロックによる速度低下を無効化します。");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment"), "即時テレポート");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "desc"), "エンダーパール使用時、視線の先にテレポートします。スニークしている場合はブロックの向こう側にテレポートします。");
        add(AEEnchantments.INSTANT_TELEPORT.location().toLanguageKey("enchantment", "message.fail"), "距離内に有効なブロックが存在しません！");

        // attribute
        add(AEAttributes.COOLDOWN_REDUCTION.unwrapKey().orElseThrow().location().toLanguageKey("attribute"), "クールダウン短縮率");

        // 設定画面
        add(AstralEnchant.MOD_ID + ".configuration.enchantment_toggling", "エンチャントの切り替え");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.MITIGATION_PIERCING.location().getPath(), "軽減貫通");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.LAST_STAND.location().getPath(), "ラストスタンド");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.ITEM_PROTECTION.location().getPath(), "アイテム保護");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.ESSENCE_OF_ENCHANTMENT.location().getPath(), "エンチャントの極意");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.COOLDOWN_REDUCTION.location().getPath(), "クールダウン削減");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.FEATHER_TOUCH.location().getPath(), "フェザータッチ");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.ADVENTURERS_LORE.location().getPath(), "冒険者の心得");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.COMPATIBILITY.location().getPath(), "適合");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.ENDLESS_APPETITE.location().getPath(), "果てしない食欲");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.MOMENTUM.location().getPath(), "速度維持");
        add(AstralEnchant.MOD_ID + ".configuration." + AEEnchantments.INSTANT_TELEPORT.location().getPath(), "即時テレポート");
    }
}