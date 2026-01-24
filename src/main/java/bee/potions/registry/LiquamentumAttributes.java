package bee.potions.registry;

import bee.potions.Liquamentum;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class LiquamentumAttributes {


    public static Holder<Attribute> DOUBLE_JUMP = registerAttribute("double_jump", 0, 0, 255, true);

    public static final AttachmentType<Integer> USED_JUMPS = AttachmentRegistry.create(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "used_jumps"));


    private static Holder<Attribute> registerAttribute(
            String name, double defaultValue, double minValue, double maxValue, boolean syncedWithClient
    ) {
        Identifier identifier = Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, name);
        Attribute entityAttribute = new RangedAttribute(
                identifier.toLanguageKey(),
                defaultValue,
                minValue,
                maxValue
        ).setSyncable(syncedWithClient);

        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, identifier, entityAttribute);
    }

    public static void init() {}

}
