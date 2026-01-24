package bee.potions;

import bee.potions.packet.PotionVialClickC2SPayload;
import bee.potions.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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

		PayloadTypeRegistry.playC2S().register(PotionVialClickC2SPayload.ID, PotionVialClickC2SPayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(PotionVialClickC2SPayload.ID, ((potionVialClickC2SPayload, context) -> {
			ItemStack stack = context.player().getInventory().getItem(potionVialClickC2SPayload.index());
			stack.set(LiquamentumComponents.CAN_BE_THROWN, !stack.get(LiquamentumComponents.CAN_BE_THROWN));
			context.player().disconnect();

		}));


	}


	public static boolean isClientFrozen(Minecraft minecraft) {
		LocalPlayer player = minecraft.player;
		if (player != null) {
			return ((player.hasEffect(LiquamentumEffects.FATIGUED) || player.hasEffect(LiquamentumEffects.PETRIFIED)) && !player.isCreative() && !player.isSpectator());
		} else return false;
	}

	public static boolean isFrozen(LivingEntity livingEntity) {
		if (livingEntity != null) {
			if (livingEntity instanceof Player player) {
				return ((player.hasEffect(LiquamentumEffects.FATIGUED) || player.hasEffect(LiquamentumEffects.PETRIFIED)) && !player.isCreative() && !player.isSpectator());
			} else {
				return ((livingEntity.hasEffect(LiquamentumEffects.FATIGUED) || livingEntity.hasEffect(LiquamentumEffects.PETRIFIED)) && !livingEntity.isSpectator());
			}
		} else return false;
	}

}