package net.deadlydiamond98.common.items;

import net.deadlydiamond98.BlockBots;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockBotsItems {

    // TITANIUM

    public static final Item TITANIUM_SCRAPS = register("titanium_scrap", new Item(new FabricItemSettings()));
    public static final Item TITANIUM_NUGGET = register("titanium_nugget", new Item(new FabricItemSettings()));
    public static final Item TITANIUM_INGOT = register("titanium_ingot", new Item(new FabricItemSettings()));

    // SILICON

    public static final Item SILICON_CRYSTALS = register("silicon_crystals", new Item(new FabricItemSettings()));
    public static final Item SILICON_WAFER = register("silicon_wafer", new Item(new FabricItemSettings()));

    // BATTERIES

    public static final Item EMPTY_BATTERY = register("empty_battery", new Item(new FabricItemSettings()));
    public static final Item REDSTONE_BATTERY = register("redstone_battery", new Item(new FabricItemSettings()));
    public static final Item MAGMATIC_BATTERY = register("magmatic_battery", new Item(new FabricItemSettings()));

    // ELECTRONIC PARTS

    public static final Item PROCESSOR = register("processor", new Item(new FabricItemSettings()));
    public static final Item PCB = register("pcb", new Item(new FabricItemSettings()));

    public static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BlockBots.MOD_ID, name), item);
    }

    public static void register() {}
}
