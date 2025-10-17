package net.deadlydiamond98.datagen;

import net.deadlydiamond98.common.blocks.BlockBotsBlocks;
import net.deadlydiamond98.common.items.BlockBotsItems;
import net.deadlydiamond98.koalalib.util.datagen.ItemModelDatagenUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.Item;

public class BlockBotsModelDatagen extends FabricModelProvider {

    public BlockBotsModelDatagen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockBotsBlocks.TITANIUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockBotsBlocks.QUARTZ_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(BlockBotsBlocks.DEEPSLATE_QUARTZ_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        ItemModelDatagenUtil.registerGenerated(itemModelGenerator, BlockBotsItems.EGGS.toArray(Item[]::new));

        ItemModelDatagenUtil.registerGenerated(itemModelGenerator,
                BlockBotsItems.TITANIUM_SCRAPS,
                BlockBotsItems.TITANIUM_NUGGET,
                BlockBotsItems.TITANIUM_INGOT,
                BlockBotsItems.CRUSHED_QUARTZ,
                BlockBotsItems.SILICON_CRYSTALS,
                BlockBotsItems.SILICON_WAFER,
                BlockBotsItems.PROCESSOR,
                BlockBotsItems.PCB,
                BlockBotsItems.WIRES,
                BlockBotsItems.SCREEN,
                BlockBotsItems.BATTERY,
                BlockBotsItems.REDSTONE_BATTERY,
                BlockBotsItems.MAGMATIC_BATTERY

//                BlockBotsItems.FAULTY_BLOCK_BOT_EGG,
//                BlockBotsItems.BLOCK_BOT_EGG,
//                BlockBotsItems.BLOCK_BOT_COLLECTOR_EGG,
//                BlockBotsItems.BLOCK_BOT_FARMER_EGG,
//                BlockBotsItems.BLOCK_BOT_HEALER_EGG,
//                BlockBotsItems.BLOCK_BOT_FIGHTER_EGG,
//                BlockBotsItems.BLOCK_BOT_LUMBERJACK_EGG
        );
    }
}
