package bee.potions.effect.tick;

import net.minecraft.world.entity.LivingEntity;

public class SetOnFire extends OnTick {



    @Override
    public void triggerEffect(LivingEntity livingEntity) {
        if (!livingEntity.isOnFire()) {
            livingEntity.igniteForSeconds(1f);
        }

    }

}
