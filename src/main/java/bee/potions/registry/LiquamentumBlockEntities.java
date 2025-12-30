package bee.potions.registry;

import bee.potions.Liquamentum;
import bee.potions.block.entity.BrewingCauldronBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class LiquamentumBlockEntities {

    public static final BlockEntityType<BrewingCauldronBlockEntity> BREWING_CAULDRON_BLOCK_ENTITY =
            register("brewing_cauldron", BrewingCauldronBlockEntity::new, LiquamentumBlocks.BREWING_CAULDRON);

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void init(){}

}
