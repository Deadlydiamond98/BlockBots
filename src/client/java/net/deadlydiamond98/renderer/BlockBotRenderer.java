package net.deadlydiamond98.renderer;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.MalfunctioningBlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.deadlydiamond98.renderer.features.BlockBotEyesFeatureRenderer;
import net.deadlydiamond98.renderer.features.BlockBotScreenFeatureRenderer;
import net.deadlydiamond98.renderer.features.BlockBotWiresFeatureRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BlockBotRenderer extends MobEntityRenderer<MalfunctioningBlockBotEntity, BlockBotModel<MalfunctioningBlockBotEntity>> {

    private static float offset = 0.001f;

    public BlockBotRenderer(EntityRendererFactory.Context context) {
        super(context, new BlockBotModel<>(context.getPart(BlockBotModel.LAYER_LOCATION)), 0.25f);
        addFeature(new BlockBotWiresFeatureRenderer(this, context.getModelLoader()));
        addScreenLayer(getScreen("color/red", true), 255);
        this.addFeature(new BlockBotEyesFeatureRenderer(this, offset += 0.0001f));
        addScreenLayer(getScreen("off", false), 50);
    }

    @Override
    public void render(MalfunctioningBlockBotEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(MalfunctioningBlockBotEntity entity) {
        return new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/malfunctioning_block_bot.png");
    }

    private void addScreenLayer(RenderLayer layer, int alpha) {
        this.addFeature(new BlockBotScreenFeatureRenderer(this, layer, alpha, offset += 0.0001f));
    }

    private RenderLayer getScreen(String string, boolean glow) {
        Identifier texture = new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/screen/" + string + ".png");
        return glow ? RenderLayer.getEyes(texture) : RenderLayer.getItemEntityTranslucentCull(texture);
    }
}
