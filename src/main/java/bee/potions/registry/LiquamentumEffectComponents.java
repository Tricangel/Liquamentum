package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.effect.EffectTrigger;
import bee.potions.effect.posthit.Wet;
import bee.potions.effect.effectcondition.EffectCondition;
import bee.potions.effect.effectcondition.WhenWet;
import bee.potions.effect.tick.MovementBurst;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;

public class LiquamentumEffectComponents {




    public static final Holder<EffectCondition> EMPTY_SHOULD = registerEffectCondition("empty_should", new WhenWet());

    public static final Holder<EffectTrigger> EMPTY_EFFECT = registerEffectTrigger("empty_effect", new Wet());

    public static final Holder<EffectTrigger> MOVEMENT_BURST = registerEffectTrigger("movement_burst", new MovementBurst());

    private static Holder<EffectCondition> registerEffectCondition(String name, EffectCondition effectCondition) {
        return Registry.registerForHolder(LiquamentumRegistries.SHOULD_TRIGGER, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, name), effectCondition);
    }

    private static Holder<EffectTrigger> registerEffectTrigger(String name, EffectTrigger effectTrigger) {
        return Registry.registerForHolder(LiquamentumRegistries.EFFECT_TRIGGER, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, name), effectTrigger);
    }


    public static void init() {}


}
