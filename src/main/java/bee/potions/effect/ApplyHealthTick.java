package bee.potions.effect;

import net.minecraft.world.entity.LivingEntity;

public class ApplyHealthTick extends OnTick{
    private final int health;

    public ApplyHealthTick(String name, int health) {
        super(name);
        this.health = health;
    }

    @Override
    public void tick(LivingEntity livingEntity) {
        super.tick(livingEntity);
        livingEntity.heal(health);
    }
}
