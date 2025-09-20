package net.deadlydiamond98.common.blocks;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.blocks.entities.GeneratorBotBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockBotsBlockEntities {

    public static final BlockEntityType<GeneratorBotBlockEntity> GENERATOR_BOT = register("generator_bot",
            FabricBlockEntityTypeBuilder.create(GeneratorBotBlockEntity::new, BlockBotsBlocks.GENERATOR_BOT).build(null));

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> blockEntity) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(BlockBots.MOD_ID, name), blockEntity);
    }

    public static void register() {}

}
