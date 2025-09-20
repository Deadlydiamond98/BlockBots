package net.deadlydiamond98.common.blocks.entities;

import net.deadlydiamond98.common.blocks.BlockBotsBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class GeneratorBotBlockEntity extends AbstractBotBlockEntity {

    public GeneratorBotBlockEntity(BlockPos pos, BlockState state) {
        super(BlockBotsBlockEntities.GENERATOR_BOT, pos, state);
    }
}
