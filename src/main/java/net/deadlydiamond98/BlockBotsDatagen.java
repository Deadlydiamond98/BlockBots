package net.deadlydiamond98;

import net.deadlydiamond98.datagen.BlockBotsModelDatagen;
import net.deadlydiamond98.datagen.BlockBotsTagsDatagen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BlockBotsDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(BlockBotsModelDatagen::new);
        pack.addProvider(BlockBotsTagsDatagen.Blocks::new);
        pack.addProvider(BlockBotsTagsDatagen.Items::new);
    }
}
