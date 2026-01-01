package bee.potions.registry;

import bee.potions.Liquamentum;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class LiquamentumTags {

    public static final TagKey<Item> BREWABLE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "brewable"));

    public static final TagKey<Item> ROCK_TYPE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "rock_type"));
    public static final TagKey<Item> ORE_TYPE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "ore_type"));
    public static final TagKey<Item> NATURE_TYPE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "nature_type"));
    public static final TagKey<Item> FIRE_TYPE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "fire_type"));
    public static final TagKey<Item> WATER_TYPE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "water_type"));
    public static final TagKey<Item> UNDEAD_TYPE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "undead_type"));
    public static final TagKey<Item> FLESH_TYPE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "flesh_type"));
    public static final TagKey<Item> SKY_TYPE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "sky_type"));
    public static final TagKey<Item> END_TYPE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "end_type"));
    public static final TagKey<Item> BOSS_TYPE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "boss_type"));

    public static final TagKey<Block> ROOTS_APPLY_WHEN_ON = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "roots_apply_when_on"));

    public static void init() {}

}
