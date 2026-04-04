package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.ingredientEffect.IngredientCategory;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public interface LiquamentumRegistries {
    ResourceKey<Registry<IngredientCategory>> INGREDIENT_CATEGORIES = createRegistryKey("ingredient_categories");

    private static <T> ResourceKey<Registry<T>> createRegistryKey(String string) {
        return ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, string));
    }
}
