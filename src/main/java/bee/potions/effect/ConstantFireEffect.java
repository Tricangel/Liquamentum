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
        if (livingEntity instanceof Player player) {
            if (!player.isCreative() && !player.isSpectator() ) {
                player.setRemainingFireTicks(10);
                if (!player.fireImmune()) {
                    player.hurtServer(serverLevel, player.damageSources().inFire(), 1);
                }
            }
        } else {
            livingEntity.setRemainingFireTicks(10);
            livingEntity.hurtServer(serverLevel, livingEntity.damageSources().inFire(), 1);
        }
        return super.applyEffectTick(serverLevel, livingEntity, i);
    }
}
