package bee.potions.mixin;

import bee.potions.cca.EffectComponent;
import bee.potions.effect.posthit.PostHit;
import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class EntityMixin {
	@Inject(at = @At("HEAD"), method = "tick")
	private void cancelInteraction(CallbackInfo ci) {
		LivingEntity livingEntity = (LivingEntity) (Object) this;
		EffectComponent effects = LiquamentumEntityComponents.EFFECTS.get(livingEntity);
		effects.getEffectInstances().forEach(effect -> {
			effect.tick(livingEntity);

		});
	}


	@Inject(at = @At("HEAD"), method = "hurtServer")
	private void wawaw(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
		if (source.getEntity() != null && source.getEntity() instanceof LivingEntity livingEntity) {
			EffectComponent effects = LiquamentumEntityComponents.EFFECTS.get(livingEntity);
			effects.getEffectInstances().forEach(effect -> {
				if (effect.getEffect().getEffectTrigger() instanceof PostHit postHit && source.getEntity() != null) {
					postHit.triggerEffect(livingEntity, (LivingEntity) (Object) this);
				}

			});
		}
	}
}