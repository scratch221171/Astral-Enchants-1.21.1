package net.scratch221171.astralenchant.common.event;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.scratch221171.astralenchant.common.AstralEnchant;

@EventBusSubscriber(modid = AstralEnchant.MOD_ID)
public class AERecipeEventHandler {

    @SubscribeEvent
    private static void onExplode(ExplosionEvent.Detonate event) {
//        Level level = event.getLevel();
//        if (level.isClientSide) return;
//        Iterator<Entity> it = event.getAffectedEntities().iterator();
//        while (it.hasNext()) {
//            Entity e = it.next();
//            if (e instanceof ItemEntity item && item.isAlive()) {
//                if (processExplosionRecipe(item, event)) {
//                    it.remove();
//                    item.discard();
//                }
//            }
//        }
    }

    static boolean processExplosionRecipe(ItemEntity itemEntity, ExplosionEvent.Detonate event) {
        if (itemEntity.getItem().is(ItemTags.COALS)) {
            Level level = event.getLevel();
            level.addFreshEntity(new ItemEntity(
                    level, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(),
                    new ItemStack(Items.DIAMOND, itemEntity.getItem().getCount())
            ));
            return true;
        }
        return false;
    }
}
