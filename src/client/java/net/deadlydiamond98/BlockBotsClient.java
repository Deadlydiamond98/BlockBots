package net.deadlydiamond98;

import net.deadlydiamond98.common.blocks.BlockBotsBlockEntities;
import net.deadlydiamond98.common.entity.BlockBotsEntities;
import net.deadlydiamond98.events.BlockBotsItemTooltipEvents;
import net.deadlydiamond98.models.BlockBotModel;
import net.deadlydiamond98.models.BlockBotMossModel;
import net.deadlydiamond98.models.BlockBotWiresModel;
import net.deadlydiamond98.renderer.block.AliveBlockEntityRenderer;
import net.deadlydiamond98.renderer.entity.FaultyBlockBotRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class BlockBotsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {

		// Entity Renderers
		EntityRendererRegistry.register(BlockBotsEntities.FAULTY_BLOCK_BOT_ENTITY, FaultyBlockBotRenderer::new);

		// Block Entity Renderers

		BlockEntityRendererFactories.register(BlockBotsBlockEntities.GENERATOR_BOT, AliveBlockEntityRenderer::new);

		// Model Layers
		EntityModelLayerRegistry.registerModelLayer(BlockBotModel.LAYER_LOCATION, BlockBotModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BlockBotWiresModel.LAYER_LOCATION, BlockBotWiresModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BlockBotMossModel.LAYER_LOCATION, BlockBotMossModel::getTexturedModelData);

		// Events
		BlockBotsItemTooltipEvents.register();
	}
}