package net.deadlydiamond98.renderer.features;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.MalfunctioningBlockBotEntity;
import net.deadlydiamond98.models.BlockBotModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class BlockBotEyesFeatureRenderer extends BlockBotScreenFeatureRenderer {

    public static final Identifier REG = new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/screen/face/default_eyes.png");
    public static final Identifier BLINK = new Identifier(BlockBots.MOD_ID, "textures/entity/block_bot/screen/face/default_eyes_blink.png");

    public BlockBotEyesFeatureRenderer(FeatureRendererContext<MalfunctioningBlockBotEntity, BlockBotModel<MalfunctioningBlockBotEntity>> context, float frontOffset) {
        super(context, null, 255, frontOffset, true);
    }

    @Override
    protected RenderLayer getRenderLayer(MalfunctioningBlockBotEntity entity) {
        int blinkTick = entity.age % 60;

        if (blinkTick <= 3) {
            return RenderLayer.getEyes(BLINK);
        }

        return RenderLayer.getEyes(REG);
    }
}
