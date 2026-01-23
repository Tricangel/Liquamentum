package bee.potions.packet;

import bee.potions.Liquamentum;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public record PotionVialClickC2SPayload(int index) implements CustomPacketPayload {
    public static final Identifier POTION_VIAL_CLICK_PAYLOAD_ID =Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "potion_vial_click");
    public static final CustomPacketPayload.Type<PotionVialClickC2SPayload> ID = new Type<>(POTION_VIAL_CLICK_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, PotionVialClickC2SPayload> CODEC = StreamCodec.composite(ByteBufCodecs.INT, PotionVialClickC2SPayload::index, PotionVialClickC2SPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
