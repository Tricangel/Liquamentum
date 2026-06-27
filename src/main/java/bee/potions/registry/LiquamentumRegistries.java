package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.data.IngredientCategory;
import bee.potions.effect.EffectTrigger;
import bee.potions.effect.effectcondition.EffectCondition;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public interface LiquamentumRegistries {
    ResourceKey<Registry<IngredientCategory>> INGREDIENT_CATEGORIES = createRegistryKey("ingredient_categories");
    ResourceKey<Registry<EffectCondition>> SHOULD_TRIGGER_KEY = createRegistryKey("should_trigger");
    ResourceKey<Registry<EffectTrigger>> EFFECT_TRIGGER_KEY = createRegistryKey("effect_trigger_key");

    Registry<EffectCondition> SHOULD_TRIGGER = BuiltInRegistries.registerSimple(SHOULD_TRIGGER_KEY, _ -> LiquamentumEffectComponents.WHEN_WET);
    Registry<EffectTrigger> EFFECT_TRIGGER = BuiltInRegistries.registerSimple(EFFECT_TRIGGER_KEY, _ -> LiquamentumEffectComponents.MOVEMENT_BURST);



    private static <T> ResourceKey<Registry<T>> createRegistryKey(String string) {
        return ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, string));
    }








}
