package bee.potions.overlay;

import bee.potions.cca.EffectComponent;
import bee.potions.effect.Effect;
import bee.potions.effect.EffectInstance;
import bee.potions.registry.LiquamentumEffectComponents;
import bee.potions.registry.LiquamentumEntityComponents;
import bee.potions.registry.LiquamentumItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.font.FontTexture;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ARGB;

import java.util.List;

public class EffectOverlay {

    public static void renderBlueBox(GuiGraphicsExtractor graphics) {

        if (Minecraft.getInstance().player != null) {
            List<EffectInstance> effects = LiquamentumEntityComponents.EFFECTS.get(Minecraft.getInstance().player).getEffectInstances();
            for (int i = 0; i < effects.size(); i++) {
                EffectInstance effect = effects.get(i);
                int x = graphics.guiWidth();
                x -= 25 * (i + 1);

                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, Identifier.withDefaultNamespace("hud/effect_background"), x, 1, 24 ,24);

                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, getShouldTriggerSprite(effect.getEffect()), x + 3, 4, 18, 18);
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, getEffectTriggerSprite(effect.getEffect()), x + 3, 4, 18, 18);
                graphics.text(Minecraft.getInstance().font, String.valueOf(effect.getDuration() / 20), x, 25, ARGB.white(255));
                //bugs with this hmmmm
            }
        }


    }

    public static Identifier getEffectTriggerSprite(final Effect effect) {
        return effect.getEffectTrigger().unwrapKey().map(ResourceKey::identifier).map(id -> id.withPrefix("effect/")).orElse(MissingTextureAtlasSprite.getLocation());

    }

    public static Identifier getShouldTriggerSprite(final Effect effect) {
        return effect.getShouldTrigger().unwrapKey().map(ResourceKey::identifier).map(id -> id.withPrefix("effect/")).orElse(MissingTextureAtlasSprite.getLocation());
    }

}
