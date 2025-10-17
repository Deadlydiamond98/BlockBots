package net.deadlydiamond98.renderer.entity.bot;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.renderer.features.passive.BlockBotFarmHatFeature;
import net.deadlydiamond98.renderer.features.passive.BlockBotLittleChestFeature;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class BlockBotCollectorRenderer<T extends BaseBlockBotEntity> extends BlockBotRenderer<T> {
    public BlockBotCollectorRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.addFeature(new BlockBotLittleChestFeature<>(this, context.getHeldItemRenderer()));
    }
}
