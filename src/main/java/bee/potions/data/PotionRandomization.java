package bee.potions.data;

import bee.potions.effect.Effect;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;

import java.util.List;

public class PotionRandomization {
    public static Codec<PotionRandomization> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(PotionRandomization::getItem),
            Effect.CODEC.fieldOf("effect").forGetter(PotionRandomization::getEffect),
            IngredientCategory.CODEC.fieldOf("category").forGetter(PotionRandomization::getCategory)
    ).apply(instance, PotionRandomization::new));
    public final Item item;
    public final Effect effect;
    public final IngredientCategory category;

    public PotionRandomization(Item item, Effect effect, IngredientCategory category) {
        this.item = item;
        this.effect = effect;
        this.category = category;

    }

    public Item getItem() {
        return item;
    }

    public Effect getEffect() {
        return effect;
    }

    public IngredientCategory getCategory() {
        return category;
    }
}
