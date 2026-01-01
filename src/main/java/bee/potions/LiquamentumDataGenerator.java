package bee.potions;

import bee.potions.datagen.LiquamentumBlockTagDatagen;
import bee.potions.datagen.LiquamentumItemTagDatagen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class LiquamentumDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(LiquamentumItemTagDatagen::new);
		pack.addProvider(LiquamentumBlockTagDatagen::new);
	}
}
