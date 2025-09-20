package net.deadlydiamond98;

import net.deadlydiamond98.common.blocks.BlockBotsBlockEntities;
import net.deadlydiamond98.common.blocks.BlockBotsBlocks;
import net.deadlydiamond98.common.entity.BlockBotsEntities;
import net.deadlydiamond98.common.items.BlockBotsItems;
import net.deadlydiamond98.common.misc.BlockBotsFeatures;
import net.deadlydiamond98.common.misc.BlockBotsTab;
import net.fabricmc.api.ModInitializer;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class BlockBots implements ModInitializer {
	public static final String MOD_ID = "block_bots";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		BlockBotsItems.register();
		BlockBotsBlocks.register();
		BlockBotsBlockEntities.register();
		BlockBotsEntities.register();
		BlockBotsTab.register();
		BlockBotsFeatures.register();
	}
}