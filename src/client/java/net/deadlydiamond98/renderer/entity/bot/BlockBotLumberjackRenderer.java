package net.deadlydiamond98.renderer.entity.bot;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.renderer.features.passive.BlockBotBandanaFeature;
import net.deadlydiamond98.renderer.features.passive.BlockBotBeanieFeature;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class BlockBotLumberjackRenderer<T extends BaseBlockBotEntity> extends BlockBotRenderer<T> {
    public BlockBotLumberjackRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.addFeature(new BlockBotBeanieFeature<>(this, context.getModelLoader()));
    }
}
