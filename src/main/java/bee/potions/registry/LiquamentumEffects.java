package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.effect.ConstantFireEffect;
import bee.potions.effect.EmptyEffect;
import bee.potions.effect.FrozenEffect;
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

    public static final Holder<MobEffect> FATIGUED =
            register("fatigued", new EmptyEffect(MobEffectCategory.HARMFUL, 12));

    public static final Holder<MobEffect> BLAZING =
            register("blazing", new ConstantFireEffect());

    public static final Holder<MobEffect> INDIGESTION =
            register("indigestion", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> IMPLOSION =
            register("implosion", new ImplosionEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> NUMBNESS =
            register("numbness", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> FEAR =
            register("fear", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> ROOTED =
            register("rooted", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> HYDROPHOBIA =
            register("hydrophobia", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> SINK =
            register("sink", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> WATER_WALKING =
            register("water_walking", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> LAVA_WALKING =
            register("lava_walking", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> FROZEN =
            register("frozen", new FrozenEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> ARSONIST =
            register("arsonist", new EmptyEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> GILLS =
            register("gills", new EmptyEffect(MobEffectCategory.BENEFICIAL, 123));



    private static Holder<MobEffect> register(String name, MobEffect effect) {
        Identifier id = Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, id, effect);
    }

    public static void init() {}

}
