package bee.potions.effect;

import bee.potions.Liquamentum;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class LowerJumpEffect extends MobEffect {
    public LowerJumpEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }


    @Override
    public void addAttributeModifiers(AttributeMap attributeMap, int i) {
        AttributeInstance attribute = attributeMap.getInstance(Attributes.JUMP_STRENGTH);
        if (attribute != null) {
            attribute.addTransientModifier(new AttributeModifier(
                    Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "lower_jump"),
                    -attribute.getBaseValue() / 2,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE

            ));
        }
    }

    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);
        AttributeInstance attribute = attributeMap.getInstance(Attributes.JUMP_STRENGTH);
        if (attribute != null) {
            attribute.removeModifier(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "lower_jump"));
        }
    }
}
