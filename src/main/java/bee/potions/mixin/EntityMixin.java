package bee.potions.mixin;

import bee.potions.cca.EffectComponent;
import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
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
		effects.getEffects().forEach(effect -> {
			effect.tick(livingEntity);

		});
	}
}