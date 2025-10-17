package net.deadlydiamond98.common.misc;

import net.deadlydiamond98.BlockBots;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class BlockBotsSounds {
    public static SoundEvent BLOCK_BOT_HURT = register("entity.block_bot.hurt");
    public static SoundEvent BLOCK_BOT_DEATH = register("entity.block_bot.death");

    public static SoundEvent register(String name) {
        Identifier id = new Identifier(BlockBots.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void register() {}
}
