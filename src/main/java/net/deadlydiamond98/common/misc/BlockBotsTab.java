package net.deadlydiamond98.common.misc;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.blocks.BlockBotsBlocks;
import net.deadlydiamond98.common.items.BlockBotsItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BlockBotsTab {
    public static final Identifier TAB_ID = new Identifier(BlockBots.MOD_ID, "block_bots_tab");

    public static final ItemGroup BLOCK_BOTS_TAB = Registry.register(Registries.ITEM_GROUP, TAB_ID, FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.block_bots")).icon(BlockBotsItems.PROCESSOR::getDefaultStack).entries((displayContext, entry) -> {

                // Items

                entry.add(BlockBotsBlocks.QUARTZ_ORE);
                entry.add(BlockBotsBlocks.DEEPSLATE_QUARTZ_ORE);
                entry.add(BlockBotsItems.CRUSHED_QUARTZ);

                entry.add(BlockBotsItems.TITANIUM_SCRAPS);
                entry.add(BlockBotsItems.TITANIUM_NUGGET);
                entry.add(BlockBotsItems.TITANIUM_INGOT);
                entry.add(BlockBotsBlocks.TITANIUM_BLOCK);

                entry.add(BlockBotsItems.SILICON_CRYSTALS);
                entry.add(BlockBotsItems.SILICON_WAFER);

                entry.add(BlockBotsItems.PROCESSOR);
                entry.add(BlockBotsItems.PCB);
                entry.add(BlockBotsItems.MOTHERBOARD);
                entry.add(BlockBotsItems.WIRES);
                entry.add(BlockBotsItems.SCREEN);

//                entry.add(BlockBotsItems.BATTERY);

                entry.add(BlockBotsBlocks.CHEST_MARKER);

                BlockBotsItems.EGGS.forEach(entry::add);


            }).build()
    );

    public static void register() {}
}
