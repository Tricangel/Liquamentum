package bee.potions.effect;

import bee.potions.Liquamentum;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;

public class FlyingEffect extends MobEffect {
    public FlyingEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity livingEntity, int i) {


        return super.applyEffectTick(serverLevel, livingEntity, i);
    }

    @Override
    public void addAttributeModifiers(AttributeMap attributeMap, int i) {
        super.addAttributeModifiers(attributeMap, i);
        AttributeInstance jumpStrength = attributeMap.getInstance(Attributes.JUMP_STRENGTH);
        if (jumpStrength != null) {
            jumpStrength.addTransientModifier(new AttributeModifier(
                    Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "flying_jump_strength"),
                    i + .5,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL

            ));
        }
    }

    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);
        AttributeInstance attribute = attributeMap.getInstance(Attributes.ATTACK_DAMAGE);
        if (attribute != null) {
            attribute.removeModifier(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "flying_jump_strength"));
        }
    }
}
