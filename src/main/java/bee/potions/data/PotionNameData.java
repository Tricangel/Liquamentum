package bee.potions.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PotionNameData extends SavedData {
    private final Map<List<Holder<MobEffect>>, String> potionNameMap = new HashMap<>();

    public PotionNameData() {

    }

    public static PotionNameData getPotionNameData(MinecraftServer server) {
        ServerLevel level = server.getLevel(ServerLevel.OVERWORLD);
        return new PotionNameData();
        //return level.getDataStorage().computeIfAbsent(TYPE);
    }

    public static PotionNameData unpack(Map<List<Holder<MobEffect>>, String> map) {
        PotionNameData potionNameData = new PotionNameData();
        map.forEach((effect, string) -> potionNameData.potionNameMap.put(effect, string));
        return potionNameData;
    }

    public Map<List<Holder<MobEffect>>, String> pack() {
        Map<List<Holder<MobEffect>>, String> map = new HashMap<>();
        //this is the line that breaks it
        //i believe i understand it now
        //i am not angry

        this.potionNameMap.forEach((effect, string) -> map.put(effect, string));
        return map;
    }

    private static final Codec<PotionNameData> CODEC =
            Codec.unboundedMap(MobEffect.CODEC.listOf(), Codec.STRING).fieldOf("potion_names").codec().xmap(PotionNameData::unpack, PotionNameData::pack);
    //public static final SavedDataType<PotionNameData> TYPE =new SavedDataType<>("potion_names", PotionNameData::new, CODEC, null);


    //this working evades me



    public Map<List<Holder<MobEffect>>, String> getPotionNameMap() {
        return potionNameMap;
    }

    public String getPotionName(List<Holder<MobEffect>> effects) {
        return potionNameMap.get(effects);
    }

    public void setPotionName(List<Holder<MobEffect>> effects, String potionName) {
        this.potionNameMap.put(effects, potionName);
        setDirty();
    }





}
