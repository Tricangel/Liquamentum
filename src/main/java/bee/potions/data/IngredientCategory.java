package bee.potions.data;

import bee.potions.effect.Effect;
import bee.potions.effect.OnTick;
import bee.potions.effect.ShouldTick;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;

import java.util.List;

public class IngredientCategory {

    private final List<ShouldTick> ingredientShouldTicks;

    private final List<OnTick> ingredientOnTicks;

    private final List<Item> ingredients;

    public IngredientCategory(List<ShouldTick> ingredientShouldTicks, List<OnTick> ingredientOnTicks, List<Item> ingredients) {
        this.ingredientShouldTicks = ingredientShouldTicks;
        this.ingredientOnTicks = ingredientOnTicks;

        this.ingredients = ingredients;
    }

    public List<Item> getIngredients() {
        return ingredients;
    }

    public List<OnTick> getIngredientOnTicks() {
        return ingredientOnTicks;
    }

    public List<ShouldTick> getIngredientShouldTicks() {
        return ingredientShouldTicks;
    }

    public static final Codec<IngredientCategory> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ShouldTick.CODEC.listOf().fieldOf("should_tick").forGetter(IngredientCategory::getIngredientShouldTicks),
            OnTick.CODEC.listOf().fieldOf("on_tick").forGetter(IngredientCategory::getIngredientOnTicks),
            BuiltInRegistries.ITEM.byNameCodec().listOf().fieldOf("ingredients").forGetter(IngredientCategory::getIngredients)
    ).apply(instance, IngredientCategory::new));

}
