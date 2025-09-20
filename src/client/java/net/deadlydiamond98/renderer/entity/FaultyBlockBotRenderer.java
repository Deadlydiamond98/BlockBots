package net.deadlydiamond98.renderer.entity;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.hostile.FaultyBlockBotEntity;
import net.deadlydiamond98.renderer.features.faulty.BlockBotWiresFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class FaultyBlockBotRenderer extends BlockBotRenderer<FaultyBlockBotEntity> {

    public FaultyBlockBotRenderer(EntityRendererFactory.Context context) {
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
