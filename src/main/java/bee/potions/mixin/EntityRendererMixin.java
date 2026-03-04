package bee.potions.mixin;

import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {

    @Inject(at = @At("HEAD"), method = "getNameTag", cancellable = true)
    private void obscureEntityNames(Entity entity, CallbackInfoReturnable<Component> cir) {

        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            LiquamentumEntityComponents.ISOBSCURED.sync(player);
            if (LiquamentumEntityComponents.ISOBSCURED.get(player).getValue() && player != entity) {
                cir.setReturnValue(Component.literal(entity.getDisplayName().getString()).withStyle(ChatFormatting.OBFUSCATED));
            }
        }

    }


}
