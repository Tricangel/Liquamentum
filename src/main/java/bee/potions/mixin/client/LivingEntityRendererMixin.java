package bee.potions.mixin.client;

import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {

	@Inject(at = @At("HEAD"), method = "getRenderType", cancellable = true)
	private void wawa(LivingEntityRenderState livingEntityRenderState, boolean bl, boolean bl2, boolean bl3, CallbackInfoReturnable<RenderType> cir) {

		if (livingEntityRenderState.isInvisible) {
			cir.setReturnValue(RenderTypes.waterMask());

		}

	}

	@Inject(at = @At("HEAD"), method = "isBodyVisible", cancellable = true)
	private void waw(LivingEntityRenderState livingEntityRenderState, CallbackInfoReturnable<Boolean> cir) {
			cir.setReturnValue(true);
	}





}