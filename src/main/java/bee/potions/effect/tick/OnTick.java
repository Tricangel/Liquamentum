package bee.potions.effect.tick;

import bee.potions.effect.EffectTrigger;
import bee.potions.registry.LiquamentumRegistries;
import com.mojang.serialization.Codec;
import net.minecraft.world.entity.LivingEntity;

public abstract  class OnTick extends EffectTrigger {

    public OnTick(String name) {
        super(name);
    }

    public void triggerEffect(LivingEntity livingEntity) {
    }

    public boolean removesAfterTick() {
        return false;
    }
}
