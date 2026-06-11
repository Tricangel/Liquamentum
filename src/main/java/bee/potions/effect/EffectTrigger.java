package bee.potions.effect;

import bee.potions.effect.tick.OnTick;
import bee.potions.registry.LiquamentumRegistries;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Util;
import org.jspecify.annotations.Nullable;

public abstract class EffectTrigger {
    //idk what to name this rn

    private @Nullable String descriptionId;
    public static final Codec<Holder<EffectTrigger>> CODEC = LiquamentumRegistries.EFFECT_TRIGGER.holderByNameCodec();




    protected String getOrCreateDescriptionId() {
        if (this.descriptionId == null) {
            this.descriptionId = "wawa";
            //this.descriptionId = Util.makeDescriptionId("effect", BuiltInRegistries.MOB_EFFECT.g);
        }

        return this.descriptionId;
    }

}
