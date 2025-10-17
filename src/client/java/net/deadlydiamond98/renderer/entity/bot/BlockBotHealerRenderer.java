package net.deadlydiamond98.renderer.entity.bot;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.renderer.features.passive.BlockBotBandanaFeature;
import net.deadlydiamond98.renderer.features.passive.BlockBotFarmHatFeature;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class BlockBotHealerRenderer<T extends BaseBlockBotEntity> extends BlockBotRenderer<T> {
    public BlockBotHealerRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.addFeature(new BlockBotBandanaFeature<>(this, context.getModelLoader(), "head_mirror.png"));
    }

    @Override
    public Identifier getTexture(T entity) {
        return new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/block_bot_healer.png");
    }
}
