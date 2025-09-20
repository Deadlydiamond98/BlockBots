package net.deadlydiamond98.renderer.entity;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.deadlydiamond98.renderer.features.screen.BlockBotEyesFeatureRenderer;
import net.deadlydiamond98.renderer.features.faulty.BlockBotMossFeatureRenderer;
import net.deadlydiamond98.renderer.features.screen.BlockBotScreenFeatureRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BlockBotRenderer<T extends BaseBlockBotEntity> extends MobEntityRenderer<T, BlockBotModel<T>> {

    private float offset = 0.001f;

    public BlockBotRenderer(EntityRendererFactory.Context context) {
        super(context, new BlockBotModel<>(context.getPart(BlockBotModel.LAYER_LOCATION)), 0.25f);
        initFeatures(context);
    }

    protected void initFeatures(EntityRendererFactory.Context context) {
        addFeature(new BlockBotMossFeatureRenderer<>(this, context.getModelLoader()));
        addScreenLayer(getScreen("color/red", true), false);
        this.addFeature(new BlockBotEyesFeatureRenderer<>(this, offset += 0.0001f));
        addScreenLayer(getScreen("off", false), true);
    }

    @Override
    public void render(T mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(T entity) {
        return new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/block_bot.png");
    }

    private void addScreenLayer(RenderLayer layer, boolean alpha) {
        this.addFeature(new BlockBotScreenFeatureRenderer<>(this, layer, alpha, offset += 0.0001f));
    }

    private RenderLayer getScreen(String string, boolean glow) {
        Identifier texture = new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/screen/" + string + ".png");
        return glow ? RenderLayer.getEyes(texture) : RenderLayer.getItemEntityTranslucentCull(texture);
    }
}
