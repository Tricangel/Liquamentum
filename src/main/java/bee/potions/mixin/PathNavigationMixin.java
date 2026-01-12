package bee.potions.mixin;

import bee.potions.Liquamentum;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PathNavigation.class)
public abstract class PathNavigationMixin {

    @Shadow
    @Final
    protected Mob mob;

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    private void cancelInteraction(CallbackInfo ci) {
        if (Liquamentum.isFrozen(mob)) {
            ci.cancel();
        }
    }
}
