package bee.potions;

import bee.potions.data.IngredientCategory;
import bee.potions.effect.Effect;
import bee.potions.packet.EffectS2CPacket;
import bee.potions.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Liquamentum implements ModInitializer {
	public static final String MOD_ID = "liquamentum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		LiquamentumBlocks.init();
		LiquamentumBlockEntities.init();
		LiquamentumItems.init();
		LiquamentumComponents.init();
		LiquamentumAttributes.init();
		LiquamentumEffectComponents.init();
		LiquamentumCommands.registerCommands();
		DynamicRegistries.register(LiquamentumRegistries.INGREDIENT_CATEGORIES, IngredientCategory.CODEC);
		PayloadTypeRegistry.clientboundPlay().register(EffectS2CPacket.TYPE, EffectS2CPacket.CODEC);
	}




}