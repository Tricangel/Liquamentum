package bee.potions.item;

import bee.potions.data.PotionNameData;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.Calendar;
import java.util.List;

public class ClickerItem extends Item {
    public ClickerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {

        if (level instanceof ServerLevel serverLevel) {

            MinecraftServer server = serverLevel.getServer();
            PotionNameData potionNameData = PotionNameData.getPotionNameData(server);
            if (player.isCrouching()) {
                player.displayClientMessage(Component.literal(potionNameData.getPotionNameMap().toString()), false);
            }
            potionNameData.setPotionName(List.of(MobEffects.HASTE), "wasa");

        }

        return super.use(level, player, interactionHand);
    }
}
