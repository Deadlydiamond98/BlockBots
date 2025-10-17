package net.deadlydiamond98;

import net.deadlydiamond98.common.blocks.BlockBotsBlockEntities;
import net.deadlydiamond98.common.entity.BlockBotsEntities;
import net.deadlydiamond98.common.screenhandler.BlockBotsScreenHandlers;
import net.deadlydiamond98.events.BlockBotsClientTickEvent;
import net.deadlydiamond98.events.BlockBotsItemTooltipEvents;
import net.deadlydiamond98.models.*;
import net.deadlydiamond98.renderer.block.AliveBlockEntityRenderer;
import net.deadlydiamond98.renderer.block.ChestMarkerBlockEntityRenderer;
import net.deadlydiamond98.renderer.entity.bot.*;
import net.deadlydiamond98.screens.TitaniumForgeScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class BlockBotsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {

		initRendering();

		// Screens
		HandledScreens.register(BlockBotsScreenHandlers.TITANIUM_FORGE, TitaniumForgeScreen::new);

		// Events
		BlockBotsItemTooltipEvents.register();
		BlockBotsClientTickEvent.register();
	}

	private void initRendering() {
		// Entity Renderers
		EntityRendererRegistry.register(BlockBotsEntities.FAULTY_BLOCK_BOT, FaultyBlockBotRenderer::new);
		EntityRendererRegistry.register(BlockBotsEntities.BLOCK_BOT, BlockBotRenderer::new);
		EntityRendererRegistry.register(BlockBotsEntities.BLOCK_BOT_COLLECTOR, BlockBotCollectorRenderer::new);
		EntityRendererRegistry.register(BlockBotsEntities.BLOCK_BOT_FARMER, BlockBotFarmerRenderer::new);
		EntityRendererRegistry.register(BlockBotsEntities.BLOCK_BOT_HEALER, BlockBotHealerRenderer::new);
		EntityRendererRegistry.register(BlockBotsEntities.BLOCK_BOT_FIGHTER, BlockBotFighterRenderer::new);
		EntityRendererRegistry.register(BlockBotsEntities.BLOCK_BOT_ARCHER, BlockBotArcherRenderer::new);
		EntityRendererRegistry.register(BlockBotsEntities.BLOCK_BOT_CREEPER, BlockBotCreeperRenderer::new);
		EntityRendererRegistry.register(BlockBotsEntities.BLOCK_BOT_LUMBERJACK, BlockBotLumberjackRenderer::new);
		EntityRendererRegistry.register(BlockBotsEntities.BLOCK_BOT_MINER, BlockBotMinerRenderer::new);

		// Block Entity Renderers
		BlockEntityRendererFactories.register(BlockBotsBlockEntities.GENERATOR_BOT, AliveBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(BlockBotsBlockEntities.CHEST_MARKER, ChestMarkerBlockEntityRenderer::new);

		// Model Layers
		EntityModelLayerRegistry.registerModelLayer(BlockBotModel.LAYER_LOCATION, BlockBotModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BlockBotWiresModel.LAYER_LOCATION, BlockBotWiresModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BlockBotMossModel.LAYER_LOCATION, BlockBotMossModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BlockBotFarmHatModel.LAYER_LOCATION, BlockBotFarmHatModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BlockBotBandanaLikeModel.LAYER_LOCATION, BlockBotBandanaLikeModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BlockBotBeanieModel.LAYER_LOCATION, BlockBotBeanieModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BlockBotMinerHatModel.LAYER_LOCATION, BlockBotMinerHatModel::getTexturedModelData);
	}
}