package bee.potions.ingredienttype;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Map;

public class OreEffects {

    private Map<Item, Holder<MobEffect>> positiveEffects = Map.ofEntries(
            Map.entry(Items.FLINT, orePositiveEffects.get(0)),
            Map.entry(Items.COPPER_INGOT, orePositiveEffects.get(1)),
            Map.entry(Items.IRON_INGOT, orePositiveEffects.get(2)),
            Map.entry(Items.GOLD_INGOT, orePositiveEffects.get(3)),
            Map.entry(Items.EMERALD, orePositiveEffects.get(4)),
            Map.entry(Items.LAPIS_LAZULI, orePositiveEffects.get(5)),
            Map.entry(Items.DIAMOND, orePositiveEffects.get(6)),
            Map.entry(Items.QUARTZ, orePositiveEffects.get(7)),
            Map.entry(Items.AMETHYST_SHARD, orePositiveEffects.get(8))
    );

    private Map<Item, Holder<MobEffect>> negativeEffects = Map.ofEntries(
            Map.entry(Items.FLINT, oreNegativeEffects.get(0)),
            Map.entry(Items.COPPER_INGOT, oreNegativeEffects.get(1)),
            Map.entry(Items.IRON_INGOT, oreNegativeEffects.get(2)),
            Map.entry(Items.GOLD_INGOT, oreNegativeEffects.get(3)),
            Map.entry(Items.EMERALD, oreNegativeEffects.get(4)),
            Map.entry(Items.LAPIS_LAZULI, oreNegativeEffects.get(5)),
            Map.entry(Items.DIAMOND, oreNegativeEffects.get(6)),
            Map.entry(Items.QUARTZ, oreNegativeEffects.get(7)),
            Map.entry(Items.AMETHYST_SHARD, oreNegativeEffects.get(8))
    );

    public Holder<MobEffect> getPositiveEffect(ItemStack stack) {
        return positiveEffects.get(stack.getItem());
    }
    public Holder<MobEffect> getNegativeEffect(ItemStack stack) {
        return negativeEffects.get(stack.getItem());
    }

    public static List<Holder<MobEffect>> orePositiveEffects = List.of(
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

    public static List<Holder<MobEffect>> oreNegativeEffects = List.of(
            MobEffects.INSTANT_DAMAGE,
            MobEffects.MINING_FATIGUE
    );
}
