package net.deadlydiamond98.renderer;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.entity.BlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class BlockBotRenderer extends MobEntityRenderer<BlockBotEntity, BlockBotModel<BlockBotEntity>> {

    public BlockBotRenderer(EntityRendererFactory.Context context) {
        super(context, new BlockBotModel<>(context.getPart(BlockBotModel.LAYER_LOCATION)), 0.25F);
    }

    @Override
    public Identifier getTexture(BlockBotEntity entity) {
        return new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot.png");
    }
}
