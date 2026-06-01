package bee.potions.effect.shouldtrigger;

import bee.potions.registry.LiquamentumRegistries;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;

public abstract class ShouldTrigger {
    public static final Codec<Holder<ShouldTrigger>> CODEC = LiquamentumRegistries.SHOULD_TRIGGER.holderByNameCodec();
    private final String name;

    public ShouldTrigger(String name) {
        this.name= name;
    }

    public String getName() {
        return name;
    }

    public boolean canTrigger(LivingEntity livingEntity) {
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
