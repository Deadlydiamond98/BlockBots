package net.deadlydiamond98.events;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class BlockBotsClientTickEvent {

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(BlockBotsClientTickEvent::tick);
    }

    private static void tick(MinecraftClient client) {

    }
}
