package bee.potions.overlay;

import bee.potions.effect.Effect;
import bee.potions.effect.EffectInstance;
import bee.potions.effect.tick.CooldownOnTick;
import bee.potions.effect.tick.OnTick;
import bee.potions.registry.LiquamentumEntityComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ARGB;

public class EffectOverlay {

    public static void renderBlueBox(GuiGraphicsExtractor graphics) {

        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            int effectAmount = 0;
            for (EffectInstance effectInstance : LiquamentumEntityComponents.EFFECTS.get(Minecraft.getInstance().player).getEffectInstances()) {
                effectAmount++;
                int x = graphics.guiWidth();
                x -= 25 * effectAmount;

                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, Identifier.withDefaultNamespace("hud/effect_background"), x, 1, 24 ,24);

                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, getShouldTriggerSprite(effectInstance.getEffect()), x + 3, 4, 18, 18);
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, getEffectTriggerSprite(effectInstance.getEffect()), x + 3, 4, 18, 18);

                if (effectInstance.getEffect().getEffectTrigger().value() instanceof CooldownOnTick tick && tick.onCooldown()) {

                    int overlay = (int) ((tick.getDefaultCooldown() - tick.getCooldown()));

                    //graphics.fill(x + 3, 4, x + 21 - overlay, 22, ARGB.white(150));
                }

                graphics.text(Minecraft.getInstance().font, String.valueOf(effectInstance.getDuration() / 20), x, 20, ARGB.white(255));
                graphics.text(Minecraft.getInstance().font, String.valueOf(effectInstance.getEffect().canTrigger(player)), x, 30, ARGB.white(255));
                //wow now that the buggy code is gone is been fixed!! wow thats so smart me
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
