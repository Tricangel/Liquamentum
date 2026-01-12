package bee.potions.mixin;

import bee.potions.Liquamentum;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public abstract class MobMixin {

    @Inject(at = @At("HEAD"), method = "setTarget", cancellable = true)
    private void cancelInteraction(CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (Liquamentum.isFrozen(livingEntity)) {
            ci.cancel();
        }
    }
}
