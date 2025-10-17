package net.deadlydiamond98.common.blocks;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.blocks.blocks.ChestMarker;
import net.deadlydiamond98.common.blocks.blocks.crafting.TitaniumForgeBlock;
import net.deadlydiamond98.common.blocks.blocks.power.GeneratorBotBlock;
import net.deadlydiamond98.common.items.BlockBotsItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class BlockBotsBlocks {

    private static final AbstractBlock.Settings TITANIUM = FabricBlockSettings.copyOf(Blocks.IRON_BLOCK);

    // Resources

    public static final Block TITANIUM_BLOCK = register("titanium_block", new Block(TITANIUM));

    public static final Block QUARTZ_ORE = register("quartz_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.IRON_ORE), UniformIntProvider.create(1, 3)));
    public static final Block DEEPSLATE_QUARTZ_ORE = register("deepslate_quartz_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE), UniformIntProvider.create(1, 3)));

    // Functional

    public static final Block CHEST_MARKER = register("chest_marker", new ChestMarker(FabricBlockSettings.copyOf(Blocks.TRIPWIRE_HOOK)));

    public static final Block GENERATOR_BOT = register("generator_bot", new GeneratorBotBlock(TITANIUM));
    public static final Block TITANIUM_FORGE = register("titanium_forge", new TitaniumForgeBlock(TITANIUM));


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
