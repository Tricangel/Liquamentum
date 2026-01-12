package bee.potions.mixin;

import bee.potions.Liquamentum;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Input;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Input.class)
public abstract class InputMixin {
	@Inject(at = @At("HEAD"), method = "forward", cancellable = true)
	private void cancelForward(CallbackInfoReturnable<Boolean> cir) {
		if (Liquamentum.isFrozen(Minecraft.getInstance())) {
			cir.setReturnValue(false);
		}
	}
	@Inject(at = @At("HEAD"), method = "backward", cancellable = true)
	private void cancelBackward(CallbackInfoReturnable<Boolean> cir) {
		if (Liquamentum.isFrozen(Minecraft.getInstance())) {
			cir.setReturnValue(false);
		}
	}
	@Inject(at = @At("HEAD"), method = "left", cancellable = true)
	private void cancelLeft(CallbackInfoReturnable<Boolean> cir) {
		if (Liquamentum.isFrozen(Minecraft.getInstance())) {
			cir.setReturnValue(false);
		}
	}
	@Inject(at = @At("HEAD"), method = "right", cancellable = true)
	private void cancelRight(CallbackInfoReturnable<Boolean> cir) {
		if (Liquamentum.isFrozen(Minecraft.getInstance())) {
			cir.setReturnValue(false);
		}
	}
	@Inject(at = @At("HEAD"), method = "jump", cancellable = true)
	private void cancelJump(CallbackInfoReturnable<Boolean> cir) {
		if (Liquamentum.isFrozen(Minecraft.getInstance())) {
			cir.setReturnValue(false);
		}
	}
	@Inject(at = @At("HEAD"), method = "shift", cancellable = true)
	private void cancelCrouch(CallbackInfoReturnable<Boolean> cir) {
		if (Liquamentum.isFrozen(Minecraft.getInstance())) {
			cir.setReturnValue(false);
		}
	}

}