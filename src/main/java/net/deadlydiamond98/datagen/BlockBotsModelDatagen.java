package net.deadlydiamond98.datagen;

import net.deadlydiamond98.common.blocks.BlockBotsBlocks;
import net.deadlydiamond98.common.items.BlockBotsItems;
import net.deadlydiamond98.koalalib.util.datagen.ItemModelDatagenUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class BlockBotsModelDatagen extends FabricModelProvider {

    public BlockBotsModelDatagen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockBotsBlocks.TITANIUM_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        ItemModelDatagenUtil.registerGenerated(itemModelGenerator,
                BlockBotsItems.TITANIUM_SCRAPS,
                BlockBotsItems.TITANIUM_NUGGET,
                BlockBotsItems.TITANIUM_INGOT,
                BlockBotsItems.SILICON_CRYSTALS,
                BlockBotsItems.SILICON_WAFER,
                BlockBotsItems.PROCESSOR,
                BlockBotsItems.PCB,
                BlockBotsItems.EMPTY_BATTERY,
                BlockBotsItems.REDSTONE_BATTERY,
                BlockBotsItems.MAGMATIC_BATTERY
        );
        ItemModelDatagenUtil.registerSpawnEggs(itemModelGenerator,
                BlockBotsItems.FAULTY_BLOCK_BOT_EGG
        );
    }
}
