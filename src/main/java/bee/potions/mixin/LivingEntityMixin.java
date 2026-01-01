package bee.potions.mixin;

import bee.potions.registry.LiquamentumEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Shadow public abstract void setSprinting(boolean bl);

	@Inject(at = @At("TAIL"), method = "hurtServer", cancellable = true)
	private void ifHurtRemoveSleepy(ServerLevel serverLevel, DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (entity.hasEffect(LiquamentumEffects.SLEEPY)) {
			entity.removeEffect(LiquamentumEffects.SLEEPY);
			cir.setReturnValue(false);
		}

		if (entity.hasEffect(LiquamentumEffects.IMPLOSION)) {
			if (damageSource.is(DamageTypes.EXPLOSION) || damageSource.is(DamageTypes.PLAYER_EXPLOSION)) {
				amount *= 4;
			}
		}

	}

	@Inject(at = @At("TAIL"), method = "tick", cancellable = true)
	private void ifHurtRemoveSleepy(CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (entity.hasEffect(LiquamentumEffects.NOSWIM)) {
			if (entity.isUnderWater()) {
				setSprinting(false);
			}
		}


	}

	@Inject(at = @At("HEAD"), method = "isAffectedByFluids", cancellable = true)
	private void sinkInWater(CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (entity.hasEffect(LiquamentumEffects.SINK)) {
			cir.setReturnValue(false);
		}
	}

	@Inject(at = @At("HEAD"), method = "canStandOnFluid", cancellable = true)
	private void waterWalking(FluidState fluidState, CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (entity.hasEffect(LiquamentumEffects.LAVA_WALKING)) {
			if (fluidState.is(Fluids.LAVA)) {
				cir.setReturnValue(true);
			}
		}

		if (entity.hasEffect(LiquamentumEffects.WATER_WALKING)) {
			if (fluidState.is(Fluids.WATER)) {
				cir.setReturnValue(true);
			}
		}
	}


}