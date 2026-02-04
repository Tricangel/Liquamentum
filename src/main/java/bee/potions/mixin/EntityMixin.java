package bee.potions.mixin;

import bee.potions.registry.LiquamentumEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    private int remainingFireTicks;

    @Inject(at = @At("HEAD"), method = "isCurrentlyGlowing", cancellable = true)
    private void glow(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.hasEffect(LiquamentumEffects.SHINY)) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "fireImmune", cancellable = true)
    private void Liquamentum$fired(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof LivingEntity entity) {
            if (entity.hasEffect(LiquamentumEffects.FIREPROOF)) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "setRemainingFireTicks", cancellable = true)
    private void Liquamentum$extraFired(int i, CallbackInfo ci) {
        if ((Object) this instanceof LivingEntity entity) {
            if (entity.hasEffect(LiquamentumEffects.AFTERBURN)) {
                this.remainingFireTicks = i * 2;
                ci.cancel();
            }
        }
    }



}
