package bee.potions.effect.tick;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;

public class MovementBurst extends OnTick{



    @Override
    public void triggerEffect(LivingEntity livingEntity) {

        livingEntity.addDeltaMovement(livingEntity.getViewVector(1));
        livingEntity.playSound(SoundEvents.BREEZE_WHIRL);
        this.setCooldownSeconds(5f);
    }
}
