package bee.potions.mixin;

import bee.potions.registry.LiquamentumEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PanicGoal.class)
public abstract class AnimalMixin {

	@Shadow @Final protected PathfinderMob mob;

	@Inject(at = @At("HEAD"), method = "shouldPanic", cancellable = true)
	private void ifHurtRemoveSleepy(CallbackInfoReturnable<Boolean> cir) {
		PathfinderMob entity = this.mob;
		if (entity != null) {
			Level level = entity.level();
			List<Entity> entities = level.getPushableEntities(entity, entity.getBoundingBox().inflate(5));
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i) instanceof LivingEntity livingEntity) {
					if (livingEntity.hasEffect(LiquamentumEffects.FEAR)) {
						entity.setLastHurtByMob(livingEntity);
						entity.setLastHurtMob(livingEntity);
						cir.setReturnValue(true);
					}
				}
			}
		}


	}


}