package net.deadlydiamond98.datagen;

import net.deadlydiamond98.common.blocks.BlockBotsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.impl.datagen.FabricTagBuilder;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class BlockBotsTagsDatagen {
    public static class Blocks extends FabricTagProvider.BlockTagProvider {
        public Blocks(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(
                    BlockBotsBlocks.TITANIUM_BLOCK,
                    BlockBotsBlocks.QUARTZ_ORE,
                    BlockBotsBlocks.DEEPSLATE_QUARTZ_ORE
            );
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                    BlockBotsBlocks.TITANIUM_BLOCK,
                    BlockBotsBlocks.QUARTZ_ORE,
                    BlockBotsBlocks.DEEPSLATE_QUARTZ_ORE
            );
        }
    }

    public static class Items extends FabricTagProvider.ItemTagProvider {
        public Items(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
        }
    }
}
