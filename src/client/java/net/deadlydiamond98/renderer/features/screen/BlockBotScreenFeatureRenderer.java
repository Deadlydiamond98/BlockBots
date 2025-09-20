package net.deadlydiamond98.renderer.features.screen;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.deadlydiamond98.renderer.IBotScreenRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class BlockBotScreenFeatureRenderer<T extends BaseBlockBotEntity> extends FeatureRenderer<T, BlockBotModel<T>> implements IBotScreenRenderer {

    // TODO: THIS PROBABLY NEEDS CLEAN UP

    protected RenderLayer layer;
    private final boolean alpha;
    private final float frontOffset;
    private final boolean followPlayer;

    public BlockBotScreenFeatureRenderer(FeatureRendererContext<T, BlockBotModel<T>> context, RenderLayer layer, boolean alpha, float frontOffset) {
        this(context, layer, alpha, frontOffset, false);
    }

    protected BlockBotScreenFeatureRenderer(FeatureRendererContext<T, BlockBotModel<T>> context, RenderLayer layer, boolean alpha, float frontOffset, boolean followPlayer) {
        super(context);
        this.layer = layer;
        this.alpha = alpha;
        this.frontOffset = -frontOffset;
        this.followPlayer = followPlayer;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        matrices.push();

        double viewOffset = getViewingOffset(entity) * 0.03;

        matrices.translate(-0.5 + viewOffset, 0.625, -0.34375);
        matrices.translate(0.5, 0.5, 0);
        matrices.scale(0.5625f, 0.5625f, 1);
        matrices.translate(-0.5, -0.5, 0);

        VertexConsumer vertexConsumerNumber = vertexConsumers.getBuffer(getRenderLayer(entity));

        renderFace(vertexConsumerNumber, matrices, Math.min(255, Math.max(0, this.alpha ? entity.getScreenAlpha(entity) : 255)), 0, 0.5625f, 0, 0.5625f, this.frontOffset, light);

        matrices.pop();
    }

    private double getViewingOffset(T entity) {

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

    protected RenderLayer getRenderLayer(T entity) {
        return layer;
    }
}
