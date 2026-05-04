package bee.potions.mixin;

import bee.potions.Liquamentum;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mixin(PrimaryLevelData.class)
public abstract class Lingering {

    @Inject(at = @At("HEAD"), method = { "parse"}, cancellable = true)
    private static void hideAllEffectParticles(Dynamic<PrimaryLevelData> input, LevelSettings settings, PrimaryLevelData.SpecialWorldProperty specialWorldProperty, Lifecycle worldGenSettingsLifecycle, CallbackInfoReturnable<PrimaryLevelData> cir) {

        Liquamentum.POTION_NAME_MAP_CODEC.decode(input.get("potion_name_map").orElseEmptyMap()).ifSuccess(
                result -> {
                    DataResult<? extends Pair<?, ?>> result1 = Liquamentum.POTION_NAME_MAP_CODEC.decode((Dynamic) result);
                    if (result1.isSuccess()) {
                        Liquamentum.potionNameMap = (Map<List<Holder<MobEffect>>, String>) result1.getOrThrow().getFirst();
                    } else {
                        throw new RuntimeException("wawa");
                    }
                }
        );
    }

    @Inject(at = @At("HEAD"), method = { "setTagData"}, cancellable = true)
    private static void wawa(CompoundTag tag, UUID singlePlayerUUID, CallbackInfo ci) {
        CompoundTag compoundTag = new CompoundTag();

        Liquamentum.POTION_NAME_MAP_CODEC.encodeStart(NbtOps.INSTANCE, Liquamentum.potionNameMap).result().ifPresent(
                elem -> compoundTag.put("potion_name_map", (Tag) elem));
        tag.put("potionNameMap", compoundTag);

    }
}