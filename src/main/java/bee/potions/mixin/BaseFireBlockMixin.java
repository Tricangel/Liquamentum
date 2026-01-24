package bee.potions.mixin;

import bee.potions.registry.LiquamentumEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseFireBlock.class)
public abstract class BaseFireBlockMixin {


    @Shadow
    protected abstract void spawnDestroyParticles(Level level, Player player, BlockPos blockPos, BlockState blockState);

    @Inject(at = @At("HEAD"), method = "entityInside", cancellable = true)
    private void Liquamentum$cancelFire(BlockState blockState, Level level, BlockPos blockPos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier, boolean bl, CallbackInfo ci) {
        if (entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(LiquamentumEffects.FIREPROOF)) {
            this.spawnDestroyParticles(level, (Player) entity, blockPos, blockState);
            entity.playSound(SoundEvents.FIRE_EXTINGUISH, .25f, 1);
            level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 0);
            ci.cancel();
        }
    }

}
