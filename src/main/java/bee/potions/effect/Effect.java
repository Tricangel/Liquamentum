package bee.potions.effect;

import bee.potions.cca.EffectComponent;
import bee.potions.effect.shouldtrigger.ShouldTrigger;
import bee.potions.effect.tick.OnTick;
import bee.potions.registry.LiquamentumEntityComponents;
import bee.potions.registry.LiquamentumRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Holder;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.HolderSetCodec;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class Effect {
    public static final Codec<Effect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ShouldTrigger.CODEC.fieldOf("should_tick").forGetter(Effect::getShouldTrigger),
            EffectTrigger.CODEC.fieldOf("on_tick").forGetter(Effect::getEffectTrigger)
    ).apply(instance, Effect::new));

    private final Holder<ShouldTrigger> shouldTrigger;
    private final Holder<EffectTrigger> effectTrigger;

    public Effect(Holder<ShouldTrigger> shouldTrigger, Holder<EffectTrigger> effectTrigger) {
        this.shouldTrigger = shouldTrigger;
        this.effectTrigger = effectTrigger;

    }

    //--- evil comment to make this more readable ---\\




    public Holder<EffectTrigger> getEffectTrigger() {
        return effectTrigger;
    }

    public Holder<ShouldTrigger> getShouldTrigger() {
        return shouldTrigger;
    }

}
