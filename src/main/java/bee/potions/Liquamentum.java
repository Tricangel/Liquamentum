package bee.potions;

import bee.potions.registry.LiquamentumBlockEntities;
import bee.potions.registry.LiquamentumBlocks;
import bee.potions.registry.LiquamentumEffects;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
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
	public static boolean isFrozen(Minecraft minecraft) {
		LocalPlayer player = minecraft.player;
		if (player != null) {
			return (player.hasEffect(LiquamentumEffects.FATIGUED) || player.hasEffect(LiquamentumEffects.PETRIFIED) && !player.isCreative() && !player.isSpectator());
		} else return false;
	}

	public static boolean isFrozen(Player player) {
		if (player != null) {
			return (player.hasEffect(LiquamentumEffects.FATIGUED) || player.hasEffect(LiquamentumEffects.PETRIFIED) && !player.isCreative() && !player.isSpectator());
		} else return false;
	}

	public static boolean isFrozen(LivingEntity livingEntity) {
		if (livingEntity != null) {
			if (livingEntity instanceof Player player) {
				return (player.hasEffect(LiquamentumEffects.FATIGUED) || player.hasEffect(LiquamentumEffects.PETRIFIED) && !player.isCreative() && !player.isSpectator());
			} else {
				return (livingEntity.hasEffect(LiquamentumEffects.FATIGUED) || livingEntity.hasEffect(LiquamentumEffects.PETRIFIED) && !livingEntity.isSpectator());
			}
		} else return false;
	}

}