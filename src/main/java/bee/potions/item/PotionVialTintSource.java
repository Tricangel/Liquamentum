package bee.potions.item;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public record PotionVialTintSource(int color) implements ItemTintSource {

    public static final MapCodec<PotionVialTintSource> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ExtraCodecs.RGB_COLOR_CODEC.fieldOf("color").forGetter(PotionVialTintSource::color)
            ).apply(instance, PotionVialTintSource::new)
    );

    @Override
    public int calculate(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity) {
        if (itemStack.get(DataComponents.POTION_CONTENTS) != null) {

            //whats the difference between this and the vanilla method, uhhhh i can mess with this one :3
            return itemStack.get(DataComponents.POTION_CONTENTS).getColor();
        }
        return 0;
    }

    @Override
    public MapCodec<? extends ItemTintSource> type() {
        return MAP_CODEC;
    }
}
