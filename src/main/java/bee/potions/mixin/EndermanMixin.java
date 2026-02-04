package bee.potions.mixin;

import bee.potions.registry.LiquamentumEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderMan.class)
public abstract class EndermanMixin {


    @Shadow
    private @Nullable EntityReference<LivingEntity> persistentAngerTarget;

    @Inject(at = @At("HEAD"), method = "isBeingStaredBy", cancellable = true)
    private void Liquamentum$cancelFire(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(LiquamentumEffects.SERENITY) && this.persistentAngerTarget == null) cir.setReturnValue(false);
    }

}
