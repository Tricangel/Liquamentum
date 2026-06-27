package bee.potions.effect.effectcondition;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class StandingStillCondition extends EffectCondition{

    @Override
    public boolean canTrigger(LivingEntity livingEntity) {
        boolean bl = livingEntity.getDeltaMovement().x == 0 && livingEntity.getDeltaMovement().z == 0;
        if (livingEntity instanceof Player player) player.sendOverlayMessage(Component.literal(String.valueOf(bl)));
        return bl;
    }
}
