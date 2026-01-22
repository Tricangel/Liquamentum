package bee.potions.mixin;

import bee.potions.registry.LiquamentumComponents;
import bee.potions.registry.LiquamentumEffects;
import bee.potions.registry.LiquamentumItems;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
public abstract class InventoryMixin {

    @Inject(at = @At("HEAD"), method = "slotClicked", cancellable = true)
    private void Liquamentum$knockback(Slot slot, int i, int j, ClickType clickType, CallbackInfo ci) {
        if (clickType.id() == 0) {
            if (slot == null || !slot.hasItem()) return;
            ItemStack stack = slot.getItem();

            if (stack.is(LiquamentumItems.POTION_VIAL)) {
                if (Minecraft.getInstance().screen != null) {
                    //stack.set(LiquamentumComponents.CAN_BE_THROWN, !stack.get(LiquamentumComponents.CAN_BE_THROWN));
                   // ci.cancel();
                    // this is kil
                }
            }

        }
    }
}
