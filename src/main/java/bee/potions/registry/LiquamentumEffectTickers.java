package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.effect.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

public class LiquamentumEffectTickers {


    public static final ShouldTick TEST = registerShouldTick("test", new TickOnBlock("test", Blocks.WATER));

    public static final OnTick TEST2 = registerOnTick("test2", new ExplodeTick("test2"));


    private static ShouldTick registerShouldTick(String name, ShouldTick shouldTick) {
        return Registry.register(LiquamentumRegistries.SHOULD_TICK, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, name), shouldTick);
    }

    private static OnTick registerOnTick(String name, OnTick onTick) {
        return Registry.register(LiquamentumRegistries.ON_TICK, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, name), onTick);
    }


    public static void init() {}


}
