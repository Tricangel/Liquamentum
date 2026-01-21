package bee.potions.mixin.client;

import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin {

	@Inject(at = @At("HEAD"), method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)Lnet/minecraft/resources/Identifier;", cancellable = true)
	private void wawa(AvatarRenderState avatarRenderState, CallbackInfoReturnable<Identifier> cir) {
		if (Minecraft.getInstance().player != null) {
			if (Liquamentum.isClientFrozen(Minecraft.getInstance()) && Minecraft.getInstance().player.hasEffect(LiquamentumEffects.PETRIFIED)) {

				Identifier id = avatarRenderState.skin.body().texturePath();

				cir.setReturnValue(id);

			}
		}

	}


}