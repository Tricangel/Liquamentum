package bee.potions.mixin;

import bee.potions.registry.LiquamentumEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin {



    @Inject(at = @At("HEAD"), method = "entityInside", cancellable = true)
    private void Liquamentum$cancelFire(Level level, BlockPos blockPos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier, CallbackInfo ci) {
        if (entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(LiquamentumEffects.FIREPROOF)) {
            entity.playSound(SoundEvents.FIRE_EXTINGUISH, .25f, 0.8f);
            if (level.getFluidState(blockPos).isSource()) level.setBlock(blockPos, Blocks.OBSIDIAN.defaultBlockState(), 0);
            else level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 0);
            ci.cancel();
        }
    }

}
