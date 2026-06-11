package bee.potions.effect.tick;

import bee.potions.effect.EffectTrigger;
import bee.potions.registry.LiquamentumRegistries;
import com.mojang.serialization.Codec;
import net.minecraft.world.entity.LivingEntity;

public abstract  class OnTick extends EffectTrigger {


    private float cooldown;

    public void triggerEffect(LivingEntity livingEntity) {
    }

    public boolean removesAfterTick() {
        return false;
    }


    public float getCooldown() {
        return cooldown;
    }

    public boolean onCooldown() {
        return this.cooldown > 0f;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public void setCooldownSeconds(float cooldown) {
        this.cooldown = cooldown * 20;
    }
}
