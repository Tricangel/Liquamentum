package bee.potions.mixin.client;

import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin {

    @Inject(at = @At("HEAD"), method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)Lnet/minecraft/resources/Identifier;", cancellable = true)
    private void obscureEntityNames(AvatarRenderState avatarRenderState, CallbackInfoReturnable<Identifier> cir) {

        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            LiquamentumEntityComponents.ISOBSCURED.sync(player);
            if (LiquamentumEntityComponents.ISOBSCURED.get(player).getValue()) {
                cir.setReturnValue(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "textures/misc/petrified_player.png"));

            }
        }

    }





}
