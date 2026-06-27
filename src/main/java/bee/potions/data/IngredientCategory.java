package bee.potions.data;

import bee.potions.effect.EffectTrigger;
import bee.potions.effect.effectcondition.EffectCondition;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.List;

public class IngredientCategory {

    private final List<Holder<EffectCondition>> ingredientShouldTriggers;

    private final List<Holder<EffectTrigger>> ingredientOnTicks;

    private final List<Item> ingredients;

    public IngredientCategory(List<Holder<EffectCondition>> ingredientShouldTriggers, List<Holder<EffectTrigger>> ingredientOnTicks, List<Item> ingredients) {
        this.ingredientShouldTriggers = ingredientShouldTriggers;
        this.ingredientOnTicks = ingredientOnTicks;

        this.ingredients = ingredients;
    }

    public List<Item> getIngredients() {
        return ingredients;
    }

    public List<Holder<EffectTrigger>> getIngredientOnTicks() {
        return ingredientOnTicks;
    }

    public List<Holder<EffectCondition>> getIngredientShouldTicks() {
        return ingredientShouldTriggers;
    }

    public static final Codec<IngredientCategory> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EffectCondition.CODEC.listOf().fieldOf("should_trigger").forGetter(IngredientCategory::getIngredientShouldTicks),
            EffectTrigger.CODEC.listOf().fieldOf("effect_trigger").forGetter(IngredientCategory::getIngredientOnTicks),
            BuiltInRegistries.ITEM.byNameCodec().listOf().fieldOf("ingredients").forGetter(IngredientCategory::getIngredients)
    ).apply(instance, IngredientCategory::new));

}
