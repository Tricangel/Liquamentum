package bee.potions.effect.posthit;

import bee.potions.effect.EffectTrigger;
import net.minecraft.world.entity.LivingEntity;

public abstract class PostHit extends EffectTrigger {

    public PostHit(String name) {
        super(name);
    }

    public void triggerEffect(LivingEntity attacker, LivingEntity target) {

    }

}
