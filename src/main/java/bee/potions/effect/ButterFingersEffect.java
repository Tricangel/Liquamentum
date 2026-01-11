package bee.potions.effect;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.RandomSequence;
import net.minecraft.world.RandomSequences;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;

import java.util.Random;

public class ButterFingersEffect extends MobEffect {
    public ButterFingersEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity livingEntity, int i) {
        i++;
        if (new Random().nextInt(0,  256 / i) == 0) {
            livingEntity.drop(livingEntity.getMainHandItem().consumeAndReturn(livingEntity.getMainHandItem().getCount(), livingEntity), false, true);
        }

        return super.applyEffectTick(serverLevel, livingEntity, i);
    }
}
