package bee.potions.mixin.client;

import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin {

    @Inject(at = @At("HEAD"), method = "shouldRender(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/EquipmentSlot;)Z", cancellable = true)
    private static void cancelArmorRendering(ItemStack itemStack, EquipmentSlot equipmentSlot, CallbackInfoReturnable<Boolean> cir) {
        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (LiquamentumEntityComponents.ISOBSCURED.get(player).getValue()) {
                cir.setReturnValue(false);
            }
        }
    }


}
