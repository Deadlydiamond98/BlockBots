package net.deadlydiamond98.common.misc;

import net.deadlydiamond98.BlockBots;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class BlockBotsTags {

    public static class Blocks {
        public static final TagKey<Block> MOSS_SPAWN = createTag("spawns_mossy_bots");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(BlockBots.MOD_ID, name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> RUSTY = createTag("spawns_rusty_bots");
        public static final TagKey<Biome> MOSSY = createTag("spawns_mossy_bots");


        private static TagKey<Biome> createTag(String name) {
            return TagKey.of(RegistryKeys.BIOME, new Identifier(BlockBots.MOD_ID, name));
        }
    }
}
