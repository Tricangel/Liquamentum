package bee.potions.mixin.client;

import bee.potions.overlay.EffectOverlay;
import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(at = @At("HEAD"), method = "extractRenderState", cancellable = true)
    private static void cancelArmorRendering(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        EffectOverlay.renderBlueBox(graphics);
    }


}
