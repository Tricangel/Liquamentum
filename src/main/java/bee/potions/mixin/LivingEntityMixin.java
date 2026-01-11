package bee.potions.mixin;

import bee.potions.registry.LiquamentumEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Shadow public abstract void setSprinting(boolean bl);

	@Shadow
	@Final
	public static float BASE_JUMP_POWER;

	@Inject(at = @At("HEAD"), method = "hurtServer", cancellable = true)
	private void hurtServer(ServerLevel serverLevel, DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;
		Entity attackerEntity = damageSource.getEntity();
		if (entity.hasEffect(LiquamentumEffects.FATIGUED)) {
			entity.removeEffect(LiquamentumEffects.FATIGUED);
			cir.setReturnValue(false);
		}

		if (entity.hasEffect(LiquamentumEffects.IMPLOSION)) {
			if (damageSource.is(DamageTypes.EXPLOSION) || damageSource.is(DamageTypes.PLAYER_EXPLOSION)) {
				amount *= 4;
			}
		}

		if (entity.hasEffect(LiquamentumEffects.ARSONIST)) {
			if (damageSource.is(DamageTypeTags.IS_FIRE)) {
				if (!entity.isInvulnerableTo(serverLevel, damageSource)) {
					if (damageSource.is(DamageTypes.IN_FIRE)) {
						amount /= 32;
					} else amount /= 4;
					entity.heal(amount);
					cir.setReturnValue(false);
				}
			}
		}


		if (entity.hasEffect(LiquamentumEffects.THORNS)) {
			if (damageSource.getEntity() != null) {
				LivingEntity attacker = (LivingEntity) damageSource.getEntity();
				attacker.hurtServer(serverLevel, serverLevel.damageSources().thorns(entity), (float) Math.clamp(1 * (.2 * amount), 0, 15));
			}
		}

		if (attackerEntity instanceof LivingEntity attacker) {
			if (attacker.hasEffect(LiquamentumEffects.RAGE)) {
				amount *= attacker.getMaxHealth() - attacker.getHealth() / 2;
			}
		}

	}

	@Inject(at = @At("TAIL"), method = "tick", cancellable = true)
	private void tick(CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (entity.hasEffect(LiquamentumEffects.HYDROPHOBIA)) {
			if (entity.isUnderWater()) {
				setSprinting(false);
			}
		}

	}


	@Inject(at = @At("HEAD"), method = "jumpInLiquid", cancellable = true)
	private void Liquamentum$(TagKey<Fluid> tagKey, CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (entity.hasEffect(LiquamentumEffects.ROCK)) {
			if (entity.onGround()) {
				float f = (float) (BASE_JUMP_POWER / 1.5);
				Vec3 vec3 = entity.getDeltaMovement();
				entity.setDeltaMovement(vec3.x, Math.max((double)f, vec3.y), vec3.z);
				if (entity.isSprinting()) {
					float g = entity.getYRot() * ((float)Math.PI / 180F);
					entity.addDeltaMovement(new Vec3((double)(-Mth.sin((double)g)) * 0.2, (double)0.0F, (double)Mth.cos((double)g) * 0.2));
				}
			}
			entity.needsSync = true;
		}
	}

	@Inject(at = @At("HEAD"), method = "jumpOutOfFluid", cancellable = true)
	private void Liquamentum$jumpOutOfFluid(double d, CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (entity.hasEffect(LiquamentumEffects.ROCK)) {
			if (entity.onGround()) {
				float f = (float) (BASE_JUMP_POWER / 1.5);
				Vec3 vec3 = entity.getDeltaMovement();
				entity.setDeltaMovement(vec3.x, Math.max((double)f, vec3.y), vec3.z);
				if (entity.isSprinting()) {
					float g = entity.getYRot() * ((float)Math.PI / 180F);
					entity.addDeltaMovement(new Vec3((double)(-Mth.sin((double)g)) * 0.2, (double)0.0F, (double)Mth.cos((double)g) * 0.2));
				}
			}
			entity.needsSync = true;
		}
	}

	@Inject(at = @At("HEAD"), method = "shouldTravelInFluid", cancellable = true)
	private void Liquamentum$shouldTravelInFluid(CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (entity.hasEffect(LiquamentumEffects.ROCK)) {
			cir.setReturnValue(false);
		}
	}

	@Inject(at = @At("HEAD"), method = "travelInWater", cancellable = true)
	private void Liquamentum$travelInWater(Vec3 vec3, double d, boolean bl, double e, CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (entity.hasEffect(LiquamentumEffects.ROCK)) {
			ci.cancel();
		}
	}


	@Inject(at = @At("HEAD"), method = "canStandOnFluid", cancellable = true)
	private void Liquamentum$canStandOnFluid(FluidState fluidState, CallbackInfoReturnable<Boolean> cir) {
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

	@Inject(at = @At("HEAD"), method = "canBreatheUnderwater", cancellable = true)
	private void Liquamentum$canBreathUnderwater(CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;

		if (entity.hasEffect(LiquamentumEffects.GILLS)) {
			cir.setReturnValue(true);
		}
	}




}