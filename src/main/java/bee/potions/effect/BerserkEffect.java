package bee.potions.effect;

import bee.potions.Liquamentum;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BerserkEffect extends MobEffect {
    public BerserkEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    @Override
    public void addAttributeModifiers(AttributeMap attributeMap, int i) {
        super.addAttributeModifiers(attributeMap, i);
        AttributeInstance moreDamageGiven = attributeMap.getInstance(Attributes.ATTACK_DAMAGE);
        if (moreDamageGiven != null) {
            moreDamageGiven.addTransientModifier(new AttributeModifier(
                    Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "attack_damage_increase"),
                    2,
                    AttributeModifier.Operation.ADD_VALUE

            ));
        }
    }
}
