package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.data.IngredientCategory;
import bee.potions.effect.OnTick;
import bee.potions.effect.ShouldTick;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public interface LiquamentumRegistries {
    ResourceKey<Registry<IngredientCategory>> INGREDIENT_CATEGORIES = createRegistryKey("ingredient_categories");
    ResourceKey<Registry<ShouldTick>> SHOULD_TICK_KEY = createRegistryKey("should_tick");
    ResourceKey<Registry<OnTick>> ON_TICK_KEY = createRegistryKey("on_tick");

    Registry<ShouldTick> SHOULD_TICK = BuiltInRegistries.registerSimple(SHOULD_TICK_KEY, registry -> LiquamentumEffectTickers.TEST);
    Registry<OnTick> ON_TICK = BuiltInRegistries.registerSimple(ON_TICK_KEY, registry -> LiquamentumEffectTickers.TEST2);

    private static <T> ResourceKey<Registry<T>> createRegistryKey(String string) {
        return ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, string));
    }








}
