package bee.potions.effect;

import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ObscuredEffect extends MobEffect {
    public ObscuredEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int i) {

        LiquamentumEntityComponents.ISOBSCURED.get(livingEntity).setValue(true);

        super.onEffectAdded(livingEntity, i);
    }

    @Override
    public void onEffectRemoved(MobEffectInstance effectInstance, LivingEntity entity) {

        LiquamentumEntityComponents.ISOBSCURED.get(entity).setValue(false);

        super.onEffectRemoved(effectInstance, entity);
    }
}
