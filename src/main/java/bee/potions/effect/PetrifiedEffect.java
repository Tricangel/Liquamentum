package bee.potions.effect;

import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class PetrifiedEffect extends MobEffect {
    public PetrifiedEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int i) {

        LiquamentumEntityComponents.ISPETRIFIED.get(livingEntity).setValue(true);
        super.onEffectAdded(livingEntity, i);
    }

    @Override
    public void onEffectRemoved(MobEffectInstance effectInstance, LivingEntity entity) {

        LiquamentumEntityComponents.ISPETRIFIED.get(entity).setValue(false);
        super.onEffectRemoved(effectInstance, entity);
    }
}
