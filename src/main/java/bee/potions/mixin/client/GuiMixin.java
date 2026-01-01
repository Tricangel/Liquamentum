package bee.potions.mixin.client;


import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumEffects;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @Shadow public abstract void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker);

    @Shadow @Final private Minecraft minecraft;

    @Inject(at = @At("HEAD"), method = "renderHearts", cancellable = true)
    private void cancelRendering(GuiGraphics guiGraphics, Player player, int i, int j, int k, int l, float f, int m, int n, int o, boolean bl, CallbackInfo ci) {
        if (player.hasEffect(LiquamentumEffects.NUMBNESS)) {
            int x = i;
            for (int p = 0; p < player.getMaxHealth() / 2; p++) {
                guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "hud/heart/numbness") , x, j, 9, 9);
                x += 8;
            }
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "renderChat", cancellable = true)
    private void cancelChat(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            if ((player.hasEffect(LiquamentumEffects.MUTE) && !player.isCreative() && !player.isSpectator())) {
                ci.cancel();
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "render")
    private void rootedOverlay(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (Liquamentum.shouldApplyRoots(minecraft)) {
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "hud/rooted_overlay"), 0, 0, guiGraphics.guiWidth(), guiGraphics.guiHeight());
        }
    }




}
