package net.deadlydiamond98.renderer.entity.bot;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.deadlydiamond98.renderer.features.faulty.BlockBotMossFeatureRenderer;
import net.deadlydiamond98.renderer.features.BlockBotScreenFeature;
import net.deadlydiamond98.renderer.features.passive.BlockBotHeldItemFeature;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BlockBotRenderer<T extends BaseBlockBotEntity> extends MobEntityRenderer<T, BlockBotModel<T>> {

    public BlockBotRenderer(EntityRendererFactory.Context context) {
        super(context, new BlockBotModel<>(context.getPart(BlockBotModel.LAYER_LOCATION)), 0.25f);
        addFeature(new BlockBotMossFeatureRenderer<>(this, context.getModelLoader()));
        this.addFeature(new BlockBotHeldItemFeature<>(this, context.getHeldItemRenderer()));
        this.addFeature(getScreen());
    }

    protected FeatureRenderer<T, BlockBotModel<T>> getScreen() {
        return new BlockBotScreenFeature<>(this);
    }

    @Override
    public void render(T mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(T entity) {
        return new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/block_bot.png");
    }
}
