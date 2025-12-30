package bee.potions.mixin.client;


import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumEffects;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {


    @Shadow public abstract void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker);

    @Shadow protected abstract void renderTextureOverlay(GuiGraphics guiGraphics, Identifier identifier, float f);



    @Inject(at = @At("HEAD"), method = "renderHearts", cancellable = true)
    private void cancelRendering(GuiGraphics guiGraphics, Player player, int i, int j, int k, int l, float f, int m, int n, int o, boolean bl, CallbackInfo ci) {
        if (player != null) {
            if (player.hasEffect(LiquamentumEffects.NUMBNESS) && !player.isCreative() && !player.isSpectator()) {
                this.renderTextureOverlay(guiGraphics, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "textures/gui/numbness.png"), 1);

                ci.cancel();

            }
        }
    }




}
