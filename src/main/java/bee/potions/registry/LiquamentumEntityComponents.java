package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.cca.EffectComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class LiquamentumEntityComponents implements EntityComponentInitializer {


    public static ComponentKey<EffectComponent> EFFECTS =
            ComponentRegistry.getOrCreate(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "effects"), EffectComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {

        entityComponentFactoryRegistry.registerFor(LivingEntity.class, EFFECTS, EffectComponent::new);


    }
}
