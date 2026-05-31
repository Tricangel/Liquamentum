package bee.potions.cca;

import bee.potions.effect.Effect;
import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v8.component.CardinalComponent;

import java.util.ArrayList;
import java.util.List;

public class EffectComponent implements CardinalComponent, AutoSyncedComponent {
    private final LivingEntity entity;
    private List<Effect> effects = new ArrayList<>();

    public EffectComponent(LivingEntity entity) {
        this.entity = entity;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
        LiquamentumEntityComponents.EFFECTS.sync(entity);
    }

    public void addEffect(Effect effect) {
        boolean isNew = true;
        for (Effect effect1 : effects) {
            if (effect1.equals(effect)) {
                effect1.setDuration(effect.getDuration());
                isNew = false;
            }
        }
        if (isNew) this.effects.add(effect);
        LiquamentumEntityComponents.EFFECTS.sync(entity);
    }

    public void removeEffect(Effect effect) {
        List<Effect> effects = new ArrayList<>();
        for (Effect effect1 : this.effects) {
            if (!effect1.equals(effect)) {
                effects.add(effect1);
            }
        }
        this.setEffects(effects);
        LiquamentumEntityComponents.EFFECTS.sync(entity);
    }


    @Override
    public void readData(ValueInput readView) {
        readView.read("effects", Effect.CODEC.listOf());
    }

    @Override
    public void writeData(ValueOutput writeView) {
        writeView.store("effects", Effect.CODEC.listOf(), getEffects());
    }
}
