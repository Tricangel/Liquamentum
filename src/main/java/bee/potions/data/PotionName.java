package bee.potions.data;

import bee.potions.Liquamentum;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public class PotionName {
    public String name;
    public final List<MobEffect> effects;

    public PotionName(String name, List<MobEffect> effects) {
        this.name = name;
        this.effects = effects;
        Liquamentum.ALL_EFFECTS.add(this);
    }

    public String getName() {
        return name;
    }

    public List<MobEffect> getEffects() {
        return effects;
    }

    public void setName(String name) {
        this.name = name;
    }

}
