package bee.potions.ingredienttype;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NatureEffects {


    private Map<Item, Holder<MobEffect>> positiveEffects = Map.ofEntries(
            Map.entry(Items.OXEYE_DAISY, naturePositiveEffects.get(0)),
            Map.entry(Items.CORNFLOWER, naturePositiveEffects.get(1)),
            Map.entry(Items.LILY_OF_THE_VALLEY, naturePositiveEffects.get(2)),
            Map.entry(Items.TORCHFLOWER, naturePositiveEffects.get(3)),
            Map.entry(Items.CACTUS_FLOWER, naturePositiveEffects.get(4)),
            Map.entry(Items.SPORE_BLOSSOM, naturePositiveEffects.get(5)),
            Map.entry(Items.LILAC, naturePositiveEffects.get(6)),
            Map.entry(Items.SUNFLOWER, naturePositiveEffects.get(7)),
            Map.entry(Items.ROSE_BUSH, naturePositiveEffects.get(8)),
            Map.entry(Items.PEONY, naturePositiveEffects.get(9)),
            Map.entry(Items.PITCHER_PLANT, naturePositiveEffects.get(10)),
            Map.entry(Items.PINK_TULIP, naturePositiveEffects.get(11)),
            Map.entry(Items.ORANGE_TULIP, naturePositiveEffects.get(12)),
            Map.entry(Items.WHITE_TULIP, naturePositiveEffects.get(12)),
            Map.entry(Items.RED_TULIP, naturePositiveEffects.get(14)),
            Map.entry(Items.AZURE_BLUET, naturePositiveEffects.get(15)),
            Map.entry(Items.ALLIUM, naturePositiveEffects.get(16)),
            Map.entry(Items.BLUE_ORCHID, naturePositiveEffects.get(17)),
            Map.entry(Items.DANDELION, naturePositiveEffects.get(18)),
            Map.entry(Items.POPPY, naturePositiveEffects.get(19))
            );


    private Map<Item, Holder<MobEffect>> negativeEffects = Map.ofEntries(
            Map.entry(Items.OXEYE_DAISY, natureNegativeEffects.get(0)),
            Map.entry(Items.CORNFLOWER, natureNegativeEffects.get(1)),
            Map.entry(Items.LILY_OF_THE_VALLEY, natureNegativeEffects.get(2)),
            Map.entry(Items.TORCHFLOWER, natureNegativeEffects.get(3)),
            Map.entry(Items.CACTUS_FLOWER, natureNegativeEffects.get(4)),
            Map.entry(Items.SPORE_BLOSSOM, natureNegativeEffects.get(5)),
            Map.entry(Items.LILAC, natureNegativeEffects.get(6)),
            Map.entry(Items.SUNFLOWER, natureNegativeEffects.get(7)),
            Map.entry(Items.ROSE_BUSH, natureNegativeEffects.get(8)),
            Map.entry(Items.PEONY, natureNegativeEffects.get(9)),
            Map.entry(Items.PITCHER_PLANT, natureNegativeEffects.get(10)),
            Map.entry(Items.PINK_TULIP, natureNegativeEffects.get(11)),
            Map.entry(Items.ORANGE_TULIP, natureNegativeEffects.get(12)),
            Map.entry(Items.WHITE_TULIP, natureNegativeEffects.get(12)),
            Map.entry(Items.RED_TULIP, natureNegativeEffects.get(14)),
            Map.entry(Items.AZURE_BLUET, natureNegativeEffects.get(15)),
            Map.entry(Items.ALLIUM, natureNegativeEffects.get(16)),
            Map.entry(Items.BLUE_ORCHID, natureNegativeEffects.get(17)),
            Map.entry(Items.DANDELION, natureNegativeEffects.get(18)),
            Map.entry(Items.POPPY, natureNegativeEffects.get(19))
    );

    public Holder<MobEffect> getPositiveEffect(ItemStack stack, ServerLevel level) {
        Collections.shuffle(naturePositiveEffects, new Random(level.getSeed()));
        return positiveEffects.get(stack.getItem());
    }
    public Holder<MobEffect> getNegativeEffect(ItemStack stack, ServerLevel level) {
        Collections.shuffle(natureNegativeEffects, new Random(level.getSeed()));
        return negativeEffects.get(stack.getItem());
    }

    public static List<Holder<MobEffect>> naturePositiveEffects = List.of(
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.HASTE,
            MobEffects.DOLPHINS_GRACE
    );

    public static List<Holder<MobEffect>> natureNegativeEffects = List.of(
            MobEffects.NAUSEA,
            MobEffects.SLOWNESS,
            MobEffects.HUNGER
    );
}
