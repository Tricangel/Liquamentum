package bee.potions.effect.tick;

import bee.potions.effect.EffectTrigger;
import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public abstract  class OnTick extends EffectTrigger {



    public void triggerEffect(LivingEntity livingEntity) {
    }


    public boolean removesAfterTick() {
        return false;
    }



}
