package bee.potions.mixin;

import bee.potions.registry.LiquamentumEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.sheep.Sheep;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PanicGoal.class)
public abstract class AnimalMixin {

	@Shadow @Final protected PathfinderMob mob;

	@Inject(at = @At("HEAD"), method = "shouldPanic", cancellable = true)
	private void ifHurtRemoveSleepy(CallbackInfoReturnable<Boolean> cir) {
		PathfinderMob entity = this.mob;
		if (entity != null) {
			if (entity.hasEffect(LiquamentumEffects.FEAR)) {
				cir.setReturnValue(true);
			}
		}


	}


}