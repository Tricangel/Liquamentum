package bee.potions.data;

import bee.potions.Liquamentum;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PotionNameData extends SavedData {
    private final List<PotionName> potionNames = new ArrayList<>();
    //this took me about a month (of on and off work)
    //i am so fuckig happy rn

    public PotionNameData() {
    }

    public static PotionNameData getPotionNameData(MinecraftServer server) {
        ServerLevel level = server.getLevel(ServerLevel.OVERWORLD);
        return level.getDataStorage().computeIfAbsent(TYPE);
    }

    public static PotionNameData unpack(List<PotionName> list) {
        PotionNameData potionNameData = new PotionNameData();
        potionNameData.potionNames.addAll(list);
        return potionNameData;
    }

    public List<PotionName> pack() {
        return new ArrayList<>(this.potionNames);
    }

    private static final Codec<PotionNameData> CODEC = PotionName.CODEC.listOf().fieldOf("potion_names").codec().xmap(PotionNameData::unpack, PotionNameData::pack);
    public static final SavedDataType<PotionNameData> TYPE = new SavedDataType<>(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "potion_names"), PotionNameData::new, CODEC, null);


    public List<PotionName> getPotionNames() {
        return potionNames;
    }

    public void addName(Map<List<Holder<MobEffect>>, String> potionNameMap) {
        potionNameMap.forEach(this::addName);
    }

    public void addName(List<Holder<MobEffect>> effectList, String name) {
        this.potionNames.add(new PotionName(name, effectList));
        setDirty();
    }

    public void removeName(String name) {
        potionNames.forEach(potionName -> {
            if (potionName.name.equals(name)) {
                potionNames.remove(potionName);
            }
        });
        setDirty();
    }

    public String getName(List<Holder<MobEffect>> effectList) {
        for (PotionName potionName : potionNames) {
            if (potionName.getEffects().equals(effectList)) {
                return potionName.getName();
            }
        }
        return null;
    }

    public Boolean hasName(List<Holder<MobEffect>> effectList) {
        for (PotionName potionName : potionNames) {
            if (potionName.getEffects().equals(effectList)) {
                return true;
            }
        }
        return false;
    }





}
