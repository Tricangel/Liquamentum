package bee.potions.block.entity.client;

import bee.potions.block.entity.BrewingCauldronBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class BrewingCauldronBlockEntityRenderer implements BlockEntityRenderer<BrewingCauldronBlockEntity, BrewingCauldronBlockEntityRenderState> {

    @Override
    public BrewingCauldronBlockEntityRenderState createRenderState() {
        return new BrewingCauldronBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(BrewingCauldronBlockEntity blockEntity, BrewingCauldronBlockEntityRenderState blockEntityRenderState, float f, Vec3 vec3, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, blockEntityRenderState, f, vec3, crumblingOverlay);
        blockEntityRenderState.setIngredients(blockEntity.getIngredients());
    }

    @Override
    public void submit(BrewingCauldronBlockEntityRenderState blockEntityRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        MultiBufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        }
}
