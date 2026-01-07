package bee.potions.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ConstantFireEffect extends MobEffect {
    public ConstantFireEffect() {
        super(MobEffectCategory.HARMFUL, 12);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity livingEntity, int i) {
        if (!livingEntity.isInWater()) {
            if (livingEntity instanceof Player player) {
                if (!player.isCreative() && !player.isSpectator()) {
                    if (livingEntity.getRemainingFireTicks() < 0) {
                        livingEntity.setRemainingFireTicks(10);
                    } else {
                        livingEntity.igniteForSeconds(1);
                    }
                }
            } else {
                if (livingEntity.getRemainingFireTicks() < 0) {
                    livingEntity.setRemainingFireTicks(10);
                } else {
                    livingEntity.igniteForSeconds(1);
                }
            }
        }
        return super.applyEffectTick(serverLevel, livingEntity, i);
    }
}
