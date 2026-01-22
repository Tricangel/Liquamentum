package bee.potions.mixin;

import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Shadow public abstract void setSprinting(boolean bl);

	@Shadow
	@Final
	public static float BASE_JUMP_POWER;

	@Shadow
	protected abstract double getDefaultGravity();


	@Inject(at = @At("HEAD"), method = "hurtServer", cancellable = true)
	private void hurtServer(ServerLevel serverLevel, DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;

		if (entity.hasEffect(LiquamentumEffects.FATIGUED)) {
			entity.removeEffect(LiquamentumEffects.FATIGUED);
			cir.setReturnValue(false);
		}

		if (Liquamentum.isFrozen(entity)) {
			cir.setReturnValue(false);
		}

		if (entity.hasEffect(LiquamentumEffects.ARSONIST)) {
			if (damageSource.is(DamageTypeTags.IS_FIRE)) {
				if (!entity.isInvulnerableTo(serverLevel, damageSource)) {
					if (entity.hasEffect(LiquamentumEffects.BLAZING)) amount /= 4;
					if (damageSource.is(DamageTypes.IN_FIRE)) {
						amount /= 32;
					} else amount /= 4;
					entity.heal(amount);
					cir.setReturnValue(false);
				}
			}
		}

		if (damageSource.getEntity() != null) {
			LivingEntity attacker = (LivingEntity) damageSource.getEntity();
			if (entity.hasEffect(LiquamentumEffects.THORNS)) {
				attacker.hurtServer(serverLevel, serverLevel.damageSources().thorns(entity), (float) Math.clamp(1 * (.2 * amount), 0, 15));
			}

			if (attacker.hasEffect(LiquamentumEffects.HOT_TOUCH)) {
				entity.igniteForSeconds(2);
			}

			if (attacker instanceof Player player) {
				if (player.hasEffect(LiquamentumEffects.SAITIATED))
					if (Random.from(new Random()).nextInt(0, 3) == 0) {
						player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + 1);
					}
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

	@Inject(at = @At("HEAD"), method = "getEffectiveGravity", cancellable = true)
	private void Liquamentum$getEffectiveGravity(CallbackInfoReturnable<Double> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;

		if (entity.hasEffect(LiquamentumEffects.LOW_GRAVITY)) {
			cir.setReturnValue(getDefaultGravity() / 2);
		}
	}

	@Inject(at = @At("HEAD"), method = "knockback", cancellable = true)
	private void Liquamentum$knockback(double d, double e, double f, CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;

		if (entity.hasEffect(LiquamentumEffects.STURDY)) {
			ci.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "heal", cancellable = true)
	private void Liquamentum$nohealing(float f, CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;

		if (entity.hasEffect(LiquamentumEffects.ANASTHETIC)) {
			ci.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "getVisibilityPercent", cancellable = true)
	private void Liquamentum$getVisibilityPercent(Entity entity, CallbackInfoReturnable<Double> cir) {
		LivingEntity livingEntity = (LivingEntity) (Object) this;


		if (livingEntity.hasEffect(LiquamentumEffects.KIN)) {
			if (entity != null) {
				EntityType<?> entityType = entity.getType();
				if (!(entityType == EntityType.WARDEN || entityType == EntityType.WITHER || entityType == EntityType.ENDER_DRAGON || entityType == EntityType.ELDER_GUARDIAN)) {
					cir.setReturnValue(0.0);
				}
			} else cir.setReturnValue(0.0);
		}

		if (livingEntity.hasEffect(LiquamentumEffects.SHINY)) {
			cir.setReturnValue(2.0);
		}

		if (livingEntity.hasEffect(LiquamentumEffects.ANGERS_HOSTILE)) {
			cir.setReturnValue(2.0);
		}

	}


	@ModifyVariable(at = @At(value = "HEAD"), method = "hurtServer", argsOnly = true)
	private float modifyDamageamount(float original) {
		LivingEntity entity = (LivingEntity) (Object) this;
		LivingEntity attackerEntity = entity.getLastAttacker();
		if (entity != null) {
			if (entity.hasEffect(LiquamentumEffects.BERSERK)) return original + entity.getEffect(LiquamentumEffects.BERSERK).getAmplifier() + 2;

			if (entity.hasEffect(LiquamentumEffects.FIRE_VULNERABILITY)) return original * (entity.getEffect(LiquamentumEffects.FIRE_VULNERABILITY).getAmplifier() + 2);


			if (entity.hasEffect(LiquamentumEffects.BLAZING)) {
				return original + (entity.getEffect(LiquamentumEffects.BLAZING).getAmplifier() + 1) * 2;
			}
		}

		if (attackerEntity instanceof LivingEntity attacker) {
			if (attacker.hasEffect(LiquamentumEffects.RAGE)) {
				return (float) (original + (attacker.getMaxHealth() - attacker.getHealth()) / 2.5);
			}
		}


		return original;
	}




}