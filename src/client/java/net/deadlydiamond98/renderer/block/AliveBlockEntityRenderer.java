package net.deadlydiamond98.renderer.block;

import net.deadlydiamond98.common.blocks.blocks.power.GeneratorBotBlock;
import net.deadlydiamond98.common.blocks.entities.AbstractBotBlockEntity;
import net.deadlydiamond98.renderer.IBotScreenRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.*;

public class AliveBlockEntityRenderer<T extends AbstractBotBlockEntity> implements BlockEntityRenderer<T>, IBotScreenRenderer {
    protected static final float OFFSET = 0.0001f;

    public AliveBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vCon, int light, int overlay) {
        matrices.push();
        matrices.translate(0.5, 0.5, 0.5);
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees((getRotationDeg(entity).getHorizontal() * 90) + 180));
        matrices.translate(-0.5, -0.5, -0.5);
        renderScreen(entity, vCon, matrices, light);
        matrices.pop();
    }

    protected void renderScreen(T entity, VertexConsumerProvider vCon, MatrixStack matrices, int light) {
        float offset = renderFace(glowLayer(entity.getOnTexture(), vCon), matrices, 255, OFFSET * 2, light);

        matrices.push();
        if (entity.eyesFollowPlayer()) {
            double viewOffset = getViewingOffset(entity) * 0.045;
            matrices.translate(viewOffset, entity.eyePositionOffset(), 0);
        }
        offset = renderFace(glowLayer(entity.getEyeTexture(entity), vCon), matrices, 255, offset, light);
        matrices.pop();

        matrices.push();
        if (entity.showMouth(entity)) {
            animateMouth(entity, matrices);
            offset = renderFace(glowLayer(entity.getMouthTexture(entity), vCon), matrices, 255, offset, light);
        }
        matrices.pop();

        renderFace(regLayer(entity.getOffTexture(), vCon), matrices, entity.getScreenAlpha(entity), offset, light);
    }

    protected void animateMouth(T entity, MatrixStack matrices) {}

    public float renderFace(VertexConsumer vCon, MatrixStack matrices, int alpha, float offset, int light) {
        renderFace(vCon, matrices, alpha, 0, 1, 0, 1, -offset, light);
        return offset + OFFSET;
    }

    private double getViewingOffset(T entity) {

        Entity viewedEntity = MinecraftClient.getInstance().getCameraEntity();

        if (viewedEntity != null) {
            Vec3d vec3d = viewedEntity.getCameraPosVec(0);
            Vec3d vec3d2 = entity.getPos().toCenterPos();
            Vec3d vec3d3 = getRotationVector((getRotationDeg(entity).getHorizontal() * 90) + 180);
            vec3d3 = new Vec3d(vec3d3.x, 0.0, vec3d3.z);
            Vec3d vec3d4 = (new Vec3d(vec3d2.x - vec3d.x, 0.0, vec3d2.z - vec3d.z)).normalize().rotateY(1.5707964f);
            double e = vec3d3.dotProduct(vec3d4);
            return MathHelper.sqrt((float)Math.abs(e)) * 2 * (float)Math.signum(e);
        }
        return 0;
    }

    // Method pulled from entity class
    private Vec3d getRotationVector(float yaw) {
        float g = -yaw * 0.017453292F;
        float h = MathHelper.cos(g);
        float i = MathHelper.sin(g);
        float j = MathHelper.cos(0);
        float k = MathHelper.sin(0);
        return new Vec3d(i * j, -k, h * j);
    }

    protected Direction getRotationDeg(T entity) {
        BlockState blockState = entity.getCachedState();
        return blockState.get(GeneratorBotBlock.FACING);
    }
}
