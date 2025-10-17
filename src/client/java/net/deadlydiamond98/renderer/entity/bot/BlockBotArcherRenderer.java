package net.deadlydiamond98.renderer.entity.bot;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.renderer.features.passive.BlockBotBandanaFeature;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class BlockBotArcherRenderer<T extends BaseBlockBotEntity> extends BlockBotRenderer<T> {
    public BlockBotArcherRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.addFeature(new BlockBotBandanaFeature<>(this, context.getModelLoader(),"bandana_green.png"));
    }
}
