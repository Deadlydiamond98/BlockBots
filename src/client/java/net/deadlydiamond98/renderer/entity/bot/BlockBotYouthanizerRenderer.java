package net.deadlydiamond98.renderer.entity.bot;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.renderer.features.passive.BlockBotBandanaFeature;
import net.deadlydiamond98.renderer.features.passive.BlockBotCatEarsFeature;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class BlockBotYouthanizerRenderer<T extends BaseBlockBotEntity> extends BlockBotRenderer<T> {
    public BlockBotYouthanizerRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.addFeature(new BlockBotCatEarsFeature<>(this, context.getModelLoader()));
    }
}
