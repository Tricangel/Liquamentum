package bee.potions.mixin;

import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientboundPlayerAbilitiesPacket.class)
public abstract class ClientboundPlayerAbilitiesPacketMixin {

    @Inject(at = @At("HEAD"), method = "canFly", cancellable = true)
    private void waw(CallbackInfoReturnable<Boolean> cir) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.hasEffect(LiquamentumEffects.FLYING)) {
                cir.setReturnValue(true);
            }
        }
    }
}
