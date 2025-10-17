package net.deadlydiamond98.renderer.entity.bot;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.renderer.features.passive.BlockBotBeanieFeature;
import net.deadlydiamond98.renderer.features.passive.BlockBotMinerHatFeature;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class BlockBotMinerRenderer<T extends BaseBlockBotEntity> extends BlockBotRenderer<T> {
    public BlockBotMinerRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.addFeature(new BlockBotMinerHatFeature<>(this, context.getModelLoader()));
    }
}
