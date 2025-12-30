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

        valueLookupBuilder(LiquamentumTags.NATURE_TYPE)
                .forceAddTag(ItemTags.FLOWERS)
                .forceAddTag(ItemTags.SAPLINGS)
                .forceAddTag(ItemTags.LOGS)
                .add(Items.POTATO)
                .add(Items.CARROT)
                .add(Items.CACTUS)
                .add(Items.WHEAT)
                .add(Items.BEETROOT)
                .add(Items.COCOA_BEANS)
                .add(Items.PITCHER_POD)
                .add(Items.MELON)
                .add(Items.PUMPKIN)
                .add(Items.SUGAR_CANE)
                .add(Items.POISONOUS_POTATO)
                .add(Items.GLOW_BERRIES)
                .add(Items.SWEET_BERRIES)
                .add(Items.BROWN_MUSHROOM)
                .add(Items.RED_MUSHROOM);

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
                .forceAddTag(LiquamentumTags.NATURE_TYPE)
                .forceAddTag(LiquamentumTags.FIRE_TYPE)
                .forceAddTag(LiquamentumTags.WATER_TYPE)
                .forceAddTag(LiquamentumTags.UNDEAD_TYPE)
                .forceAddTag(LiquamentumTags.FLESH_TYPE)
                .forceAddTag(LiquamentumTags.SKY_TYPE)
                .forceAddTag(LiquamentumTags.END_TYPE)
                .forceAddTag(LiquamentumTags.BOSS_TYPE);



    }
}
