package bee.potions.mixin;

import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumEffects;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

	@Inject(at = @At("HEAD"), method = "tick", cancellable = true)
	private void rotateCorrectly(CallbackInfo ci) {
		Player player = (Player) (Object) this;
		if (Liquamentum.isSleepy(player)) {
			player.lookAt(EntityAnchorArgument.Anchor.FEET, new Vec3(player.position().x(), player.position().y() - 2, player.position().z()));
		}

		if (player.hasEffect(LiquamentumEffects.GILLS)) {
			if (!player.isInWater()) {
				player.setAirSupply(player.getAirSupply() - 1);
			}
		}

	}

	@Inject(at = @At("HEAD"), method = "canEat", cancellable = true)
	private void stopEating(boolean bl, CallbackInfoReturnable<Boolean> cir) {
		Player player = (Player) (Object) this;
		if (player.hasEffect(LiquamentumEffects.INDIGESTION)) {
			cir.setReturnValue(false);
		}
	}

	@Inject(at = @At("HEAD"), method = "isAffectedByFluids", cancellable = true)
	private void stopEating(CallbackInfoReturnable<Boolean> cir) {
		Player player = (Player) (Object) this;
		if (player.hasEffect(LiquamentumEffects.ROCK)) {
			//cir.setReturnValue(false);
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





}