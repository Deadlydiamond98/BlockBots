package net.deadlydiamond98.renderer.features.passive;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.models.BlockBotFarmHatModel;
import net.deadlydiamond98.models.BlockBotModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BlockBotFarmHatFeature<T extends BaseBlockBotEntity> extends FeatureRenderer<T, BlockBotModel<T>> {

    private final BlockBotFarmHatModel<T> model;

    public BlockBotFarmHatFeature(FeatureRendererContext<T, BlockBotModel<T>> context, EntityModelLoader loader) {
        super(context);
        this.model = new BlockBotFarmHatModel<>(loader.getModelPart(BlockBotFarmHatModel.LAYER_LOCATION));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        this.getContextModel().copyStateTo(this.model);
        matrices.translate(0, -0.01f, 0);
        this.model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(getTexture(entity)));
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    protected Identifier getTexture(T entity) {
        return new Identifier("textures/entity/villager/profession/farmer.png");
    }
}