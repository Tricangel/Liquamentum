package bee.potions.packet;

import bee.potions.Liquamentum;
import bee.potions.effect.Effect;
import bee.potions.effect.EffectInstance;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.Map;

public record EffectS2CPacket(List<EffectInstance> effects) implements CustomPacketPayload {
    public static final Identifier EFFECTS_PACKET_ID = Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "effects");
    public static final Type<EffectS2CPacket> TYPE = new Type<>(EFFECTS_PACKET_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, EffectS2CPacket> CODEC =
            StreamCodec.composite(ByteBufCodecs.fromCodec(EffectInstance.CODEC.listOf()), EffectS2CPacket::effects, EffectS2CPacket::new);
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
