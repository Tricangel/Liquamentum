package bee.potions.mixin;

import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumEffects;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
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


	}

	@Inject(at = @At("HEAD"), method = "canEat", cancellable = true)
	private void stopEating(boolean bl, CallbackInfoReturnable<Boolean> cir) {
		Player player = (Player) (Object) this;
		if (player.hasEffect(LiquamentumEffects.NO_EATING)) {
			cir.setReturnValue(false);
		}
	}

	@Inject(at = @At("HEAD"), method = "animateHurt", cancellable = true)
	private void hideDamage(float f, CallbackInfo ci) {
		Player player = (Player) (Object) this;
		if (player.hasEffect(LiquamentumEffects.NUMBNESS)) {
			ci.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "getHurtSound", cancellable = true)
	private void hideDamage(DamageSource damageSource, CallbackInfoReturnable<SoundEvent> cir) {
		Player player = (Player) (Object) this;
		if (player.hasEffect(LiquamentumEffects.NUMBNESS)) {
			cir.setReturnValue(SoundEvents.EMPTY);
		}
	}

}