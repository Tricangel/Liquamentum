package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.effect.ConstantFireEffect;
import bee.potions.effect.EmptyEffect;
import bee.potions.effect.ImplosionEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class LiquamentumEffects {
    public static final Holder<MobEffect> LOWLIGHT =
            register("lowlight", new EmptyEffect(MobEffectCategory.HARMFUL, 12));

    public static final Holder<MobEffect> MUTE =
            register("mute", new EmptyEffect(MobEffectCategory.HARMFUL, 12));

    public static final Holder<MobEffect> SLEEPY =
            register("sleepy", new EmptyEffect(MobEffectCategory.HARMFUL, 12));

    public static final Holder<MobEffect> CONSTANT_FIRE =
            register("constant_fire", new ConstantFireEffect());

    public static final Holder<MobEffect> NO_EATING =
            register("no_eating", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> IMPLOSION =
            register("implosion", new ImplosionEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> NUMBNESS =
            register("numbness", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> FEAR =
            register("fear", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> ROOTED =
            register("rooted", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> NOSWIM =
            register("noswim", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> SINK =
            register("sink", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> WATER_WALKING =
            register("water_walking", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> LAVA_WALKING =
            register("lava_walking", new EmptyEffect(MobEffectCategory.HARMFUL, 123));



    private static Holder<MobEffect> register(String name, MobEffect effect) {
        Identifier id = Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, id, effect);
    }

    public static void init() {}

}
