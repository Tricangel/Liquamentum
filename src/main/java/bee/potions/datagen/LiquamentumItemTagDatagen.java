package bee.potions.datagen;

import bee.potions.registry.LiquamentumTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class LiquamentumItemTagDatagen extends FabricTagProvider.ItemTagProvider {

    public LiquamentumItemTagDatagen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        valueLookupBuilder(LiquamentumTags.BURNS_IN_HANDS)
                .forceAddTag(ItemTags.WOODEN_BUTTONS)
                .forceAddTag(ItemTags.WOODEN_DOORS)
                .forceAddTag(ItemTags.WOODEN_FENCES)
                .forceAddTag(ItemTags.WOODEN_SHELVES)
                .forceAddTag(ItemTags.WOODEN_SLABS)
                .forceAddTag(ItemTags.WOODEN_PRESSURE_PLATES)
                .forceAddTag(ItemTags.WOODEN_STAIRS)
                .forceAddTag(ItemTags.WOODEN_TOOL_MATERIALS)
                .forceAddTag(ItemTags.WOODEN_TRAPDOORS)
                .forceAddTag(ItemTags.LOGS_THAT_BURN)
                .forceAddTag(ItemTags.LEAVES);



        valueLookupBuilder(LiquamentumTags.BASE_INGREDIENT)
                .forceAddTag(LiquamentumTags.FLOWER_TYPE)
                .add(Items.NETHER_WART)
                .add(Items.CRIMSON_FUNGUS)
                .add(Items.WARPED_FUNGUS)
                .add(Items.DRAGON_BREATH);



        valueLookupBuilder(LiquamentumTags.ROCK_TYPE)
                .forceAddTag(ItemTags.STONE_CRAFTING_MATERIALS)
                .forceAddTag(ItemTags.COAL_ORES)
                .forceAddTag(ItemTags.COPPER_ORES)
                .forceAddTag(ItemTags.DIAMOND_ORES)
                .forceAddTag(ItemTags.GOLD_ORES)
                .forceAddTag(ItemTags.EMERALD_ORES)
                .forceAddTag(ItemTags.IRON_ORES)
                .forceAddTag(ItemTags.LAPIS_ORES)
                .forceAddTag(ItemTags.REDSTONE_ORES)
                .add(Items.ANDESITE)
                .add(Items.GRANITE)
                .add(Items.DIORITE)
                .add(Items.DEEPSLATE)
                .add(Items.STONE)
                .add(Items.COAL)
                .add(Items.CHARCOAL);

        valueLookupBuilder(LiquamentumTags.ORE_TYPE)
                .add(Items.FLINT)
                .add(Items.IRON_INGOT)
                .add(Items.DIAMOND)
                .add(Items.GOLD_INGOT)
                .add(Items.EMERALD)
                .add(Items.LAPIS_LAZULI)
                .add(Items.QUARTZ)
                .add(Items.AMETHYST_SHARD);

        valueLookupBuilder(LiquamentumTags.FLOWER_TYPE)
                .add(Items.OXEYE_DAISY)
                .add(Items.CORNFLOWER)
                .add(Items.LILY_OF_THE_VALLEY)
                .add(Items.TORCHFLOWER)
                .add(Items.CACTUS_FLOWER)
                .add(Items.SPORE_BLOSSOM)
                .add(Items.LILAC)
                .add(Items.SUNFLOWER)
                .add(Items.ROSE_BUSH)
                .add(Items.PEONY)
                .add(Items.PITCHER_PLANT)
                .add(Items.PINK_TULIP)
                .add(Items.WHITE_TULIP)
                .add(Items.ORANGE_TULIP)
                .add(Items.RED_TULIP)
                .add(Items.AZURE_BLUET)
                .add(Items.ALLIUM)
                .add(Items.BLUE_ORCHID)
                .add(Items.DANDELION)
                .add(Items.POPPY);

        valueLookupBuilder(LiquamentumTags.CROP_TYPE)
                .add(Items.WHEAT)
                .add(Items.POTATO)
                .add(Items.CARROT)
                .add(Items.APPLE)
                .add(Items.SWEET_BERRIES)
                .add(Items.GLOW_BERRIES)
                .add(Items.SUGAR_CANE)
                .add(Items.MELON)
                .add(Items.PUMPKIN)
                .add(Items.BROWN_MUSHROOM)
                .add(Items.RED_MUSHROOM);

        valueLookupBuilder(LiquamentumTags.WOOD_TYPE)
                .forceAddTag(ItemTags.LOGS);

        valueLookupBuilder(LiquamentumTags.FIRE_TYPE)
                .add(Items.MAGMA_BLOCK)
                .add(Items.MAGMA_CREAM)
                .add(Items.FIRE_CHARGE)
                .add(Items.BLAZE_POWDER)
                .add(Items.BLAZE_ROD);

        valueLookupBuilder(LiquamentumTags.WATER_TYPE)
                .add(Items.HEART_OF_THE_SEA)
                .add(Items.SNOW)
                .add(Items.BLUE_ICE)
                .add(Items.NAUTILUS_SHELL)
                .add(Items.TURTLE_SCUTE)
                .add(Items.PRISMARINE_SHARD);

        valueLookupBuilder(LiquamentumTags.UNDEAD_TYPE)
                .forceAddTag(ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .add(Items.SKELETON_SKULL)
                .add(Items.PLAYER_HEAD)
                .add(Items.ZOMBIE_HEAD)
                .add(Items.PIGLIN_HEAD)
                .add(Items.CREEPER_HEAD)
                .add(Items.BONE_BLOCK)
                .add(Items.BONE)
                .add(Items.ANCIENT_DEBRIS)
                .add(Items.ECHO_SHARD);

        valueLookupBuilder(LiquamentumTags.FLESH_TYPE)
                .forceAddTag(ItemTags.MEAT)
                .forceAddTag(ItemTags.EGGS)
                .add(Items.SNIFFER_EGG)
                .add(Items.TURTLE_EGG);

        valueLookupBuilder(LiquamentumTags.SKY_TYPE)
                .add(Items.BREEZE_ROD)
                .add(Items.WIND_CHARGE)
                .add(Items.FEATHER)
                .add(Items.PHANTOM_MEMBRANE)
                .add(Items.GHAST_TEAR)
                .add(Items.DRIED_GHAST);

        valueLookupBuilder(LiquamentumTags.END_TYPE)
                .add(Items.ENDER_PEARL)
                .add(Items.ENDER_EYE)
                .add(Items.END_STONE)
                .add(Items.CHORUS_FLOWER)
                .add(Items.CHORUS_FRUIT)
                .add(Items.SHULKER_SHELL)
                .add(Items.DRAGON_BREATH);


        valueLookupBuilder(LiquamentumTags.BOSS_TYPE)
                .add(Items.NETHER_STAR)
                .add(Items.ELYTRA)
                .add(Items.DRAGON_HEAD)
                .add(Items.DRAGON_EGG)
                .add(Items.WITHER_SKELETON_SKULL)
                .add(Items.TOTEM_OF_UNDYING);

        valueLookupBuilder(LiquamentumTags.BREWABLE)
                .forceAddTag(LiquamentumTags.ROCK_TYPE)
                .forceAddTag(LiquamentumTags.ORE_TYPE)
                .forceAddTag(LiquamentumTags.FLOWER_TYPE)
                .forceAddTag(LiquamentumTags.CROP_TYPE)
                .forceAddTag(LiquamentumTags.WOOD_TYPE)
                .forceAddTag(LiquamentumTags.FIRE_TYPE)
                .forceAddTag(LiquamentumTags.WATER_TYPE)
                .forceAddTag(LiquamentumTags.UNDEAD_TYPE)
                .forceAddTag(LiquamentumTags.FLESH_TYPE)
                .forceAddTag(LiquamentumTags.SKY_TYPE)
                .forceAddTag(LiquamentumTags.END_TYPE)
                .forceAddTag(LiquamentumTags.BOSS_TYPE)
                .forceAddTag(LiquamentumTags.BASE_INGREDIENT);



    }
}
