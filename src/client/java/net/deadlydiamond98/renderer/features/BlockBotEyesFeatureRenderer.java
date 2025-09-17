package net.deadlydiamond98.renderer.features;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.IBlockBot;
import net.deadlydiamond98.common.entity.FaultyBlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Identifier;

public class BlockBotEyesFeatureRenderer<T extends PathAwareEntity & IBlockBot<T>> extends BlockBotScreenFeatureRenderer<T> {

    public static final Identifier REG = new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/screen/face/default_eyes.png");
    public static final Identifier BLINK = new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/screen/face/default_eyes_blink.png");
    public static final Identifier ANGRY = new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/screen/face/angry_eyes.png");
    public static final Identifier OUCHIE = new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/screen/face/ouchie_eyes.png");

    public BlockBotEyesFeatureRenderer(FeatureRendererContext<T, BlockBotModel<T>> context, float frontOffset) {
        super(context, null, 255, frontOffset, true);
    }

    @Override
    protected RenderLayer getRenderLayer(T entity) {
        int blinkTick = entity.age % 60;

        if (entity.getHurtTime() > 0) {
            return RenderLayer.getEyes(OUCHIE);
        } else if (blinkTick <= 3) {
            return RenderLayer.getEyes(BLINK);
        } else if (entity instanceof FaultyBlockBotEntity mean && mean.isTargeting()) {
            return RenderLayer.getEyes(ANGRY);
        }

        return RenderLayer.getEyes(REG);
    }
}
