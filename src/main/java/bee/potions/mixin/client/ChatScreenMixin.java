package bee.potions.mixin.client;


import bee.potions.registry.LiquamentumEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin {

    @Inject(at = @At("HEAD"), method = "keyPressed", cancellable = true)
    private void cancelChat(KeyEvent keyEvent, CallbackInfoReturnable<Boolean> cir) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            if ((player.hasEffect(LiquamentumEffects.MUTE) && !player.isCreative() && !player.isSpectator())) {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    private void cancelChat(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            if ((player.hasEffect(LiquamentumEffects.MUTE) && !player.isCreative() && !player.isSpectator())) {
                ci.cancel();
            }
        }
    }




}
