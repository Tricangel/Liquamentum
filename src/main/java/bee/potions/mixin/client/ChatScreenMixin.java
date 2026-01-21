package bee.potions.mixin.client;


import bee.potions.registry.LiquamentumEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin {


    @Inject(at = @At("HEAD"), method = "handleChatInput", cancellable = true)
    private void cancelChat(String string, boolean bl, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            if ((player.hasEffect(LiquamentumEffects.MUTE) && !player.isCreative() && !player.isSpectator())) {
                if (!string.startsWith("/")) {
                    if (!string.startsWith("/msg") || !string.startsWith("/me") || !string.startsWith("/tell") || !string.startsWith("/w") || !string.startsWith("/say")) {
                        ci.cancel();
                    }
                }
            }
        }
    }




}
