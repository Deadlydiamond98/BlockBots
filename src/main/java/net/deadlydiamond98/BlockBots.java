package net.deadlydiamond98;

import net.deadlydiamond98.entity.BlockBotsEntities;
import net.deadlydiamond98.items.BlockBotsItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockBots implements ModInitializer {
	public static final String MOD_ID = "block_bots";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		BlockBotsItems.register();
		BlockBotsEntities.register();
	}
}