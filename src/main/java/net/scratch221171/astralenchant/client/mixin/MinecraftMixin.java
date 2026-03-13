package net.scratch221171.astralenchant.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.scratch221171.astralenchant.common.config.AEConfig;
import net.scratch221171.astralenchant.common.config.RuntimeConfigState;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    @Nullable
    public HitResult hitResult;

    @Shadow
    public LocalPlayer player;

    @Shadow
    public ClientLevel level;

    /**
     * {@link AEEnchantments#DISTORTION} が付いている場合は攻撃時hitResultを上書きする
     */
    @Inject(method = "startAttack()Z", at = @At("HEAD"))
    private void astralenchant$redirectMissAttack(CallbackInfoReturnable<Boolean> cir) {

        // entityにヒットしたらリダイレクトしない
        if (this.hitResult == null
            || this.hitResult.getType() == HitResult.Type.ENTITY) return;

        Holder<Enchantment> enchantment = AEUtils.getEnchantmentHolder(AEEnchantments.DISTORTION, level);
        int level = EnchantmentHelper.getEnchantmentLevel(enchantment, player);
        if (level <= 0) return;

        double radAnglePerLevel = RuntimeConfigState.get(AEConfig.DISTORTION_ANGLE_PER_LEVEL) * Math.PI / 180;
        Entity target = astralenchant$findTarget(level * radAnglePerLevel);
        if (target == null) return;

        this.hitResult = new EntityHitResult(target);
    }

    @Unique
    private Entity astralenchant$findTarget(double apexAngle) {

        AttributeInstance ins =
                player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE);
        if (ins == null) return null;
        double range = ins.getValue();

        AABB searchBox = player.getBoundingBox().inflate(range);

        Vec3 eye = player.getEyePosition();
        Vec3 look = player.getLookAngle().normalize();

        double bestCos = Math.cos(Math.min(apexAngle, Math.PI));
        Entity best = null;

        for (Entity e : level.getEntities(player, searchBox,
                entity -> entity instanceof LivingEntity le
                        && le.isAlive()
                        && le.isPickable()
                        && le.isAttackable()
                        && player.canAttack(le))) {

            Vec3 closestPoint = astralenchant$closestPointOnBoxToRay(eye, look, e.getBoundingBox());

            Vec3 dir = closestPoint.subtract(eye).normalize();
            double cos = dir.dot(look);

            if (cos >= bestCos) {
                bestCos = cos;
                best = e;
            }
        }

        return best;
    }

    @Unique
    private Vec3 astralenchant$closestPointOnBoxToRay(Vec3 rayOrigin, Vec3 rayDir, AABB box) {

        Vec3 toCenter = box.getCenter().subtract(rayOrigin);
        double t = Math.max(0, toCenter.dot(rayDir));

        Vec3 pointOnRay = rayOrigin.add(rayDir.scale(t));

        double x = Math.clamp(pointOnRay.x, box.minX, box.maxX);
        double y = Math.clamp(pointOnRay.y, box.minY, box.maxY);
        double z = Math.clamp(pointOnRay.z, box.minZ, box.maxZ);

        return new Vec3(x, y, z);
    }
}
