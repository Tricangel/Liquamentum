package bee.potions.item;

import bee.potions.effect.Effect;
import bee.potions.effect.EffectInstance;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ConsumableListener;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public record PotionVialComponent(Optional<Integer> colour, List<EffectInstance> effects) implements TooltipProvider, ConsumableListener {
    public static final Codec<PotionVialComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.optionalFieldOf("colour").forGetter(PotionVialComponent::colour),
            EffectInstance.CODEC.listOf().optionalFieldOf("effects", List.of()).forGetter(PotionVialComponent::effects)
    ).apply(instance, PotionVialComponent::new));

    public static final int BASE_POTION_COLOR = -13083194;

    public int getColour() {
        return colour.orElse(BASE_POTION_COLOR);
    }


    public void applyToEntity(LivingEntity livingEntity) {
        this.effects.forEach(effect -> {
            effect.applyToLivingEntity(livingEntity);

        });
    }




    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        effects.forEach(effect -> {

        });
    }

    @Override
    public void onConsume(Level level, LivingEntity user, ItemStack stack, Consumable consumable) {
        applyToEntity(user);
    }
}
