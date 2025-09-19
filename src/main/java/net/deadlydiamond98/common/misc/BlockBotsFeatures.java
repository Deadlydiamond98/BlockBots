package net.deadlydiamond98.common.misc;

import net.deadlydiamond98.BlockBots;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class BlockBotsFeatures {

    public static final Feature<?> QUARTZ_ORE_FEATURE = register("quartz_ore", new OreFeature(OreFeatureConfig.CODEC));

    public static Feature<?> register(String name, Feature<?> feature) {
        return Registry.register(Registries.FEATURE, new Identifier(BlockBots.MOD_ID, name), feature);
    }

    public static void register() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(
                        RegistryKeys.PLACED_FEATURE,
                        new Identifier(BlockBots.MOD_ID, "quartz_ore_placed")
                )
        );
    }
}
