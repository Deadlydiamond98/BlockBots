package net.deadlydiamond98.renderer.features.passive;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.math.RotationAxis;

public class BlockBotLittleChestFeature<T extends BaseBlockBotEntity> extends FeatureRenderer<T, BlockBotModel<T>> {

    private final HeldItemRenderer heldItemRenderer;

    public BlockBotLittleChestFeature(FeatureRendererContext context, HeldItemRenderer heldItemRenderer) {
        super(context);
        this.heldItemRenderer = heldItemRenderer;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        matrices.push();
        if (this.getContextModel().child) {
            matrices.translate(0, 0.75, 0);
            matrices.scale(0.5f, 0.5f, 0.5f);
        }

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));

        matrices.translate(0, -0.53, 0);
        matrices.scale(0.5f, 0.5f, 0.5f);

        this.heldItemRenderer.renderItem(entity, new ItemStack(Blocks.CHEST), ModelTransformationMode.HEAD, true, matrices, vertexConsumers, light);

        matrices.pop();
    }

    private void renderItem(T entity, ItemStack stack, ModelTransformationMode transformationMode, Arm arm, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (!stack.isEmpty()) {
            matrices.push();

//            boolean bl = arm == Arm.LEFT;
//            float offset = bl ? entity.getMainHeldItemAngle() : entity.getOffHeldItemAngle();
//            matrices.translate(-0.43 * (bl ? 1 : -1), (bl ? 0.75 : 1.75), (bl ? 0.3 : -0.15));
//
//            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90 - offset));
//
//            if (bl) {
//                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
//            }
//
//            matrices.translate((bl ? -1 : 1) / 16.0, 0.125, -0.625);

            this.heldItemRenderer.renderItem(entity, stack, ModelTransformationMode.GUI, true, matrices, vertexConsumers, light);
            matrices.pop();
        }
    }
}
