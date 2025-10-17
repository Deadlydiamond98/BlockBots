package net.deadlydiamond98.models;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BlockBotMinerHatModel<T extends BaseBlockBotEntity> extends EntityModel<T> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(BlockBots.MOD_ID, "block_bot_miner_hat"), "main");

	private final ModelPart body;

	public BlockBotMinerHatModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, -1.0F));

		ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(18, 59).cuboid(-1.0F, -4.0F, -6.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -12.6F, 2.1F, -0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r2 = body.addChild("cube_r2", ModelPartBuilder.create().uv(28, 53).cuboid(-4.0F, -2.0F, -5.0F, 9.0F, 2.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -11.0F, 1.6F, -0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r3 = body.addChild("cube_r3", ModelPartBuilder.create().uv(32, 41).cuboid(-3.0F, -4.0F, -5.0F, 8.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -13.0F, 2.1F, -0.0436F, 0.0F, 0.0F));
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