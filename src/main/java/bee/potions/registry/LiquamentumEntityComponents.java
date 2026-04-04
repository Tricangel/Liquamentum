package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.cca.BooleanComponent;
import bee.potions.cca.VoidDraftComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class LiquamentumEntityComponents implements EntityComponentInitializer {

    public static ComponentKey<BooleanComponent> EXAMPLE =
            ComponentRegistry.getOrCreate(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "example"), BooleanComponent.class);

    public static ComponentKey<BooleanComponent> ISOBSCURED =
            ComponentRegistry.getOrCreate(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "isobscured"), BooleanComponent.class);

    public static ComponentKey<VoidDraftComponent> VOIDDRAFT =
            ComponentRegistry.getOrCreate(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "voiddraft"), VoidDraftComponent.class);

    public static ComponentKey<BooleanComponent> ISPETRIFIED =
            ComponentRegistry.getOrCreate(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "ispetrified"), BooleanComponent.class);
    
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.registerFor(LivingEntity.class, EXAMPLE, example -> new BooleanComponent("example"));
        entityComponentFactoryRegistry.registerFor(LivingEntity.class, ISOBSCURED, isObscured -> new BooleanComponent("isObscured"));
        entityComponentFactoryRegistry.registerFor(LivingEntity.class, ISPETRIFIED, isPetrified -> new BooleanComponent("isPetrified"));
        entityComponentFactoryRegistry.registerFor(LivingEntity.class, VOIDDRAFT, VoidDraftComponent::new);

    }
}
