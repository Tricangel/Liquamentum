package bee.potions.effect;

import bee.potions.registry.LiquamentumEntityComponents;
import bee.potions.registry.LiquamentumRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.codecs.SimpleMapCodec;
import net.minecraft.world.entity.LivingEntity;

public class OnTick {
    public static final Codec<OnTick> CODEC = LiquamentumRegistries.ON_TICK.byNameCodec();
    private final String name;
    public OnTick(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void tick(LivingEntity livingEntity) {
    }

    public boolean removesAfterTick() {
        return false;
    }
}
