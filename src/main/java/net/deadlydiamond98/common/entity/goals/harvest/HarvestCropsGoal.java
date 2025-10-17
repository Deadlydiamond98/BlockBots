package net.deadlydiamond98.common.entity.goals.harvest;

import net.deadlydiamond98.common.entity.passive.base.BlockBotWithInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.GourdBlock;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class HarvestCropsGoal<T extends BlockBotWithInventory> extends MoveToTargetPosGoal {

    public HarvestCropsGoal(T mob, double speed, int range) {
        super(mob, speed, range);
    }

    @Override
    public boolean canStart() {
        return super.canStart();
    }

    @Override
    public void stop() {
        super.stop();
        getMob().getOffHandStack().setCount(0);
    }

    @Override
    protected int getInterval(PathAwareEntity mob) {
        return 2;
    }

    @Override
    public void tick() {
        super.tick();
        double distance = this.mob.squaredDistanceTo(this.targetPos.getX() + 0.5f, this.targetPos.getY() + 0.5f, this.targetPos.getZ() + 0.5f);
        this.mob.getNavigation().startMovingTo(this.targetPos.getX() + 0.5f, this.targetPos.getY() - 1, this.targetPos.getZ() + 0.5f, 1);

        BlockState blockState = this.mob.getWorld().getBlockState(this.targetPos);
        setStackToSeed(blockState);

        if (distance < Math.pow(this.mob.getWidth(), 2) + 3) {
            this.mob.getNavigation().stop();

            if (blockState.getBlock() instanceof CropBlock crop) {
                Item seed = crop.getSeedsItem().asItem();

                this.mob.getWorld().breakBlock(this.targetPos, true);

                getInventory().stacks.forEach(stack -> {
                    if (stack.isOf(seed)) {
                        stack.decrement(1);
                        this.mob.getWorld().setBlockState(this.targetPos, crop.withAge(0));
                    }
                });
            } else if (blockState.getBlock() instanceof GourdBlock) {
                this.mob.getWorld().breakBlock(this.targetPos, true);
            }
        }
    }

    private void setStackToSeed(BlockState blockState) {
        if (blockState.getBlock() instanceof CropBlock crop) {
            Item seed = crop.getSeedsItem().asItem();
            getInventory().stacks.forEach(stack -> {
                if (stack.isOf(seed)) {
                    getMob().setOffhandStack(seed.getDefaultStack());
                }
            });
        }
    }

    private SimpleInventory getInventory() {
        return getMob().getInventory();
    }

    private BlockBotWithInventory getMob() {
        return ((BlockBotWithInventory) this.mob);
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof CropBlock crop) {
            return crop.getAge(state) == crop.getMaxAge();
        }
        return state.getBlock() instanceof GourdBlock;
    }
}

