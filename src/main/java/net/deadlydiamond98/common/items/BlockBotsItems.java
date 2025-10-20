package net.deadlydiamond98.common.items;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.BlockBotsEntities;
import net.deadlydiamond98.common.items.battery.DisposableBatteryItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class BlockBotsItems {

    public static final List<Item> EGGS = new ArrayList<>();

    // FOODS

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // TITANIUM

    public static final Item TITANIUM_SCRAPS = register("titanium_scrap", new Item(new FabricItemSettings()));
    public static final Item TITANIUM_NUGGET = register("titanium_nugget", new Item(new FabricItemSettings()));
    public static final Item TITANIUM_INGOT = register("titanium_ingot", new Item(new FabricItemSettings()));

    // SILICON

//    public static final Item QUARTZ_POWDER = register("quartz_powder", new Item(new FabricItemSettings()));
    public static final Item CRUSHED_QUARTZ = register("crushed_quartz", new Item(new FabricItemSettings()));
    public static final Item SILICON_CRYSTALS = register("silicon_crystals", new Item(new FabricItemSettings()));
    public static final Item SILICON_WAFER = register("silicon_wafer", new Item(new FabricItemSettings()));

    // ELECTRONIC PARTS

    public static final Item PROCESSOR = register("processor", new Item(new FabricItemSettings()));
    public static final Item PCB = register("pcb", new Item(new FabricItemSettings()));
    public static final Item MOTHERBOARD = register("motherboard", new Item(new FabricItemSettings()));
    public static final Item WIRES = register("wires", new Item(new FabricItemSettings()));
    public static final Item SCREEN = register("screen", new Item(new FabricItemSettings()));

    // BATTERIES

    public static final Item BATTERY = register("battery", new DisposableBatteryItem(new FabricItemSettings().maxCount(1)));

    // EGGS?

    public static final Item FAULTY_BLOCK_BOT_EGG = registerSpawnEgg(BlockBotsEntities.FAULTY_BLOCK_BOT);
    public static final Item BLOCK_BOT_EGG = registerSpawnEgg(BlockBotsEntities.BLOCK_BOT);
    public static final Item BLOCK_BOT_COLLECTOR_EGG = registerSpawnEgg(BlockBotsEntities.BLOCK_BOT_COLLECTOR);
    public static final Item BLOCK_BOT_FARMER_EGG = registerSpawnEgg(BlockBotsEntities.BLOCK_BOT_FARMER);
    public static final Item BLOCK_BOT_HEALER_EGG = registerSpawnEgg(BlockBotsEntities.BLOCK_BOT_HEALER);
    public static final Item BLOCK_BOT_FIGHTER_EGG = registerSpawnEgg(BlockBotsEntities.BLOCK_BOT_FIGHTER);
    public static final Item BLOCK_BOT_LUMBERJACK_EGG = registerSpawnEgg(BlockBotsEntities.BLOCK_BOT_LUMBERJACK);
    public static final Item BLOCK_BOT_CREEPER_EGG = registerSpawnEgg(BlockBotsEntities.BLOCK_BOT_CREEPER);
    public static final Item BLOCK_BOT_ARCHER_EGG = registerSpawnEgg(BlockBotsEntities.BLOCK_BOT_ARCHER);
    public static final Item BLOCK_BOT_MINER_EGG = registerSpawnEgg(BlockBotsEntities.BLOCK_BOT_MINER);
    public static final Item BLOCK_BOT_YOUTHANIZER_EGG = registerSpawnEgg(BlockBotsEntities.BLOCK_BOT_YOUTHANIZER);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Item registerSpawnEgg(EntityType<? extends MobEntity> type) {
        Item egg = register(Registries.ENTITY_TYPE.getId(type).getPath() + "_spawn_egg", new BotSpawnEgg(type, new FabricItemSettings()));
        EGGS.add(egg);
        return egg;
    }

    public static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BlockBots.MOD_ID, name), item);
    }

    public static void register() {}
}
