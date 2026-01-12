package bee.potions.mixin.client;

import bee.potions.Liquamentum;
import bee.potions.registry.LiquamentumEffects;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AvatarRenderer.class)
public abstract class ModelMixin {

	@Inject(at = @At("HEAD"), method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)Lnet/minecraft/resources/Identifier;", cancellable = true)
	private void wawa(AvatarRenderState avatarRenderState, CallbackInfoReturnable<Identifier> cir) {
		if (Minecraft.getInstance().player != null) {
			if (Liquamentum.isFrozen(Minecraft.getInstance()) && Minecraft.getInstance().player.hasEffect(LiquamentumEffects.PETRIFIED)) {
				cir.setReturnValue(Identifier.fromNamespaceAndPath(Liquamentum.MOD_ID, "textures/misc/petrified_player.png"));

			}
		}

	}

	@Inject(at = @At("HEAD"), method = "scale(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;)V", cancellable = true)
	private void norenderitems(AvatarRenderState avatarRenderState, PoseStack poseStack, CallbackInfo ci) {
		if (Minecraft.getInstance().player != null) {
			LocalPlayer player = Minecraft.getInstance().player;
			if (player.hasEffect(LiquamentumEffects.ROCK_APPEARANCE)) {
				Level level = player.level();
				if (level.getBlockState(player.getOnPos()).is(BlockTags.BASE_STONE_OVERWORLD)) {
					player.setInvisible(true);
					poseStack.scale(0, 0, 0);
				}

			}
		}

	}




}