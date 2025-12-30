package bee.potions;

import bee.potions.registry.LiquamentumBlockEntities;
import bee.potions.registry.LiquamentumBlocks;
import bee.potions.registry.LiquamentumEffects;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
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





	}
	public static boolean isSleepy(Minecraft minecraft) {
		LocalPlayer player = minecraft.player;
		if (player != null) {
			return (player.hasEffect(LiquamentumEffects.SLEEPY) && !player.isCreative() && !player.isSpectator() && !player.isDeadOrDying());
		} else return false;
	}

	public static boolean isSleepy(Player player) {
		if (player != null) {
			return (player.hasEffect(LiquamentumEffects.SLEEPY) && !player.isCreative() && !player.isSpectator() && !player.isDeadOrDying());
		} else return false;
	}
}