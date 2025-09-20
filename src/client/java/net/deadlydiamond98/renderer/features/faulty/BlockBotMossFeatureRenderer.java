package net.deadlydiamond98.renderer.features.faulty;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.deadlydiamond98.models.BlockBotMossModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BlockBotMossFeatureRenderer<T extends BaseBlockBotEntity> extends FeatureRenderer<T, BlockBotModel<T>> {

    private final BlockBotMossModel<T> model;

    public BlockBotMossFeatureRenderer(FeatureRendererContext<T, BlockBotModel<T>> context, EntityModelLoader loader) {
        super(context);
        this.model = new BlockBotMossModel<>(loader.getModelPart(BlockBotMossModel.LAYER_LOCATION));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        this.getContextModel().copyStateTo(this.model);
        this.model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(getTexture(entity)));
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    protected Identifier getTexture(T entity) {
        return new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/moss_carpet.png");
    }
}
