package net.deadlydiamond98.common.items;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.BlockBotsEntities;
import net.deadlydiamond98.common.items.battery.DisposableBatteryItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockBotsItems {

    // TITANIUM

    public static final Item TITANIUM_SCRAPS = register("titanium_scrap", new Item(new FabricItemSettings()));
    public static final Item TITANIUM_NUGGET = register("titanium_nugget", new Item(new FabricItemSettings()));
    public static final Item TITANIUM_INGOT = register("titanium_ingot", new Item(new FabricItemSettings()));

    // SILICON

    public static final Item QUARTZ_POWDER = register("quartz_powder", new Item(new FabricItemSettings()));
    public static final Item SILICON_CRYSTALS = register("silicon_crystals", new Item(new FabricItemSettings()));
    public static final Item SILICON_WAFER = register("silicon_wafer", new Item(new FabricItemSettings()));

    // BATTERIES

    public static final Item BATTERY = register("battery", new DisposableBatteryItem(new FabricItemSettings().maxCount(1)));
    public static final Item REDSTONE_BATTERY = register("redstone_battery", new Item(new FabricItemSettings()));
    public static final Item MAGMATIC_BATTERY = register("magmatic_battery", new Item(new FabricItemSettings()));

    // ELECTRONIC PARTS

    public static final Item PROCESSOR = register("processor", new Item(new FabricItemSettings()));
    public static final Item PCB = register("pcb", new Item(new FabricItemSettings()));
    public static final Item WIRES = register("wires", new Item(new FabricItemSettings()));
    public static final Item SCREEN = register("screen", new Item(new FabricItemSettings()));

    public static final Item FAULTY_BLOCK_BOT_EGG = registerSpawnEgg(BlockBotsEntities.FAULTY_BLOCK_BOT_ENTITY, 0xCCEAF6, 0x954D30);

    public static Item registerSpawnEgg(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor) {
        return register(
                Registries.ENTITY_TYPE.getId(type).getPath() + "_spawn_egg",
                new SpawnEggItem(type, primaryColor, secondaryColor, new FabricItemSettings())
        );
    }

    public static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BlockBots.MOD_ID, name), item);
    }

    public static void register() {}
}
