package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.effect.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class LiquamentumEffects {
    public static final Holder<MobEffect> INVERT_LIGHT =
            register("invert_light", new EmptyEffect(MobEffectCategory.HARMFUL, 12));

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

    public static final Holder<MobEffect> ROCK =
            register("rock", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

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

    public static final Holder<MobEffect> THORNS =
            register("thorns", new EmptyEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> FLYING =
            register("flying", new EmptyEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> RAIN_BLESSING =
            register("rain_blessing", new EmptyEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> BUTTER_FINGERS =
            register("butter_fingers", new ButterFingersEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> RAGE =
            register("rage", new EmptyEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> LOW_GRAVITY =
            register("low_gravity", new EmptyEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> FIRE_VULNERABILITY =
            register("fire_vulnerability", new EmptyEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> LOWER_JUMP =
            register("lower_jump", new LowerJumpEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> BERSERK =
            register("berserk", new BerserkEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> KIN =
            register("kin", new BerserkEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> SHINY =
            register("shiny", new BerserkEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> PETRIFIED =
            register("petrified", new EmptyEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> EXPERIENCED =
            register("experienced", new EmptyEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> DECAY =
            register("decay", new DecayEffect(MobEffectCategory.HARMFUL, 123));

    public static final Holder<MobEffect> STURDY =
            register("sturdy", new EmptyEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> HOT_TOUCH =
            register("hot_touch", new EmptyEffect(MobEffectCategory.BENEFICIAL, 123));

    public static final Holder<MobEffect> ROCK_APPEARANCE =
            register("rock_appearance", new EmptyEffect(MobEffectCategory.BENEFICIAL, 123));



    private static Holder<MobEffect> register(String name, MobEffect effect) {
        Identifier id = Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, id, effect);
    }

    public static void init() {}

}
