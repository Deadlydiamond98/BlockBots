package net.deadlydiamond98.renderer.block;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.blocks.entities.ChestMarkerBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class ChestMarkerBlockEntityRenderer implements BlockEntityRenderer<ChestMarkerBlockEntity> {

    private static final Identifier TEXTURE = new Identifier(BlockBots.MOD_ID, "textures/block/chest_marker.png");
    private static int time = 0;

    public ChestMarkerBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(ChestMarkerBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        Camera camera = MinecraftClient.getInstance().getEntityRenderDispatcher().camera;

        matrices.translate(0.5, 0, 0.5);
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(camera.getYaw()));
        matrices.translate(-0.5, Math.sin(time++ * 0.005) * 0.1, 0);

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucentCull(TEXTURE));
        renderFace(vertexConsumer, matrices, LightmapTextureManager.MAX_SKY_LIGHT_COORDINATE);
    }

    private void renderFace(VertexConsumer vertexConsumer, MatrixStack matrices, int i) {
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();

        vertex(vertexConsumer, matrix4f, matrix3f, 1, 0, 0, 255, 0, 1, i);
        vertex(vertexConsumer, matrix4f, matrix3f, 0, 0, 0, 255, 1, 1, i);
        vertex(vertexConsumer, matrix4f, matrix3f, 0, 1, 0, 255, 1, 0, i);
        vertex(vertexConsumer, matrix4f, matrix3f, 1, 1, 0, 255, 0, 0, i);
    }

    private void vertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, float z, int alpha, float u, float v, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, z)
                .color(255, 255, 255, alpha)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(normalMatrix, 0.0f, 1.0f, 0.0f)
                .next();
    }
}
