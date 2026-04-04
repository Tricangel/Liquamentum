package bee.potions.ingredientEffect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;

import java.util.List;

public class IngredientCategory {

    private final List<Holder<MobEffect>> ingredientEffects;

    private final List<Item> ingredients;

    public IngredientCategory(List<Holder<MobEffect>> ingredientEffects, List<Item> ingredients) {
        this.ingredientEffects = ingredientEffects;
        this.ingredients = ingredients;
    }

    public List<Item> getIngredients() {
        return ingredients;
    }

    public List<Holder<MobEffect>> getIngredientEffects() {
        return ingredientEffects;
    }

    public static final Codec<IngredientCategory> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            MobEffect.CODEC.listOf().fieldOf("ingredient_effects").forGetter(IngredientCategory::getIngredientEffects),
            BuiltInRegistries.ITEM.byNameCodec().listOf().fieldOf("ingredients").forGetter(IngredientCategory::getIngredients)
    ).apply(instance, IngredientCategory::new));

}
