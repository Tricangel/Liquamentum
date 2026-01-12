package bee.potions.mixin;

import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

	@Inject(at = @At("HEAD"), method = "tick", cancellable = true)
	private void rotateCorrectly(CallbackInfo ci) {
		Player player = (Player) (Object) this;

		if (player.hasEffect(LiquamentumEffects.GILLS)) {
			if (!player.isInWater()) {
				player.setAirSupply(player.getAirSupply() - 1);
			}
		}

	}

	@Inject(at = @At("HEAD"), method = "canEat", cancellable = true)
	private void stopEating(boolean bl, CallbackInfoReturnable<Boolean> cir) {
		Player player = (Player) (Object) this;
		if (player.hasEffect(LiquamentumEffects.INDIGESTION) || Liquamentum.isFrozen(player)) {
			cir.setReturnValue(false);
		}
	}

	@Inject(at = @At("HEAD"), method = "canSprint", cancellable = true)
	private void noSprintUnderwater(CallbackInfoReturnable<Boolean> cir) {
		Player player = (Player) (Object) this;
		if (player.hasEffect(LiquamentumEffects.HYDROPHOBIA) || player.hasEffect(LiquamentumEffects.ROCK)) {
			if (player.isUnderWater()) {
				cir.setReturnValue(false);
			}
		}
	}

	@Inject(at = @At("HEAD"), method = "isSwimming", cancellable = true)
	private void noSwimming(CallbackInfoReturnable<Boolean> cir) {
		Player player = (Player) (Object) this;
		if (player.hasEffect(LiquamentumEffects.HYDROPHOBIA) || player.hasEffect(LiquamentumEffects.ROCK)) {
			cir.setReturnValue(false);
		}
	}

	@Inject(at = @At("HEAD"), method = "isPushedByFluid", cancellable = true)
	private void noHearDamage(CallbackInfoReturnable<Boolean> cir) {
		Player player = (Player) (Object) this;
		if (player.hasEffect(LiquamentumEffects.ROCK)) {
			cir.setReturnValue(false);
		}
	}

	@ModifyVariable(at = @At(value = "HEAD"), method = "giveExperiencePoints", argsOnly = true)
	private int lowerOverpowered(int value) {
		Player player = (Player) (Object) this;
		if (player.hasEffect(LiquamentumEffects.EXPERIENCED)) return value * 3;
		return value;
	}




}