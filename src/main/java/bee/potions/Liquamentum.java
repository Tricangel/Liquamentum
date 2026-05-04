package bee.potions;

import bee.potions.data.IngredientCategory;
import bee.potions.data.PotionName;
import bee.potions.registry.*;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
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

	public static Map<List<Holder<MobEffect>>, String> potionNameMap = new HashMap<>();

	public static Codec POTION_NAME_MAP_CODEC = Codec.unboundedMap(MobEffect.CODEC, Codec.STRING).fieldOf("potion_name_map").codec();

	public static List<PotionName> ALL_EFFECTS = new ArrayList<>();


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