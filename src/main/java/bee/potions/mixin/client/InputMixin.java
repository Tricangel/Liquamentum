package bee.potions.mixin.client;

import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Input;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Input.class)
public class InputMixin {

    //me and my fuckass mixins

/*
    @Inject(at = @At("HEAD"), method = "forward", cancellable = true)
    private static void forward(CallbackInfoReturnable<Boolean> cir) {
        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            MovementComponent component = LiquamentumEntityComponents.MOVEMENT.get(player);
            if (!component.forward(player)) cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "backward", cancellable = true)
    private static void backward(CallbackInfoReturnable<Boolean> cir) {
        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            MovementComponent component = LiquamentumEntityComponents.MOVEMENT.get(player);
            if (!component.backward(player)) cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "left", cancellable = true)
    private static void left(CallbackInfoReturnable<Boolean> cir) {
        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            MovementComponent component = LiquamentumEntityComponents.MOVEMENT.get(player);
            if (!component.left(player)) cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "right", cancellable = true)
    private static void right(CallbackInfoReturnable<Boolean> cir) {
        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            MovementComponent component = LiquamentumEntityComponents.MOVEMENT.get(player);
            if (!component.right(player)) cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "jump", cancellable = true)
    private static void jump(CallbackInfoReturnable<Boolean> cir) {
        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            MovementComponent component = LiquamentumEntityComponents.MOVEMENT.get(player);
            if (!component.jump(player)) cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "shift", cancellable = true)
    private static void shift(CallbackInfoReturnable<Boolean> cir) {
        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            MovementComponent component = LiquamentumEntityComponents.MOVEMENT.get(player);
            if (!component.shift(player)) cir.setReturnValue(false);
        }
    }
    */
}
