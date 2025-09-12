package net.deadlydiamond98;

import net.deadlydiamond98.entity.BlockBotsEntities;
import net.deadlydiamond98.models.BlockBotModel;
import net.deadlydiamond98.renderer.BlockBotRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BlockBotsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(BlockBotsEntities.BLOCK_BOT_ENTITY, BlockBotRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(BlockBotModel.LAYER_LOCATION, BlockBotModel::getTexturedModelData);
	}
}