package net.deadlydiamond98.common.blocks.entities;

import net.deadlydiamond98.common.blocks.BlockBotsBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChestMarkerBlockEntity extends BlockEntity {
    public ChestMarkerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockBotsBlockEntities.CHEST_MARKER, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState blockState, ChestMarkerBlockEntity chestMarkerBlockEntity) {}
}
