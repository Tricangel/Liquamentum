package bee.potions.registry;

import bee.potions.Liquamentum;
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

}
