package net.deadlydiamond98.common.blocks.blocks.crafting;

import net.deadlydiamond98.common.blocks.BlockBotsBlockEntities;
import net.deadlydiamond98.common.blocks.blocks.MachineBotBlock;
import net.deadlydiamond98.common.blocks.entities.crafting.TitaniumForgeBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TitaniumForgeBlock extends MachineBotBlock {

    public TitaniumForgeBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        NamedScreenHandlerFactory screenHandlerFactory = ((TitaniumForgeBlockEntity) world.getBlockEntity(pos));
        if (screenHandlerFactory != null) {
            player.openHandledScreen(screenHandlerFactory);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TitaniumForgeBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockBotsBlockEntities.TITANIUM_FORGE, TitaniumForgeBlockEntity::tick);
    }
}
