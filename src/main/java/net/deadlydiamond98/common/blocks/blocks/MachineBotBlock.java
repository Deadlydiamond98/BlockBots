package net.deadlydiamond98.common.blocks.blocks;

import net.deadlydiamond98.koalalib.common.blocks.OrientableBlockWithEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public abstract class MachineBotBlock extends OrientableBlockWithEntity {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public MachineBotBlock(Settings settings) {
        super(settings);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            this.openScreen(world, pos, player);
            return ActionResult.CONSUME;
        }
    }

    protected abstract void openScreen(World world, BlockPos pos, PlayerEntity player);

    @Override
    protected Property<Direction> getFacingProperty() {
        return FACING;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}