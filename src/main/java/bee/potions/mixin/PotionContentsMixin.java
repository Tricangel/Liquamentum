package bee.potions.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PotionContents.class)
public abstract class PotionContentsMixin {
    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectInstance;isVisible()Z"), method = "getColorOptional")
    private static boolean isInWaterOrRain(boolean original) {
        return true;
    }
}
