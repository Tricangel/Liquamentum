package bee.potions.data;

import bee.potions.Liquamentum;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public Holder<MobEffect> getRandomization(Item item) {
        for (PotionRandomization potionRandomization : potionRandomizations) {
            if (potionRandomization.item.equals(item)) return potionRandomization.effect;
        }
        return null;
    }

    public void addRandomization(Item item, Holder<MobEffect> effect, IngredientCategory category) {
        potionRandomizations.add(new PotionRandomization(item, effect, category));
        setDirty();
    }

    public Holder<MobEffect> randomize(Item item, List<Holder<MobEffect>> effects, IngredientCategory category) {
        if (this.isRandomized(item)) {
            return this.getRandomization(item);
        }
        Holder<MobEffect> effect = effects.get(new Random().nextInt(effects.size()));
        for (PotionRandomization potionRandomization : potionRandomizations) {
            if (potionRandomization.effect.equals(effect) && potionRandomization.category.equals(category)) {
                effect = effects.get(new Random().nextInt(effects.size()));
            }
        }

        addRandomization(item, effect, category);
        return effect;


    }





}
