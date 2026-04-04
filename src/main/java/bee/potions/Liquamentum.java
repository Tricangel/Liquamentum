package bee.potions;

import bee.potions.ingredientEffect.IngredientCategory;
import bee.potions.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Liquamentum implements ModInitializer {
	public static final String MOD_ID = "liquamentum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		LiquamentumBlocks.init();
		LiquamentumBlockEntities.init();
		LiquamentumEffects.init();
		LiquamentumItems.init();
		LiquamentumComponents.init();
		LiquamentumAttributes.init();
		LiquamentumIngredientCategorys.init();

		DynamicRegistries.register(LiquamentumRegistries.INGREDIENT_CATEGORIES, IngredientCategory.CODEC);





	}


}