package bee.potions.data;

import bee.potions.Liquamentum;
import bee.potions.effect.Effect;
import bee.potions.effect.EffectTrigger;
import bee.potions.effect.effectcondition.EffectCondition;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PotionRandomizationData extends SavedData {
    private final List<PotionRandomization> potionRandomizations = new ArrayList<>();

    public PotionRandomizationData() {
    }

    public static PotionRandomizationData getPotionNameData(MinecraftServer server) {
        ServerLevel level = server.getLevel(ServerLevel.OVERWORLD);
        return level.getDataStorage().computeIfAbsent(TYPE);
    }

    public static PotionRandomizationData unpack(List<PotionRandomization> list) {
        PotionRandomizationData potionRandomizationData = new PotionRandomizationData();
        potionRandomizationData.potionRandomizations.addAll(list);
        return potionRandomizationData;
    }

    public List<PotionRandomization> pack() {
        return new ArrayList<>(this.potionRandomizations);
    }

    private static final Codec<PotionRandomizationData> CODEC = PotionRandomization.CODEC.listOf().fieldOf("potion_randomization").codec().xmap(PotionRandomizationData::unpack, PotionRandomizationData::pack);
    public static final SavedDataType<PotionRandomizationData> TYPE = new SavedDataType<>(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "potion_randomization"), PotionRandomizationData::new, CODEC, null);


    public List<PotionRandomization> getPotionRandomizations() {
        return potionRandomizations;
    }

    public boolean isRandomized(Item item) {
        for (PotionRandomization potionRandomization : potionRandomizations) {
            if (potionRandomization.item.equals(item)) return true;
        }
        return false;
    }

    public Effect getRandomization(Item item) {
        for (PotionRandomization potionRandomization : potionRandomizations) {
            if (potionRandomization.item.equals(item)) return potionRandomization.effect;
        }
        return null;
    }

    public void addRandomization(Item item, Effect effect, IngredientCategory category) {
        potionRandomizations.add(new PotionRandomization(item, effect, category));
        setDirty();
    }

    public Effect randomize(Item item, List<Holder<EffectCondition>> shouldTriggers, List<Holder<EffectTrigger>> effectTriggers, IngredientCategory category) {
        if (this.isRandomized(item)) {
            return this.getRandomization(item);
        }
        Holder<EffectCondition> shouldTrigger = randomizeFirstItem(item, shouldTriggers, category);
        Holder<EffectTrigger> effectTrigger = randomizeSecondItem(item, effectTriggers, category);

        Effect effect = new Effect(shouldTrigger, effectTrigger);

        addRandomization(item, effect, category);
        return effect;


    }

    public Holder<EffectCondition> randomizeFirstItem(Item item, List<Holder<EffectCondition>> shouldTriggers, IngredientCategory category) {
        if (this.isRandomized(item)) {
            return this.getRandomization(item).getShouldTrigger();
        }

        Holder<EffectCondition> shouldTrigger = shouldTriggers.get(new Random().nextInt(shouldTriggers.size()));

        for (PotionRandomization potionRandomization : potionRandomizations) {
            if (potionRandomization.effect.getShouldTrigger().equals(shouldTrigger) && potionRandomization.category.equals(category)) {
                shouldTrigger = shouldTriggers.get(new Random().nextInt(shouldTriggers.size()));
            }
        }

        return shouldTrigger;

    }

    public Holder<EffectTrigger> randomizeSecondItem(Item item, List<Holder<EffectTrigger>> effectTriggers, IngredientCategory category) {
        if (this.isRandomized(item)) {
            return this.getRandomization(item).getEffectTrigger();
        }

        Holder<EffectTrigger> effectTrigger = effectTriggers.get(new Random().nextInt(effectTriggers.size()));

        for (PotionRandomization potionRandomization : potionRandomizations) {
            if (potionRandomization.effect.getEffectTrigger().equals(effectTrigger) && potionRandomization.category.equals(category)) {
                effectTrigger = effectTriggers.get(new Random().nextInt(effectTriggers.size()));
            }
        }

        return effectTrigger;

    }




}
