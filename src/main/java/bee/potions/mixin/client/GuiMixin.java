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
import net.minecraft.network.chat.Component;
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

    @Shadow
    protected abstract void renderTextureOverlay(GuiGraphics guiGraphics, Identifier identifier, float f);

    @Inject(at = @At("HEAD"), method = "renderHearts", cancellable = true)
    private void cancelRendering(GuiGraphics guiGraphics, Player player, int i, int j, int k, int l, float f, int m, int n, int o, boolean bl, CallbackInfo ci) {
        if (player.hasEffect(LiquamentumEffects.NUMBNESS) || player.hasEffect(LiquamentumEffects.ARSONIST)) {
            Identifier heartType = null;
            if (player.hasEffect(LiquamentumEffects.NUMBNESS)) heartType = Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "hud/heart/numbness");
            if (player.hasEffect(LiquamentumEffects.ARSONIST)) heartType = Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "hud/heart/arsonist");
            int x = i;
            for (int p = 0; p < player.getMaxHealth() / 2; p++) {
                guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, Identifier.withDefaultNamespace("hud/heart/container") , x, j, 9, 9);
                x += 8;
            }
            x = i;
            for (int p = 0; p < player.getMaxHealth() / 2; p++) {
                guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, heartType , x, j, 9, 9);
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
        LocalPlayer player = minecraft.player;
        if (player != null) {
            if (player.hasEffect(LiquamentumEffects.ROOTED) && !player.isCreative() && !player.isSpectator()) {
                this.renderTextureOverlay(guiGraphics, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "textures/misc/rooted_overlay.png"), 10000);
            }
            if (player.hasEffect(LiquamentumEffects.RAGE) && !player.isCreative() && !player.isSpectator()) {
                this.renderTextureOverlay(guiGraphics, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "textures/misc/rage_overlay.png"), (player.getMaxHealth() - player.getHealth()));
            }
        }
    }




}
