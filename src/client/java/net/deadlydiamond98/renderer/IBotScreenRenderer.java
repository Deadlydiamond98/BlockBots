package net.deadlydiamond98.renderer;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public interface IBotScreenRenderer {

    default VertexConsumer glowLayer(Identifier texture, VertexConsumerProvider vCon) {
        return vCon.getBuffer(RenderLayer.getEyes(texture));
    }

    default VertexConsumer regLayer(Identifier texture, VertexConsumerProvider vCon) {
        return vCon.getBuffer(RenderLayer.getItemEntityTranslucentCull(texture));
    }

    default void renderFace(VertexConsumer vertexConsumer, MatrixStack matrices, int alpha, float minU, float maxU, float minV, float maxV, float z, int i) {
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();

        boolean flip = this instanceof BlockEntityRenderer<?>;
        int a = flip ? 1 : 0;
        int b = flip ? 0 : 1;

        vertex(vertexConsumer, matrix4f, matrix3f, a, b, z, alpha, minU, maxV, i);
        vertex(vertexConsumer, matrix4f, matrix3f, b, b, z, alpha, maxU, maxV, i);
        vertex(vertexConsumer, matrix4f, matrix3f, b, a, z, alpha, maxU, minV, i);
        vertex(vertexConsumer, matrix4f, matrix3f, a, a, z, alpha, minU, minV, i);
    }

    default void vertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, float z, int alpha, float u, float v, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, z)
                .color(255, 255, 255, alpha)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(normalMatrix, 0.0f, 1.0f, 0.0f)
                .next();
    }
}
