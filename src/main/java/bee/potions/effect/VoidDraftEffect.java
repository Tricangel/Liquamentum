package bee.potions.effect;


import bee.potions.registry.LiquamentumEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class VoidDraftEffect extends MobEffect{
    public VoidDraftEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        return true;
    }


    @Override
    public void onMobHurt(ServerLevel serverLevel, LivingEntity livingEntity, int i, DamageSource damageSource, float f) {
        if ((livingEntity.getY() < 0 && serverLevel.dimension() != Level.OVERWORLD)
                || (livingEntity.getY() < -64 && serverLevel.dimension() == Level.OVERWORLD) && damageSource.is(DamageTypes.FELL_OUT_OF_WORLD)) {
            livingEntity.playSound(SoundEvents.AMETHYST_BLOCK_CHIME);
            livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().x(), 6 * (i + 1), livingEntity.getDeltaMovement().z());
            livingEntity.hurtMarked = true;

            livingEntity.removeEffect(LiquamentumEffects.VOID_DRAFT);
        }
    }
}
