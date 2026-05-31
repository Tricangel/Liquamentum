package bee.potions.effect;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class ExplodeTick extends OnTick{


    public ExplodeTick(String name) {
        super(name);
    }

    @Override
    public boolean removesAfterTick() {
        return true;
    }

    @Override
    public void tick(LivingEntity livingEntity) {
        super.tick(livingEntity);
        livingEntity.level().explode(livingEntity, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1, Level.ExplosionInteraction.MOB);
    }
}
