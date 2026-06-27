package bee.potions.effect;

import bee.potions.cca.EffectComponent;
import bee.potions.effect.effectcondition.WhenWet;
import bee.potions.effect.tick.CooldownOnTick;
import bee.potions.effect.tick.MovementBurst;
import bee.potions.effect.tick.OnTick;
import bee.potions.registry.LiquamentumEntityComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

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

        if (effect.getEffectTrigger().value() instanceof OnTick onTick) {

            if (onTick instanceof CooldownOnTick cooldown) cooldown.setCooldown(cooldown.getCooldown() - 1);

            if (effect.canTrigger(livingEntity)) {

                onTick.triggerEffect(livingEntity);

                if (onTick.removesAfterTick())
                    LiquamentumEntityComponents.EFFECTS.get(livingEntity).removeEffect(this);
            }
        }
    }

    public void applyToLivingEntity(LivingEntity livingEntity) {
        LiquamentumEntityComponents.EFFECTS.get(livingEntity).addEffect(this);
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
        return effect.getShouldTrigger().getRegisteredName() + effect.getEffectTrigger().getRegisteredName() + duration;
    }
}
