package bee.potions.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AreaEffectCloud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AreaEffectCloud.class)
public abstract class Lingering {

    @Inject(at = @At("HEAD"), method = { "serverTick"}, cancellable = true)
    private void hideAllEffectParticles(ServerLevel serverLevel, CallbackInfo ci) {

    }
}