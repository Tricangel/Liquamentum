package bee.potions.mixin.client;

import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumEffects;
import com.mojang.blaze3d.shaders.ShaderType;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
	@Shadow public abstract Minecraft getMinecraft();

	@Inject(at = @At("HEAD"), method = "getDarkenWorldAmount", cancellable = true)
	private void lowerLight(float f, CallbackInfoReturnable<Float> cir) {
		LocalPlayer player = Minecraft.getInstance().player;
		assert player != null;
		if (player.hasEffect(LiquamentumEffects.LOWLIGHT)) {
			cir.setReturnValue(7f);
		}

		if (Liquamentum.isSleepy(Minecraft.getInstance())) {
			cir.setReturnValue(1.5f);

		}

	}

}