package net.deadlydiamond98.renderer.features.passive;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.RotationAxis;

public class BlockBotHeldItemFeature<T extends BaseBlockBotEntity> extends FeatureRenderer<T, BlockBotModel<T>> {

    private final HeldItemRenderer heldItemRenderer;

    public BlockBotHeldItemFeature(FeatureRendererContext context, HeldItemRenderer heldItemRenderer) {
        super(context);
        this.heldItemRenderer = heldItemRenderer;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack itemStack = entity.getMainHandStack();
        ItemStack itemStack2 = entity.getOffHandStack();
        if (!itemStack.isEmpty() || !itemStack2.isEmpty()) {
            matrices.push();
            if (this.getContextModel().child) {
                matrices.translate(0, 0.75, 0);
                matrices.scale(0.5f, 0.5f, 0.5f);
            }

            this.renderItem(entity, itemStack2, ModelTransformationMode.THIRD_PERSON_RIGHT_HAND, Arm.RIGHT, matrices, vertexConsumers, light);
            this.renderItem(entity, itemStack, ModelTransformationMode.THIRD_PERSON_LEFT_HAND, Arm.LEFT, matrices, vertexConsumers, light);
            matrices.pop();
        }
    }

    private void renderItem(T entity, ItemStack stack, ModelTransformationMode transformationMode, Arm arm, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (!stack.isEmpty()) {
            matrices.push();

            boolean bl = arm == Arm.LEFT;
            float offset = bl ? entity.getMainHeldItemAngle() : entity.getOffHeldItemAngle();
            matrices.translate(-0.43 * (bl ? 1 : -1), (bl ? 0.75 : 1.75), (bl ? 0.3 : -0.15));

            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90 - offset));

            if (bl) {
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
            }

            matrices.translate((bl ? -1 : 1) / 16.0, 0.125, -0.625);
            this.heldItemRenderer.renderItem(entity, stack, transformationMode, bl, matrices, vertexConsumers, light);
            matrices.pop();
        }
    }
}
