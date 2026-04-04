package bee.potions.effect;

import bee.potions.cca.BooleanComponent;
import bee.potions.registry.LiquamentumComponents;
import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;

public class ComponentEffect extends MobEffect {
    ComponentKey<BooleanComponent> component;
    public ComponentEffect(MobEffectCategory mobEffectCategory, int i, ComponentKey<BooleanComponent> component) {
        super(mobEffectCategory, i);
        this.component = component;
    }

    @Override
    public void onEffectAdded(LivingEntity entity, int i) {

        component.get(entity).setValue(true);
        super.onEffectAdded(entity, i);
    }

    @Override
    public void onEffectRemoved(MobEffectInstance effectInstance, LivingEntity entity) {

        component.get(entity).setValue(false);
        super.onEffectRemoved(effectInstance, entity);
    }

}
