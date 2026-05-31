package bee.potions.effect;

import bee.potions.registry.LiquamentumRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Decoder;
import com.mojang.serialization.codecs.SimpleMapCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ShouldTick {
    public static final Codec<ShouldTick> CODEC = LiquamentumRegistries.SHOULD_TICK.byNameCodec();
    private final String name;

    public ShouldTick(String name) {
        this.name= name;
    }

    public String getName() {
        return name;
    }

    public boolean canTick(LivingEntity livingEntity) {
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
