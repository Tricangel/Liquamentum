package bee.potions.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class LiquamentumBlockTagDatagen extends FabricTagProvider.BlockTagProvider {
    public LiquamentumBlockTagDatagen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {


//        valueLookupBuilder(LiquamentumTags.ROOTS_APPLY_WHEN_ON)
//                .forceAddTag(BlockTags.DIRT)
//                .add(Blocks.MOSS_BLOCK)
//                .add(Blocks.MOSS_CARPET)
//                .add(Blocks.PALE_MOSS_BLOCK)
//                .add(Blocks.PALE_MOSS_CARPET)
//                .add(Blocks.GLASS);
    }
}
