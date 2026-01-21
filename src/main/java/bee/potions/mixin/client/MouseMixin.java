package bee.potions.mixin.client;

import bee.potions.Liquamentum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.input.MouseButtonInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MouseHandler.class)
public abstract class MouseMixin {

	// combined "cancelLeftClick", "cancelRightClick" and "cancelMiddleMouse" to "cancelMouseInput"
	@Inject(at = @At("HEAD"), method = {"isLeftPressed","isRightPressed","isMiddlePressed"}, cancellable = true)
	private void cancelMouseInput(CallbackInfoReturnable<Boolean> cir) {
		if (Liquamentum.isClientFrozen(Minecraft.getInstance())) {
			if (Minecraft.getInstance().screen != null) {
				if (!Minecraft.getInstance().screen.isInGameUi()) cir.setReturnValue(false);
			} else cir.setReturnValue(false);
		}
	}

	@Inject(at = @At("HEAD"), method = "onButton", cancellable = true)
	private void cancelAll(long l, MouseButtonInfo mouseButtonInfo, int i, CallbackInfo ci) {
		if (Liquamentum.isClientFrozen(Minecraft.getInstance())) {
			if (Minecraft.getInstance().screen != null) {
				if (!Minecraft.getInstance().screen.isInGameUi()) ci.cancel();
			} else ci.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "onMove", cancellable = true)
	private void stopMovement(long l, double d, double e, CallbackInfo ci) {
		if (Liquamentum.isClientFrozen(Minecraft.getInstance())) {
			if (Minecraft.getInstance().screen != null) {
				if (!Minecraft.getInstance().screen.isInGameUi()) ci.cancel();
			} else ci.cancel();
		}
	}


}