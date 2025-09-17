package net.deadlydiamond98.models;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.FaultyBlockBotEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BlockBotWiresModel<T extends FaultyBlockBotEntity> extends EntityModel<T> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(BlockBots.MOD_ID, "block_bot_wires"), "main");

	private final ModelPart wires;
	private final ModelPart wire1;
	private final ModelPart wire2;
	private final ModelPart wire3;
	private final ModelPart wire4;
	private final ModelPart wire5;
	private final ModelPart wire6;
	private final ModelPart wire7;
	private final ModelPart wire8;
	private final ModelPart wire9;
	private final ModelPart wire10;

	public BlockBotWiresModel(ModelPart root) {
		this.wires = root.getChild("wires");
		this.wire1 = this.wires.getChild("wire1");
		this.wire2 = this.wires.getChild("wire2");
		this.wire3 = this.wires.getChild("wire3");
		this.wire4 = this.wires.getChild("wire4");
		this.wire5 = this.wires.getChild("wire5");
		this.wire6 = this.wires.getChild("wire6");
		this.wire7 = this.wires.getChild("wire7");
		this.wire8 = this.wires.getChild("wire8");
		this.wire9 = this.wires.getChild("wire9");
		this.wire10 = this.wires.getChild("wire10");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData wires = modelPartData.addChild("wires", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData wire1 = wires.addChild("wire1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_blue_r1 = wire1.addChild("right_blue_r1", ModelPartBuilder.create().uv(2, 0).cuboid(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.95F, -7.75F, 0.0F, -0.0579F, -0.2555F, 0.4874F));

		ModelPartData wire2 = wires.addChild("wire2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_blue_r2 = wire2.addChild("right_blue_r2", ModelPartBuilder.create().uv(2, 0).cuboid(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.65F, -6.5F, 5.0F, -0.2784F, -0.8224F, 0.2944F));

		ModelPartData wire3 = wires.addChild("wire3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_blue_big_r1 = wire3.addChild("right_blue_big_r1", ModelPartBuilder.create().uv(1, 4).cuboid(-1.0F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.75F, -3.0F, 3.8F, 0.0448F, 0.2367F, 0.7245F));

		ModelPartData wire4 = wires.addChild("wire4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_red_r1 = wire4.addChild("right_red_r1", ModelPartBuilder.create().uv(1, 2).cuboid(0.0F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.95F, -7.55F, 0.0F, 0.0F, 0.3491F, 0.2618F));

		ModelPartData wire5 = wires.addChild("wire5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_blue_r1 = wire5.addChild("left_blue_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, 0.0F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.65F, -8.7F, -1.1F, -2.8255F, -0.0275F, 2.2431F));

		ModelPartData wire6 = wires.addChild("wire6", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_red_r1 = wire6.addChild("left_red_r1", ModelPartBuilder.create().uv(2, 6).mirrored().cuboid(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.05F, -8.7F, -1.1F, 0.1401F, -0.3778F, -0.1404F));

		ModelPartData wire7 = wires.addChild("wire7", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_red_r2 = wire7.addChild("left_red_r2", ModelPartBuilder.create().uv(2, 6).mirrored().cuboid(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-4.85F, -4.7F, 2.4F, -0.1072F, 0.5804F, -0.3599F));

		ModelPartData wire8 = wires.addChild("wire8", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_red_big_r1 = wire8.addChild("left_red_big_r1", ModelPartBuilder.create().uv(1, 6).mirrored().cuboid(-3.0F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.35F, -8.85F, -2.95F, 3.1416F, 0.0F, -0.6109F));

		ModelPartData wire9 = wires.addChild("wire9", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData back_red_r1 = wire9.addChild("back_red_r1", ModelPartBuilder.create().uv(2, 6).mirrored().cuboid(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.15F, -6.575F, 4.9F, -1.9625F, 1.2296F, -2.1957F));

		ModelPartData wire10 = wires.addChild("wire10", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData back_red_big_r1 = wire10.addChild("back_red_big_r1", ModelPartBuilder.create().uv(1, 2).mirrored().cuboid(-3.0F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-1.55F, -5.05F, 4.7F, -1.7044F, 1.1219F, -1.236F));
		return TexturedModelData.of(modelData, 8, 8);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean[] bls = entity.getWires();
		this.wire1.visible = bls[0];
		this.wire2.visible = bls[1];
		this.wire3.visible = bls[2];
		this.wire4.visible = bls[3];
		this.wire5.visible = bls[4];
		this.wire6.visible = bls[5];
		this.wire7.visible = bls[6];
		this.wire8.visible = bls[7];
		this.wire9.visible = bls[8];
		this.wire10.visible = bls[9];
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		wires.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}