package bee.potions.cca;

import bee.potions.effect.Effect;
import bee.potions.effect.EffectInstance;
import bee.potions.packet.EffectS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v8.component.CardinalComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EffectComponent implements CardinalComponent, AutoSyncedComponent {
    private final LivingEntity entity;
    private Map<Effect, EffectInstance> effects = new HashMap<>();

    public EffectComponent(LivingEntity entity) {
        this.entity = entity;
    }

    public Map<Effect, EffectInstance> getEffectMap() {
        return effects;
    }

    public List<EffectInstance> getEffectInstances() {
        List<EffectInstance> effectInstances = new ArrayList<>();
        effects.forEach((effect, effectInstance) -> effectInstances.add(effectInstance));
        return effectInstances;
    }

    public List<Effect> getEffects() {
        List<Effect> effectList = new ArrayList<>();
        effects.forEach((effect, effectInstance) -> effectList.add(effect));
        return effectList;
    }


    public void setEffects(Map<Effect, EffectInstance> effects) {
        this.effects = effects;
        if (entity instanceof ServerPlayer player) {
            ServerPlayNetworking.send(player, new EffectS2CPacket(effects.values().stream().toList()));
        }
    }

    public void addEffect(EffectInstance effectInstance) {

        if (effects.isEmpty()) effects.put(effectInstance.getEffect(), effectInstance);

        for (EffectInstance effectInstance1 : effects.values()) {
            if (effectInstance1.getEffect().equals(effectInstance.getEffect())) {
                effectInstance1.setDuration(effectInstance.getDuration());
            } else effects.put(effectInstance.getEffect(), effectInstance);
        }



        if (entity instanceof ServerPlayer player) {
            ServerPlayNetworking.send(player, new EffectS2CPacket(effects.values().stream().toList()));
        }
    }

    public void removeEffect(EffectInstance effect) {
        effects.remove(effect.getEffect(), effect);
        if (entity instanceof ServerPlayer player) {
            ServerPlayNetworking.send(player, new EffectS2CPacket(effects.values().stream().toList()));
        }
    }


    @Override
    public void readData(ValueInput readView) {
        List<EffectInstance> effectInstances;
        Map<Effect, EffectInstance> effectMap = new HashMap<>();
        if (readView.read("effects", EffectInstance.CODEC.listOf()).isPresent()) {
            effectInstances = readView.read("effects", EffectInstance.CODEC.listOf()).get();
            effectInstances.forEach(effectInstance -> effectMap.put(effectInstance.getEffect(), effectInstance));
        }
        effects = effectMap;

    }


    @Override
    public void writeData(ValueOutput writeView) {
        writeView.store("effects", EffectInstance.CODEC.listOf(), getEffectInstances().stream().toList());
    }

}
