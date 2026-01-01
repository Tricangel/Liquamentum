package bee.potions.datagen;

import bee.potions.registry.LiquamentumTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class LiquamentumBlockTagDatagen extends FabricTagProvider.BlockTagProvider {
    public LiquamentumBlockTagDatagen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {


        valueLookupBuilder(LiquamentumTags.ROOTS_APPLY_WHEN_ON)
                .forceAddTag(BlockTags.DIRT)
                .add(Blocks.MOSS_BLOCK)
                .add(Blocks.MOSS_CARPET)
                .add(Blocks.PALE_MOSS_BLOCK)
                .add(Blocks.PALE_MOSS_CARPET)
                .add(Blocks.GLASS);
    }
}
