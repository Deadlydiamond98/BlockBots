package net.deadlydiamond98.renderer.entity.bot;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.renderer.features.passive.BlockBotBandanaFeature;
import net.deadlydiamond98.renderer.features.passive.BlockBotFarmHatFeature;
import net.deadlydiamond98.renderer.features.passive.BlockBotHeldItemFeature;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class BlockBotFighterRenderer<T extends BaseBlockBotEntity> extends BlockBotRenderer<T> {
    public BlockBotFighterRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.addFeature(new BlockBotBandanaFeature<>(this, context.getModelLoader(),"bandana_red.png"));
    }
}
