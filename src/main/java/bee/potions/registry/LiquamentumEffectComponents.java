package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.effect.EffectTrigger;
import bee.potions.effect.posthit.Wet;
import bee.potions.effect.shouldtrigger.ShouldTrigger;
import bee.potions.effect.shouldtrigger.WhenWet;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;

public class LiquamentumEffectComponents {




    public static final Holder<ShouldTrigger> EMPTY_SHOULD = registerShouldTick("empty_should", new WhenWet("empty"));

    public static final Holder<EffectTrigger> EMPTY_EFFECT = registerOnTick("empty_effect", new Wet("name"));


    private static Holder<ShouldTrigger> registerShouldTick(String name, ShouldTrigger shouldTrigger) {
        return Registry.registerForHolder(LiquamentumRegistries.SHOULD_TRIGGER, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, name), shouldTrigger);
    }

    private static Holder<EffectTrigger> registerOnTick(String name, EffectTrigger effectTrigger) {
        return Registry.registerForHolder(LiquamentumRegistries.EFFECT_TRIGGER, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, name), effectTrigger);
    }


    public static void init() {}


}
