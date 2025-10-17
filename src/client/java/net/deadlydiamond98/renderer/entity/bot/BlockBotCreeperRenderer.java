package net.deadlydiamond98.renderer.entity.bot;

import net.deadlydiamond98.common.entity.passive.attacking.BlockBotCreeper;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class BlockBotCreeperRenderer<T extends BlockBotCreeper> extends BlockBotRenderer<T> {
    public BlockBotCreeperRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    protected void scale(T creeperEntity, MatrixStack matrixStack, float f) {
        float g = creeperEntity.getClientFuseTime(f);
        float h = 1.0F + MathHelper.sin(g * 100.0F) * g * 0.01F;
        g = MathHelper.clamp(g, 0.0F, 1.0F);
        g *= g;
        g *= g;
        float i = (1.0F + g * 0.4F) * h;
        float j = (1.0F + g * 0.1F) / h;
        matrixStack.scale(i, j, i);
    }

    protected float getAnimationCounter(T creeperEntity, float f) {
        float g = creeperEntity.getClientFuseTime(f);
        return (int)(g * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(g, 0.5F, 1.0F);
    }
}
