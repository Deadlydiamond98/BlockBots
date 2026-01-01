package net.deadlydiamond98.common.entity;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.entity.hostile.FaultyBlockBotEntity;
import net.deadlydiamond98.common.entity.passive.*;
import net.deadlydiamond98.common.entity.passive.base.BlockBot;
import net.deadlydiamond98.common.entity.passive.attacking.BlockBotArcher;
import net.deadlydiamond98.common.entity.passive.attacking.BlockBotCreeper;
import net.deadlydiamond98.common.entity.passive.attacking.BlockBotFighter;
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

    // HOSTILES

    public static final EntityType<FaultyBlockBotEntity> FAULTY_BLOCK_BOT = register("faulty_block_bot", createMon(FaultyBlockBotEntity.class, 0.75f, 0.75f));

    // BLOCK BOTS

    public static final EntityType<BlockBot> BLOCK_BOT = register("block_bot", createCre(BlockBot.class, 0.75f, 0.75f));
    public static final EntityType<BlockBotCollector> BLOCK_BOT_COLLECTOR = register("block_bot_collector", createCre(BlockBotCollector.class, 0.75f, 0.75f));
    public static final EntityType<BlockBotFarmer> BLOCK_BOT_FARMER = register("block_bot_farmer", createCre(BlockBotFarmer.class, 0.75f, 0.75f));
    public static final EntityType<BlockBotHealer> BLOCK_BOT_HEALER = register("block_bot_healer", createCre(BlockBotHealer.class, 0.75f, 0.75f));
    public static final EntityType<BlockBotFighter> BLOCK_BOT_FIGHTER = register("block_bot_fighter", createCre(BlockBotFighter.class, 0.75f, 0.75f));
    public static final EntityType<BlockBotLumberjack> BLOCK_BOT_LUMBERJACK = register("block_bot_lumberjack", createCre(BlockBotLumberjack.class, 0.75f, 0.75f));
    public static final EntityType<BlockBotCreeper> BLOCK_BOT_CREEPER = register("block_bot_creeper", createCre(BlockBotCreeper.class, 0.75f, 0.75f));
    public static final EntityType<BlockBotArcher> BLOCK_BOT_ARCHER = register("block_bot_archer", createCre(BlockBotArcher.class, 0.75f, 0.75f));
    public static final EntityType<BlockBotMiner> BLOCK_BOT_MINER = register("block_bot_miner", createCre(BlockBotMiner.class, 0.75f, 0.75f));
    public static final EntityType<BlockBotYouthanizer> BLOCK_BOT_YOUTHANIZER = register("block_bot_youthanizer", createCre(BlockBotYouthanizer.class, 0.75f, 0.75f));


    // REGISTRATION

    public static <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(BlockBots.MOD_ID, name), builder.build());
    }

    public static <T extends Entity> FabricEntityTypeBuilder<T> createMon(Class<T> entityClass, float width, float height) {
        return create(SpawnGroup.MONSTER, entityClass, width, height);
    }

    public static <T extends Entity> FabricEntityTypeBuilder<T> createCre(Class<T> entityClass, float width, float height) {
        return create(SpawnGroup.CREATURE, entityClass, width, height);
    }

    public static <T extends Entity> FabricEntityTypeBuilder<T> createMisc(Class<T> entityClass, float width, float height) {
        return create(SpawnGroup.MISC, entityClass, width, height);
    }

    public static <T extends Entity> FabricEntityTypeBuilder<T> create(SpawnGroup group, Class<T> entityClass, float width, float height) {
        return FabricEntityTypeBuilder.create(group, factory(entityClass)).dimensions(EntityDimensions.fixed(width, height));
    }

    public static <T extends Entity> EntityType.EntityFactory<T> factory(Class<T> entityClass) {
        return (EntityType<T> type, World world) -> {
            try {
                return entityClass.getConstructor(EntityType.class, World.class).newInstance(type, world);
            } catch (Exception ignored) {
                return null;
            }
        };
    }
    public static void register() {
        attributes();
        spawns();
    }

    // ATTRIBUTES AND SPAWNS

    private static void attributes() {
        FabricDefaultAttributeRegistry.register(FAULTY_BLOCK_BOT, BaseBlockBotEntity.createCustomAttributes());
        FabricDefaultAttributeRegistry.register(BLOCK_BOT, BaseBlockBotEntity.createCustomAttributes());
        FabricDefaultAttributeRegistry.register(BLOCK_BOT_COLLECTOR, BaseBlockBotEntity.createCustomAttributes());
        FabricDefaultAttributeRegistry.register(BLOCK_BOT_FARMER, BaseBlockBotEntity.createCustomAttributes());
        FabricDefaultAttributeRegistry.register(BLOCK_BOT_HEALER, BaseBlockBotEntity.createCustomAttributes());
        FabricDefaultAttributeRegistry.register(BLOCK_BOT_LUMBERJACK, BaseBlockBotEntity.createCustomAttributes());
        FabricDefaultAttributeRegistry.register(BLOCK_BOT_FIGHTER, BlockBotFighter.createCustomAttributes());
        FabricDefaultAttributeRegistry.register(BLOCK_BOT_CREEPER, BlockBotCreeper.createCustomAttributes());
        FabricDefaultAttributeRegistry.register(BLOCK_BOT_ARCHER, BaseBlockBotEntity.createCustomAttributes());
        FabricDefaultAttributeRegistry.register(BLOCK_BOT_MINER, BlockBotMiner.createCustomAttributes());
        FabricDefaultAttributeRegistry.register(BLOCK_BOT_YOUTHANIZER, BaseBlockBotEntity.createCustomAttributes());
    }

    private static void spawns() {
        BiomeModifications.addSpawn(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                SpawnGroup.MONSTER,
                FAULTY_BLOCK_BOT,
                50, 1, 2
        );
        SpawnRestriction.register(
                FAULTY_BLOCK_BOT, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FaultyBlockBotEntity::canSpawnInDark
        );
    }
}
