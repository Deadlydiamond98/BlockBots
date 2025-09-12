package net.deadlydiamond98.items;

import net.deadlydiamond98.BlockBots;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockBotsItems {

    public static final Item TITANIUM_SCRAPS = register("titanium_scrap", new Item(new FabricItemSettings()));

    public static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BlockBots.MOD_ID, name), item);
    }

    public static void register() {}
}
