package bee.potions.effect;

import bee.potions.registry.LiquamentumTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CauterizedEffect extends MobEffect {
    public CauterizedEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity livingEntity, int i) {
        ItemStack stack = livingEntity.getMainHandItem();

        if (stack.is(LiquamentumTags.BURNS_IN_HANDS)) {
            livingEntity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.CHARCOAL, stack.getCount()));
            livingEntity.playSound(SoundEvents.WITHER_DEATH, 1, 100);
        }


        return super.applyEffectTick(serverLevel, livingEntity, i);
    }
}
