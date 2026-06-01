package bee.potions;

import bee.potions.effect.Effect;
import bee.potions.effect.EffectInstance;
import bee.potions.item.PotionVialTintSource;
import bee.potions.packet.EffectS2CPacket;
import bee.potions.registry.LiquamentumComponents;
import bee.potions.registry.LiquamentumEffectComponents;
import bee.potions.registry.LiquamentumEntityComponents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.network.ClientboundPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiquamentumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //why did i add this...
        // ITS USED NOW
        ItemTintSources.ID_MAPPER.put(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "color"), PotionVialTintSource.MAP_CODEC);


        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipFlag, list) -> {



            if (itemStack.has(LiquamentumComponents.THROWABLE) && itemStack.get(LiquamentumComponents.THROWABLE)) {
                list.add(Component.translatable("item.liquamentum.potion_vial.tooltip"));
            }

        });

        ClientPlayNetworking.registerGlobalReceiver(EffectS2CPacket.TYPE, (packet, context) -> {
            List<EffectInstance> effectInstances = packet.effects();
            Map<Effect, EffectInstance> effectMap = new HashMap<>();

            effectInstances.forEach(effectInstance -> effectMap.put(effectInstance.getEffect(), effectInstance));
            LiquamentumEntityComponents.EFFECTS.get(context.player()).setEffects(effectMap);

        });




    }
}
