package bee.potions.item;

import bee.potions.packet.PotionVialClickC2SPayload;
import bee.potions.registry.LiquamentumComponents;
import bee.potions.registry.LiquamentumItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.ItemSlotMouseAction;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class PotionVialMouseActions implements ItemSlotMouseAction {
    @Override
    public boolean matches(Slot slot) {
        return slot.getItem().is(LiquamentumItems.POTION_VIAL);
    }

    @Override
    public boolean onMouseScrolled(double d, double e, int i, ItemStack itemStack) {
        return false;
    }

    @Override
    public void onStopHovering(Slot slot) {

    }

    @Override
    public void onSlotClicked(Slot slot, ClickType clickType) {
        if (true) {
            ItemStack stack = slot.getItem();
            stack.set(LiquamentumComponents.CAN_BE_THROWN, !stack.get(LiquamentumComponents.CAN_BE_THROWN));
            PotionVialClickC2SPayload payload = new PotionVialClickC2SPayload(slot.index);
            ClientPlayNetworking.send(payload);
        }
    }
}
