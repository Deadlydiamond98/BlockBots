package net.deadlydiamond98.common.blocks.blocks.power;

import net.deadlydiamond98.common.blocks.BlockBotsBlockEntities;
import net.deadlydiamond98.common.blocks.entities.power.GeneratorBotBlockEntity;
import net.deadlydiamond98.koalalib.common.blocks.OrientableBlockWithEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GeneratorBotBlock extends OrientableBlockWithEntity {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public GeneratorBotBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected Property<Direction> getFacingProperty() {
        return FACING;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GeneratorBotBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockBotsBlockEntities.GENERATOR_BOT, GeneratorBotBlockEntity::tick);
    }
}
