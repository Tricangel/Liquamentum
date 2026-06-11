package bee.potions.effect.effectcondition;

import net.minecraft.world.entity.LivingEntity;

public class WhenWet extends EffectCondition {


    @Override
    public boolean canTrigger(LivingEntity livingEntity) {
        return livingEntity.isInWaterOrRain();
    }
}
