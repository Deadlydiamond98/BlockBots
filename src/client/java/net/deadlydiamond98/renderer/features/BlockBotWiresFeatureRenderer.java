package net.deadlydiamond98.renderer.features;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.MalfunctioningBlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.deadlydiamond98.models.BlockBotWiresModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BlockBotWiresFeatureRenderer extends FeatureRenderer<MalfunctioningBlockBotEntity, BlockBotModel<MalfunctioningBlockBotEntity>> {

    private final BlockBotWiresModel<MalfunctioningBlockBotEntity> model;

    public BlockBotWiresFeatureRenderer(FeatureRendererContext<MalfunctioningBlockBotEntity, BlockBotModel<MalfunctioningBlockBotEntity>> context, EntityModelLoader loader) {
        super(context);
        this.model = new BlockBotWiresModel<>(loader.getModelPart(BlockBotWiresModel.LAYER_LOCATION));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, MalfunctioningBlockBotEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        this.getContextModel().copyStateTo(this.model);
        this.model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(getTexture(entity)));
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    protected Identifier getTexture(MalfunctioningBlockBotEntity entity) {
        return new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/wire/wires_" + entity.getWireColor() + ".png");
    }
}
