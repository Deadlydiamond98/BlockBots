package net.deadlydiamond98.common.screenhandler;

import net.deadlydiamond98.BlockBots;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class BlockBotsScreenHandlers {

    public final static ScreenHandlerType<TitaniumForgeScreenHandler> TITANIUM_FORGE = register("titanium_forge", TitaniumForgeScreenHandler::new);

    public static <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ExtendedScreenHandlerType.ExtendedFactory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, new Identifier(BlockBots.MOD_ID, name), new ExtendedScreenHandlerType<>(factory));
    }

    public static void register() {}
}
