package bee.potions.mixin;

import bee.potions.registry.LiquamentumEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

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


}