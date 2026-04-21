package bee.potions.data;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class test<T extends LivingEntityRenderState, M extends EntityModel<T>> extends RenderLayer<T, M> {
    public test(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, T state, float yRot, float xRot) {
        if (state.isInWater) {
            poseStack.pushPose();;
            M model = this.getParentModel();
            ModelPart modelPart = null;
            modelPart = model.root();
            modelPart.render(poseStack, (VertexConsumer) submitNodeCollector,
                    1, LivingEntityRenderer.getOverlayCoords(state, 0));
            poseStack.popPose();
        }
    }
}
