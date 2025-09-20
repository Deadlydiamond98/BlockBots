package net.deadlydiamond98.common.entity;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.hostile.FaultyBlockBotEntity;
import net.deadlydiamond98.koalalib.util.RegistryHelper;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

public class BlockBotsEntities {

    public static final EntityType<FaultyBlockBotEntity> FAULTY_BLOCK_BOT_ENTITY = register("faulty_block_bot", createM(FaultyBlockBotEntity.class, 0.75f, 0.75f));


    // REGISTRATION

    public static <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> builder) {
        return RegistryHelper.Entities.register(new Identifier(BlockBots.MOD_ID, name), builder);
    }

    public static <T extends Entity> FabricEntityTypeBuilder<T> createM(Class<T> entityClass, float width, float height) {
        return RegistryHelper.Entities.create(entityClass, SpawnGroup.MONSTER, width, height);
    }

    // ATTRIBUTES AND SPAWNS

    private static void attributes() {
        FabricDefaultAttributeRegistry.register(FAULTY_BLOCK_BOT_ENTITY, FaultyBlockBotEntity.createCustomAttributes());
    }

    private static void spawns() {
        BiomeModifications.addSpawn(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                SpawnGroup.MONSTER,
                FAULTY_BLOCK_BOT_ENTITY,
                100, 1, 2
        );
        SpawnRestriction.register(
                FAULTY_BLOCK_BOT_ENTITY, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FaultyBlockBotEntity::canSpawnInDark
        );
    }

    public static void register() {
        attributes();
        spawns();
    }
}
