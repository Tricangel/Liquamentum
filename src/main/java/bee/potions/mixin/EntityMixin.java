package bee.potions.mixin;

import bee.potions.registry.LiquamentumEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(at = @At("HEAD"), method = "isOnFire", cancellable = true)
    private void rotateCorrectly(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            if (livingEntity.hasEffect(LiquamentumEffects.CONSTANT_FIRE) && !livingEntity.isSpectator()) {
                cir.setReturnValue(true);
            }
        }
    }
}
