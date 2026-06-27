package bee.potions.effect.tick;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;

public class MovementBurst extends CooldownOnTick {



    @Override
    public void triggerEffect(LivingEntity livingEntity) {
        if (!this.onCooldown()) {
            livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().add(livingEntity.getViewVector(1)));

            livingEntity.playSound(SoundEvents.BREEZE_WHIRL);

            this.setCooldown(getDefaultCooldown());
        }

    }

    @Override
    public float getDefaultCooldown() {
        return 5 * 20f;
    }

}
