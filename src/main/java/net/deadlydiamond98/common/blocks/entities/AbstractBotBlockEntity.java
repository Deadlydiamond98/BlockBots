package net.deadlydiamond98.common.blocks.entities;

import net.deadlydiamond98.util.IBotScreenDisplay;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AbstractBotBlockEntity extends BlockEntity implements IBotScreenDisplay<AbstractBotBlockEntity> {

    private int age, blinkTimer = 0;

    public AbstractBotBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState blockState, AbstractBotBlockEntity blockEntity) {
        blockEntity.tick(world, pos, blockState);
    }

    private void tick(World world, BlockPos pos, BlockState blockState) {
        this.age++;
        this.blinkTimer += world.getRandom().nextFloat() <= 0.4 || isBlinking(this) ? 1 : 0;
    }

    @Override
    public int getBlinkTimer(AbstractBotBlockEntity entity) {
        return this.blinkTimer;
    }

    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }
}
