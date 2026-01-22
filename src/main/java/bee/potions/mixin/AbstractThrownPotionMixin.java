package bee.potions.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.AbstractThrownPotion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractThrownPotion.class)
public abstract class AbstractThrownPotionMixin {

    @Inject(at = @At("HEAD"), method = { "onHit"}, cancellable = true)
    private void hideAllEffectParticles(HitResult hitResult, CallbackInfo ci) {
        HitResult.Type type = hitResult.getType();
        if (type == HitResult.Type.ENTITY) {
            Entity entity = ((EntityHitResult) hitResult).getEntity();
            Level level = entity.level();
            if (entity instanceof LivingEntity livingEntity && level instanceof ServerLevel) {
                livingEntity.hurtServer((ServerLevel) level, livingEntity.damageSources().thrown(livingEntity, livingEntity), 2);
            }

        }
    }
}