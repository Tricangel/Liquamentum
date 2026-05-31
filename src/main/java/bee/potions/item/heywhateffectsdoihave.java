package bee.potions.item;

import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class heywhateffectsdoihave extends Item {
    public heywhateffectsdoihave(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            player.sendSystemMessage(Component.literal(String.valueOf(LiquamentumEntityComponents.EFFECTS.get(player).getEffects())));
        }
        return super.use(level, player, hand);
    }
}
