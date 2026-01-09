package bee.potions.mixin;

import bee.potions.registry.LiquamentumEffects;
import com.mojang.blaze3d.platform.Lighting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LightChunk;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightEngine.class)
public abstract class LightEngineMixin {
	@Shadow
	public abstract int runLightUpdates();

	@Inject(at = @At("TAIL"), method = "getLightValue", cancellable = true)
	private void cancelInteraction(BlockPos blockPos, CallbackInfoReturnable<Integer> cir) {
		LocalPlayer player = Minecraft.getInstance().player;
		if (player != null) {
			if (player.hasEffect(LiquamentumEffects.INVERT_LIGHT)) {
				cir.setReturnValue(-cir.getReturnValue());
			}
		}
	}
}