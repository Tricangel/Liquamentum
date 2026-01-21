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

	// combined "cancelForward", "cancelBackward", "cancelLeft", "cancelRight", cancelJump" and "cancelShift" to "cancelMovement"
	@Inject(at = @At("HEAD"), method = {"forward","backward","left","right","jump","shift"}, cancellable = true)
	private void cancelMovement(CallbackInfoReturnable<Boolean> cir) {
		if (Liquamentum.isClientFrozen(Minecraft.getInstance()))
			cir.setReturnValue(false);
	}
}