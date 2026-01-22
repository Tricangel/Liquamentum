package bee.potions;

import bee.potions.item.PotionVialTintSource;
import bee.potions.registry.LiquamentumComponents;
import bee.potions.registry.LiquamentumItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class LiquamentumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //why did i add this...
        // ITS USED NOW
        ItemTintSources.ID_MAPPER.put(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "color"), PotionVialTintSource.MAP_CODEC);


        /*ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipFlag, list) -> {
            if (!itemStack.is(LiquamentumItems.POTION_VIAL)) return;

            if (itemStack.get(LiquamentumComponents.CAN_BE_THROWN) == null) return;
            String tooltip = String.valueOf(itemStack.get(LiquamentumComponents.CAN_BE_THROWN));
            list.add(Component.translatable("item.liquamentum.potion_vial.tooltip", tooltip));
        });*/



    }
}
