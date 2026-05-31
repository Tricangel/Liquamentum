package bee.potions.effect;

import bee.potions.cca.EffectComponent;
import bee.potions.registry.LiquamentumEntityComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.LivingEntity;

public class Effect {
    public static final Codec<Effect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ShouldTick.CODEC.fieldOf("should_tick").forGetter(Effect::getShouldTick),
            OnTick.CODEC.fieldOf("on_tick").forGetter(Effect::getOnTick),
            Codec.INT.fieldOf("duration").forGetter(Effect::getDuration)

    ).apply(instance, Effect::new));

    private final ShouldTick shouldTick;
    private int duration;
    private final OnTick onTick;


    public Effect(ShouldTick shouldTick, OnTick onTick, int duration) {
        this.shouldTick = shouldTick;
        this.onTick = onTick;
        this.duration = duration;
    }


    public boolean shouldTick(LivingEntity livingEntity) {
        return shouldTick.canTick(livingEntity);
    }

    public void tick(LivingEntity livingEntity) {
        duration--;
        if (duration < 0) duration = 100;
        if (duration <= 0) LiquamentumEntityComponents.EFFECTS.get(livingEntity).removeEffect(this);

        if (shouldTick(livingEntity)) {
            this.onTick.tick(livingEntity);
            if (this.getOnTick().removesAfterTick())
                LiquamentumEntityComponents.EFFECTS.get(livingEntity).removeEffect(this);
        }
    }

    public void applyToLivingEntity(LivingEntity livingEntity) {
        EffectComponent effects = LiquamentumEntityComponents.EFFECTS.get(livingEntity);
        effects.addEffect(this);
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ShouldTick getShouldTick() {
        return shouldTick;
    }

    public OnTick getOnTick() {
        return onTick;
    }

    @Override
    public String toString() {
        return getShouldTick().toString() + getOnTick().toString() + duration;
    }
}
