package bee.potions.data;

import bee.potions.Liquamentum;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public class PotionName {
    public static Codec<PotionName> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(PotionName::getName),
            MobEffect.CODEC.listOf().fieldOf("effects").forGetter(PotionName::getEffects)
    ).apply(instance, PotionName::new));
    public String name;
    public final List<Holder<MobEffect>> effects;

    public PotionName(String name, List<Holder<MobEffect>> effects) {
        this.name = name;
        this.effects = effects;

    }

    public String getName() {
        return name;
    }

    public List<Holder<MobEffect>> getEffects() {
        return effects;
    }

    public void setName(String name) {
        this.name = name;
    }

}
