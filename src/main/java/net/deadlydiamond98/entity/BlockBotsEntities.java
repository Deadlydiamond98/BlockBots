package net.deadlydiamond98.entity;

import net.deadlydiamond98.BlockBots;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class BlockBotsEntities {

    public static final EntityType<BlockBotEntity> BLOCK_BOT_ENTITY = register("block_bot", create(BlockBotEntity.class, 1, 1));

    public static <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(BlockBots.MOD_ID, name), builder.build());
    }

    public static <T extends Entity> FabricEntityTypeBuilder<T> create(Class<T> entityClass, float width, float height) {
        return FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, factory(entityClass)).dimensions(EntityDimensions.fixed(width, height));
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
        FabricDefaultAttributeRegistry.register(BLOCK_BOT_ENTITY, BlockBotEntity.createCustomAttributes());
    }

}
