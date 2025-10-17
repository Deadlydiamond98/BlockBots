package net.deadlydiamond98.models;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BlockBotBandanaLikeModel<T extends BaseBlockBotEntity> extends EntityModel<T> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(BlockBots.MOD_ID, "block_bot_bandana_like"), "main");

	private final ModelPart body;

	public BlockBotBandanaLikeModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(20, 52).cuboid(-5.5F, -11.0F, -5.5F, 11.0F, 1.0F, 11.0F, new Dilation(0.01F))
				.uv(4, 61).cuboid(-1.5F, -12.0F, -5.6F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(0, 60).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.3F, -11.0F, 6.6F, 0.2239F, -0.0257F, -0.1787F));

		ModelPartData cube_r2 = body.addChild("cube_r2", ModelPartBuilder.create().uv(2, 59).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.4F, -11.0F, 6.6F, 0.0695F, -0.081F, 0.2548F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}