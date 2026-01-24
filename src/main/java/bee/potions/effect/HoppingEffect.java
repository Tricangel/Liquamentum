package bee.potions.effect;

import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumAttributes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class HoppingEffect extends MobEffect {
    public HoppingEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    @Override
    public void addAttributeModifiers(AttributeMap attributeMap, int i) {
        super.addAttributeModifiers(attributeMap, i);
        AttributeInstance moreHops = attributeMap.getInstance(LiquamentumAttributes.DOUBLE_JUMP);
        if (moreHops != null) {
            moreHops.addTransientModifier(new AttributeModifier(
                    Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "extra_jumps"),
                    i + 1,
                    AttributeModifier.Operation.ADD_VALUE

            ));
        }
    }

    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);
        AttributeInstance attribute = attributeMap.getInstance(LiquamentumAttributes.DOUBLE_JUMP);
        if (attribute != null) {
            attribute.removeModifier(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "extra_jumps"));
        }
    }

}
