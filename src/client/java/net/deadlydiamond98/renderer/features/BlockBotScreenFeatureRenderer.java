package net.deadlydiamond98.renderer.features;

import net.deadlydiamond98.common.entity.MalfunctioningBlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class BlockBotScreenFeatureRenderer extends FeatureRenderer<MalfunctioningBlockBotEntity, BlockBotModel<MalfunctioningBlockBotEntity>> {

    protected RenderLayer layer;
    private final int alpha;
    private final float frontOffset;
    private final boolean followPlayer;

    public BlockBotScreenFeatureRenderer(FeatureRendererContext<MalfunctioningBlockBotEntity, BlockBotModel<MalfunctioningBlockBotEntity>> context, RenderLayer layer, int alpha, float frontOffset) {
        this(context, layer, alpha, frontOffset, false);
    }

    protected BlockBotScreenFeatureRenderer(FeatureRendererContext<MalfunctioningBlockBotEntity, BlockBotModel<MalfunctioningBlockBotEntity>> context, RenderLayer layer, int alpha, float frontOffset, boolean followPlayer) {
        super(context);
        this.layer = layer;
        this.alpha = alpha;
        this.frontOffset = -frontOffset;
        this.followPlayer = followPlayer;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, MalfunctioningBlockBotEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        matrices.push();

        double ViewOffset = getViewingOffset(entity) * 0.03;

        matrices.translate(-0.5 + ViewOffset, 0.625, -0.34375);
        matrices.translate(0.5, 0.5, 0);
        matrices.scale(0.5625f, 0.5625f, 1);
        matrices.translate(-0.5, -0.5, 0);

        VertexConsumer vertexConsumerNumber = vertexConsumers.getBuffer(getRenderLayer(entity));
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();

        renderFace(vertexConsumerNumber, matrix4f, matrix3f, this.alpha, 0, 0.5625f, 0, 0.5625f, this.frontOffset, light);

        matrices.pop();
    }

    private double getViewingOffset(MalfunctioningBlockBotEntity entity) {

        Entity viewedEntity = MinecraftClient.getInstance().getCameraEntity();

        if (viewedEntity != null && this.followPlayer) {
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

    private static void renderFace(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, int alpha, float minU, float maxU, float minV, float maxV, float z, int i) {
        vertex(vertexConsumer, positionMatrix, normalMatrix, 0, 1, z, alpha, minU, maxV, i);
        vertex(vertexConsumer, positionMatrix, normalMatrix, 1, 1, z, alpha, maxU, maxV, i);
        vertex(vertexConsumer, positionMatrix, normalMatrix, 1, 0, z, alpha, maxU, minV, i);
        vertex(vertexConsumer, positionMatrix, normalMatrix, 0, 0, z, alpha, minU, minV, i);
    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, float z, int alpha, float u, float v, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, z)
                .color(255, 255, 255, alpha)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(normalMatrix, 0.0f, 1.0f, 0.0f)
                .next();
    }

    protected RenderLayer getRenderLayer(MalfunctioningBlockBotEntity entity) {
        return layer;
    }
}
