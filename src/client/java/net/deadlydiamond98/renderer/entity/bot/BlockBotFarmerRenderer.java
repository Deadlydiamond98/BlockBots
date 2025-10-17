package net.deadlydiamond98.renderer.entity.bot;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.renderer.features.passive.BlockBotFarmHatFeature;
import net.deadlydiamond98.renderer.features.passive.BlockBotHeldItemFeature;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class BlockBotFarmerRenderer<T extends BaseBlockBotEntity> extends BlockBotRenderer<T> {
    public BlockBotFarmerRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.addFeature(new BlockBotFarmHatFeature<>(this, context.getModelLoader()));
    }
}
