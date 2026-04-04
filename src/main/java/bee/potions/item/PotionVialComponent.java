package bee.potions.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;
import java.util.Optional;

public record PotionVialComponent(Optional<Integer> customColour, List<MobEffectInstance> customEffects) {
    public static final Codec<PotionVialComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.optionalFieldOf("customColour").forGetter(PotionVialComponent::customColour), MobEffectInstance.CODEC.listOf().optionalFieldOf("customEffects", List.of()).forGetter(PotionVialComponent::customEffects)
    ).apply(instance, PotionVialComponent::new));

    public static final int BASE_POTION_COLOR = -13083194;

    public int getColour() {
        return customColour.orElse(BASE_POTION_COLOR);
    }


}
