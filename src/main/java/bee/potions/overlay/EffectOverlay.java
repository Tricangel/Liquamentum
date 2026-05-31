package bee.potions.overlay;

import bee.potions.Liquamentum;
import bee.potions.cca.EffectComponent;
import bee.potions.effect.Effect;
import bee.potions.registry.LiquamentumEntityComponents;
import bee.potions.registry.LiquamentumItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;

public class EffectOverlay {

    public static void renderBlueBox(GuiGraphicsExtractor graphics) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            EffectComponent effects = LiquamentumEntityComponents.EFFECTS.get(minecraft.player);
            if (effects.getEffects() == null || effects.getEffects().isEmpty()) return;
            Effect effect = effects.getEffects().getFirst();
            graphics.blitSprite(RenderPipelines.ANIMATE_SPRITE_BLIT, getOnTickSprite(effect), 10, 10, 16, 16);
            graphics.fakeItem(LiquamentumItems.POTION_VIAL.getDefaultInstance(), 10, 10);

        }
    }

    public static Identifier getOnTickSprite(final Effect effect) {
        return Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, effect.getOnTick().getName()).withPrefix("effect/");
    }

    public static Identifier getShouldTickSprite(final Effect effect) {
        return Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, effect.getShouldTick().getName()).withPrefix("effect/");
    }

}
