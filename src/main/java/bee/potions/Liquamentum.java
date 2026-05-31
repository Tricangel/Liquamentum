package bee.potions;

import bee.potions.data.IngredientCategory;
import bee.potions.data.PotionName;
import bee.potions.registry.*;
import com.mojang.serialization.Codec;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		LiquamentumEffectTickers.init();

		DynamicRegistries.register(LiquamentumRegistries.INGREDIENT_CATEGORIES, IngredientCategory.CODEC);

	}




}