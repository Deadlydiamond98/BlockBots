package net.deadlydiamond98;

import net.deadlydiamond98.common.entity.BlockBotsEntities;
import net.deadlydiamond98.models.BlockBotModel;
import net.deadlydiamond98.models.BlockBotMossModel;
import net.deadlydiamond98.models.BlockBotWiresModel;
import net.deadlydiamond98.renderer.BlockBotRenderer;
import net.deadlydiamond98.renderer.WeatheredBlockBot;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BlockBotsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {

		// Renderers
		EntityRendererRegistry.register(BlockBotsEntities.FAULTY_BLOCK_BOT_ENTITY, WeatheredBlockBot::new);

		// Model Layers
		EntityModelLayerRegistry.registerModelLayer(BlockBotModel.LAYER_LOCATION, BlockBotModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BlockBotWiresModel.LAYER_LOCATION, BlockBotWiresModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BlockBotMossModel.LAYER_LOCATION, BlockBotMossModel::getTexturedModelData);
	}
}