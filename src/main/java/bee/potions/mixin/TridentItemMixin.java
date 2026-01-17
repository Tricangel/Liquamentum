package bee.potions.mixin;

import bee.potions.registry.LiquamentumEffects;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.TridentItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TridentItem.class)
public abstract class TridentItemMixin {

    // combined "returntrue" to "isInWaterOrRain"
    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isInWaterOrRain()Z"), method = {"releaseUsing","use"})
    private boolean isInWaterOrRain(boolean original) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.hasEffect(LiquamentumEffects.RAIN_BLESSING)) return true;
        }
        return original;
    }

    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getTridentSpinAttackStrength(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)F"), method = "releaseUsing")
    private float lowerOverpowered(float original) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.hasEffect(LiquamentumEffects.RAIN_BLESSING) && !player.isInWaterOrRain()) return original / 1.6f;
        }
        return original;
    }

}
