package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.effect.ComponentEffect;
import bee.potions.effect.EmptyEffect;
import bee.potions.effect.VoidDraftEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class LiquamentumEffects {
    public static final Holder<MobEffect> EXAMPLE_EFFECT =
            register("example_effect", new ComponentEffect(MobEffectCategory.NEUTRAL, 12, LiquamentumEntityComponents.EXAMPLE));

    public static final Holder<MobEffect> VOID_DRAFT =
            register("void_draft", new VoidDraftEffect(MobEffectCategory.NEUTRAL, 12));

    public static final Holder<MobEffect> PETRIFIED =
            register("petrified", new ComponentEffect(MobEffectCategory.NEUTRAL, 12, LiquamentumEntityComponents.ISPETRIFIED));

    public static final Holder<MobEffect> OBSCURED =
            register("obscured", new ComponentEffect(MobEffectCategory.NEUTRAL, 12, LiquamentumEntityComponents.ISOBSCURED));



    private static Holder<MobEffect> register(String name, MobEffect effect) {
        Identifier id = Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, id, effect);
    }

    public static void init() {}

}
