package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.cca.IsObscuredComponent;
import bee.potions.cca.PetrifiedComponent;
import bee.potions.cca.VoidDraftComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class LiquamentumEntityComponents implements EntityComponentInitializer {
    public static ComponentKey<IsObscuredComponent> ISOBSCURED =
            ComponentRegistry.getOrCreate(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "isobscured"), IsObscuredComponent.class);

    public static ComponentKey<VoidDraftComponent> VOIDDRAFT =
            ComponentRegistry.getOrCreate(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "voiddraft"), VoidDraftComponent.class);

    public static ComponentKey<PetrifiedComponent> ISPETRIFIED =
            ComponentRegistry.getOrCreate(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "ispetrified"), PetrifiedComponent.class);
    
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {

        entityComponentFactoryRegistry.registerFor(LivingEntity.class, ISOBSCURED, isObscured -> new IsObscuredComponent());
        entityComponentFactoryRegistry.registerFor(LivingEntity.class, ISPETRIFIED, isPetrified -> new PetrifiedComponent());
        entityComponentFactoryRegistry.registerFor(LivingEntity.class, VOIDDRAFT, VoidDraftComponent::new);

    }
}
