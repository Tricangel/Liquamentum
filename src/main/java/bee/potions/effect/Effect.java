package bee.potions.effect;

import bee.potions.effect.effectcondition.EffectCondition;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;

public class Effect {
    public static final Codec<Effect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EffectCondition.CODEC.fieldOf("should_tick").forGetter(Effect::getShouldTrigger),
            EffectTrigger.CODEC.fieldOf("on_tick").forGetter(Effect::getEffectTrigger)
    ).apply(instance, Effect::new));

    private final Holder<EffectCondition> shouldTrigger;
    private final Holder<EffectTrigger> effectTrigger;

    public Effect(Holder<EffectCondition> shouldTrigger, Holder<EffectTrigger> effectTrigger) {
        this.shouldTrigger = shouldTrigger;
        this.effectTrigger = effectTrigger;

    }

    //--- evil comment to make this more readable ---\\




    public Holder<EffectTrigger> getEffectTrigger() {
        return effectTrigger;
    }

    public Holder<EffectCondition> getShouldTrigger() {
        return shouldTrigger;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Effect effect) {
            return effect.getEffectTrigger().equals(getEffectTrigger())
                    && effect.getShouldTrigger().equals(getShouldTrigger());
        } else return false;
    }

    @Override
    public String toString() {
        return effectTrigger.getRegisteredName() + shouldTrigger.getRegisteredName();
    }
}
