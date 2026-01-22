package bee.potions.registry;

import bee.potions.Liquamentum;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;

public class LiquamentumComponents {
    public static void init() {}

        public static final DataComponentType<Boolean> CAN_BE_THROWN = Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "can_be_thrown"),
                DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build()
        );

}
