package bee.potions.mixin.client;

import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
	@Shadow public abstract Minecraft getMinecraft();

	@Inject(at = @At("HEAD"), method = "getDarkenWorldAmount", cancellable = true)
	private void lowerLight(float f, CallbackInfoReturnable<Float> cir) {
		if (Minecraft.getInstance().player != null) {
			if (Liquamentum.isClientFrozen(Minecraft.getInstance()) && Minecraft.getInstance().player.hasEffect(LiquamentumEffects.FATIGUED)) {
				cir.setReturnValue(1.5f);
			}
		}

	}


}