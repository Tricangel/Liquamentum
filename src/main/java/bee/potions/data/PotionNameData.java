package bee.potions.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import net.minecraft.world.level.storage.CommandStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PotionNameData extends SavedData {
    private final Map<List<Holder<MobEffect>>, String> potionNameMap = new Object2ObjectOpenHashMap();

    public PotionNameData() {

    }

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
