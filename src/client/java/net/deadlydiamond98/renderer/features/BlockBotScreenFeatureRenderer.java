package net.deadlydiamond98.renderer.features;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.deadlydiamond98.renderer.IBotScreenRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class BlockBotScreenFeatureRenderer<T extends BaseBlockBotEntity> extends FeatureRenderer<T, BlockBotModel<T>> implements IBotScreenRenderer {

    protected static final float OFFSET = 0.0001f;
    
    public BlockBotScreenFeatureRenderer(FeatureRendererContext<T, BlockBotModel<T>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vCon, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        matrices.push();

        matrices.translate(-0.5, 0.625, -0.34375);
        matrices.translate(0.5, 0.5, 0.5);
        matrices.scale(0.5625f, 0.5625f, 1);
        matrices.translate(-0.5, -0.5, -0.5);
        renderScreen(entity, vCon, matrices, light);
        matrices.pop();
    }

    private void renderScreen(T entity, VertexConsumerProvider vCon, MatrixStack matrices, int light) {
        float offset = renderFace(glowLayer(entity.getOnTexture(), vCon), matrices, 255, OFFSET * 2, light);

        matrices.push();
        if (entity.eyesFollowPlayer(entity)) {
            double viewOffset = getViewingOffset(entity) * 0.03;
            matrices.translate(viewOffset, 0, 0);
        }
        offset = renderFace(glowLayer(entity.getEyeTexture(entity), vCon), matrices, 255, offset, light, 0.21875f, 0.15625f);
        matrices.pop();

        matrices.push();
        if (entity.showMouth(entity)) {
            animateMouth(entity, matrices);
            offset = renderFace(glowLayer(entity.getMouthTexture(entity), vCon), matrices, 255, offset, light, 0.21875f, 0.15625f);
        }
        matrices.pop();

        renderFace(regLayer(entity.getOffTexture(), vCon), matrices, entity.getScreenAlpha(entity), offset, light);
    }

    protected void animateMouth(T entity, MatrixStack matrices) {}

    public float renderFace(VertexConsumer vCon, MatrixStack matrices, int alpha, float offset, int light) {
        return renderFace(vCon, matrices, alpha, offset, light, 0, 0);
    }

    public float renderFace(VertexConsumer vCon, MatrixStack matrices, int alpha, float offset, int light, float x, float y) {
        renderFace(vCon, matrices, alpha, x, 0.5625f + x, y, 0.5625f + y, -offset, light);
        return offset + OFFSET;
    }

    private double getViewingOffset(T entity) {
        Entity viewedEntity = MinecraftClient.getInstance().getCameraEntity();
        
        if (viewedEntity != null) {
            Vec3d vec3d = viewedEntity.getCameraPosVec(0);
            Vec3d vec3d2 = entity.getCameraPosVec(0);
            Vec3d vec3d3 = entity.getRotationVec(0);
            vec3d3 = new Vec3d(vec3d3.x, 0.0, vec3d3.z);
            Vec3d vec3d4 = (new Vec3d(vec3d2.x - vec3d.x, 0.0, vec3d2.z - vec3d.z)).normalize().rotateY(1.5707964f);
            double e = vec3d3.dotProduct(vec3d4);
            return MathHelper.sqrt((float)Math.abs(e)) * 2 * (float)Math.signum(e);
        }
        return 0;
    }
}
