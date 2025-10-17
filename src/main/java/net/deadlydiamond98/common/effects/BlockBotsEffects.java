package net.deadlydiamond98.common.effects;

import net.deadlydiamond98.BlockBots;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockBotsEffects {


    public static StatusEffect registerEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(BlockBots.MOD_ID, name), effect);
    }

    public static Potion registerPotion(String name, Potion potion) {
        return Registry.register(Registries.POTION, new Identifier(BlockBots.MOD_ID, name), potion);
    }

    public static void register() {}
}
