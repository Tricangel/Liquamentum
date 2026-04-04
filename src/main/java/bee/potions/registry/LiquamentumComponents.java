package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.item.PotionVialComponent;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class LiquamentumComponents {
    public static void init() {}

    public static final DataComponentType<Boolean> THROWABLE = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "throwable"),
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build()
    );

    public static final DataComponentType<PotionVialComponent> POTIONVIALCOMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "potionvialcomponent"),
            DataComponentType.<PotionVialComponent>builder().persistent(PotionVialComponent.CODEC).build()
    );

}
