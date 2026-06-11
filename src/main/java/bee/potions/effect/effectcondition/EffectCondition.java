package bee.potions.effect.effectcondition;

import bee.potions.registry.LiquamentumRegistries;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;

public abstract class EffectCondition {
    public static final Codec<Holder<EffectCondition>> CODEC = LiquamentumRegistries.SHOULD_TRIGGER.holderByNameCodec();


    public boolean canTrigger(LivingEntity livingEntity) {
        return true;
    }

    public boolean hasReturnValue() {
        return false;
    }

    public float getReturnValue() {
        return 0f;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
