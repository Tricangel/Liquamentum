package bee.potions.mixin;

import bee.potions.Liquamentum;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.LookControl;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LookControl.class)
public abstract class LookControlMixin {

    @Shadow
    @Final
    protected Mob mob;

    @Inject(at = @At("HEAD"), method = "setLookAt(DDDFF)V", cancellable = true)
    private void hideAllEffectParticles(double d, double e, double f, float g, float h, CallbackInfo ci) {
        if (Liquamentum.isFrozen(mob)) {
            ci.cancel();
        }
    }
}