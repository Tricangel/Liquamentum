package bee.potions.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;

public class PyrotrailEffect extends MobEffect {
    public PyrotrailEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity livingEntity, int i) {

        if (serverLevel.getBlockState(livingEntity.getOnPos().above()).isAir()) {
            serverLevel.setBlockAndUpdate(livingEntity.getOnPos().above(), Blocks.FIRE.defaultBlockState());
        }

        return super.applyEffectTick(serverLevel, livingEntity, i);
    }
}
