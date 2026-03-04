package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.component.IsObscuredComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class LiquamentumEntityComponents implements EntityComponentInitializer {
    public static ComponentKey<IsObscuredComponent> ISOBSCURED =
            ComponentRegistry.getOrCreate(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "isobscured"), IsObscuredComponent.class);
    
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {

        entityComponentFactoryRegistry.registerFor(LivingEntity.class, ISOBSCURED, isObscured -> new IsObscuredComponent());

    }
}
