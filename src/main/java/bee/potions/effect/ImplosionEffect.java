package bee.potions.effect;


import bee.potions.registry.LiquamentumEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class ImplosionEffect extends MobEffect{
    public ImplosionEffect(MobEffectCategory mobEffectCategory, int i) {
        super(MobEffectCategory.HARMFUL, 123213);
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int i) {
        Level level = livingEntity.level();
        BlockPos pos = livingEntity.getOnPos();

        level.explode(null, pos.getX(), pos.getY() +1, pos.getZ(), 3, Level.ExplosionInteraction.MOB);
        livingEntity.removeEffect(LiquamentumEffects.IMPLOSION);
        super.onEffectAdded(livingEntity, i);
    }
}
