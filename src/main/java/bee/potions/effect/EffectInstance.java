package bee.potions.effect;

import bee.potions.cca.EffectComponent;
import bee.potions.effect.tick.OnTick;
import bee.potions.registry.LiquamentumEntityComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

public class EffectInstance {
    public static final Codec<EffectInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Effect.CODEC.fieldOf("effect").forGetter(EffectInstance::getEffect),
            Codec.INT.fieldOf("duration").forGetter(EffectInstance::getDuration)
    ).apply(instance, EffectInstance::new));
    private final Effect effect;
    private int duration;

    public EffectInstance(Effect effect, int duration) {
        this.effect = effect;
        this.duration = duration;

    }



    public void tick(LivingEntity livingEntity) {
        duration--;
        if (duration < 0) duration = 100;
        if (duration <= 0) LiquamentumEntityComponents.EFFECTS.get(livingEntity).removeEffect(this);

        if (effect.getShouldTrigger().value().canTrigger(livingEntity)) {
            if (effect.getEffectTrigger().value() instanceof OnTick onTick) {
                if (!onTick.onCooldown()) onTick.triggerEffect(livingEntity);
                onTick.setCooldown(onTick.getCooldown() - 1);
                if (onTick.removesAfterTick())
                    LiquamentumEntityComponents.EFFECTS.get(livingEntity).removeEffect(this);
            }
        }
        if (livingEntity instanceof ServerPlayer player) {
            LiquamentumEntityComponents.EFFECTS.sync(player);
        }
    }

    public void applyToLivingEntity(LivingEntity livingEntity) {
        EffectComponent effects = LiquamentumEntityComponents.EFFECTS.get(livingEntity);
        effects.addEffect(this);
    }



    public int getDuration() {
        return duration;
    }
    public Effect getEffect() {
        return effect;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return effect.getShouldTrigger().getRegisteredName() + effect.getEffectTrigger().getRegisteredName();
    }
}
