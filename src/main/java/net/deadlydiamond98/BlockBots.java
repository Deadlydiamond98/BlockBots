package net.deadlydiamond98;

import net.deadlydiamond98.common.blocks.BlockBotsBlockEntities;
import net.deadlydiamond98.common.blocks.BlockBotsBlocks;
import net.deadlydiamond98.common.effects.BlockBotsEffects;
import net.deadlydiamond98.common.entity.BlockBotsEntities;
import net.deadlydiamond98.common.items.BlockBotsItems;
import net.deadlydiamond98.common.misc.BlockBotsFeatures;
import net.deadlydiamond98.common.misc.BlockBotsSounds;
import net.deadlydiamond98.common.misc.BlockBotsTab;
import net.deadlydiamond98.common.screenhandler.BlockBotsScreenHandlers;
import net.deadlydiamond98.koalalib.config.KoalaConfigCreator;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockBots implements ModInitializer {
	public static final String MOD_ID = "block_bots";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
//		KoalaConfigCreator.addModConfig(MOD_ID, BlockBotConfig.class);

		BlockBotsItems.register();
		BlockBotsBlocks.register();
		BlockBotsBlockEntities.register();
		BlockBotsEntities.register();
		BlockBotsEffects.register();
		BlockBotsTab.register();
		BlockBotsScreenHandlers.register();
		BlockBotsFeatures.register();
		BlockBotsSounds.register();
	}
}