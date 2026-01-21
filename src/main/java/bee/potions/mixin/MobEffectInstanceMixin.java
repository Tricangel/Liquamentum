package bee.potions.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEffectInstance.class)
public abstract class MobEffectInstanceMixin {

    @Inject(at = @At("HEAD"), method = {"isVisible", "showIcon"}, cancellable = true)
    private void hideAllEffectParticles(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}