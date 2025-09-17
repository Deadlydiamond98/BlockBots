package net.deadlydiamond98.renderer;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.FaultyBlockBotEntity;
import net.deadlydiamond98.renderer.features.BlockBotWiresFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class WeatheredBlockBot extends BlockBotRenderer<FaultyBlockBotEntity> {

    public WeatheredBlockBot(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    protected void initFeatures(EntityRendererFactory.Context context) {
        addFeature(new BlockBotWiresFeatureRenderer(this, context.getModelLoader()));
        super.initFeatures(context);
    }

    @Override
    public Identifier getTexture(FaultyBlockBotEntity entity) {
        String rusty = entity.isRusty() ? "rusty_" : "";
        return new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/" + rusty + "broken_block_bot.png");
    }
}
