package bee.potions.ingredienttype;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Map;

public class RockEffects {

    private Map<Item, Holder<MobEffect>> positiveEffects = Map.ofEntries(
            Map.entry(Items.COBBLESTONE, rockPositiveEffects.get(0)),
            Map.entry(Items.STONE, rockPositiveEffects.get(1)),
            Map.entry(Items.ANDESITE, rockPositiveEffects.get(2)),
            Map.entry(Items.GRANITE, rockPositiveEffects.get(3)),
            Map.entry(Items.DIORITE, rockPositiveEffects.get(4)),
            Map.entry(Items.COBBLED_DEEPSLATE, rockPositiveEffects.get(5)),
            Map.entry(Items.DEEPSLATE, rockPositiveEffects.get(6)),
            Map.entry(Items.BLACKSTONE, rockPositiveEffects.get(7)),
            Map.entry(Items.CALCITE, rockPositiveEffects.get(8)),
            Map.entry(Items.TUFF, rockPositiveEffects.get(9)),
            Map.entry(Items.DRIPSTONE_BLOCK, rockPositiveEffects.get(10)),
            Map.entry(Items.SMOOTH_BASALT, rockPositiveEffects.get(11)),
            Map.entry(Items.COAL, rockPositiveEffects.get(12)),
            Map.entry(Items.RAW_COPPER, rockPositiveEffects.get(13)),
            Map.entry(Items.RAW_IRON, rockPositiveEffects.get(14)),
            Map.entry(Items.RAW_GOLD, rockPositiveEffects.get(15))
    );

    private Map<Item, Holder<MobEffect>> negativeEffects = Map.ofEntries(
            Map.entry(Items.COBBLESTONE, rockNegativeEffects.get(0)),
            Map.entry(Items.STONE, rockNegativeEffects.get(1)),
            Map.entry(Items.ANDESITE, rockNegativeEffects.get(2)),
            Map.entry(Items.GRANITE, rockNegativeEffects.get(3)),
            Map.entry(Items.DIORITE, rockNegativeEffects.get(4)),
            Map.entry(Items.COBBLED_DEEPSLATE, rockNegativeEffects.get(5)),
            Map.entry(Items.DEEPSLATE, rockNegativeEffects.get(6)),
            Map.entry(Items.BLACKSTONE, rockNegativeEffects.get(7)),
            Map.entry(Items.CALCITE, rockNegativeEffects.get(8)),
            Map.entry(Items.TUFF, rockNegativeEffects.get(9)),
            Map.entry(Items.DRIPSTONE_BLOCK, rockNegativeEffects.get(10)),
            Map.entry(Items.SMOOTH_BASALT, rockNegativeEffects.get(11)),
            Map.entry(Items.COAL, rockNegativeEffects.get(12)),
            Map.entry(Items.RAW_COPPER, rockNegativeEffects.get(13)),
            Map.entry(Items.RAW_IRON, rockNegativeEffects.get(14)),
            Map.entry(Items.RAW_GOLD, rockNegativeEffects.get(15))
    );

    public Holder<MobEffect> getPositiveEffect(ItemStack stack) {
        return positiveEffects.get(stack.getItem());
    }

    public Holder<MobEffect> getNegativeEffect(ItemStack stack) {
        return negativeEffects.get(stack.getItem());
    }

    public static List<Holder<MobEffect>> rockPositiveEffects = List.of(
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

    public static List<Holder<MobEffect>> getRockPositiveEffects() {
        return rockPositiveEffects;
    }

    public static List<Holder<MobEffect>> rockNegativeEffects = List.of(
            MobEffects.INSTANT_DAMAGE,
            MobEffects.MINING_FATIGUE
    );

    public static List<Holder<MobEffect>> getRockNegativeEffects() {
        return rockNegativeEffects;
    }

}
