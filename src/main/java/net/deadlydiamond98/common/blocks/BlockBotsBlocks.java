package net.deadlydiamond98.common.blocks;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.items.BlockBotsItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockBotsBlocks {

    public static final Block TITANIUM_BLOCK = register("titanium_block", new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    private static Block register(String id, Block block) {
        Block registeredBlock = Registry.register(Registries.BLOCK, new Identifier(BlockBots.MOD_ID, id), block);
        registerItem(id, registeredBlock);
        return registeredBlock;
    }

    private static void registerItem(String id, Block block) {
        BlockBotsItems.register(id, new BlockItem(block, new FabricItemSettings()));
    }
    public static void register() {}
}
