package bee.potions.mixin.client;

import bee.potions.registry.LiquamentumEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

	@Inject(at = @At("HEAD"), method = "openChatScreen", cancellable = true)
	private void lowerLight(ChatComponent.ChatMethod chatMethod, CallbackInfo ci) {
		if (Minecraft.getInstance().player != null) {
			Player player = Minecraft.getInstance().player;
			if ((player.hasEffect(LiquamentumEffects.DEAF) && !player.isCreative() && !player.isSpectator())) {

				ci.cancel();
			}
		}

	}


}