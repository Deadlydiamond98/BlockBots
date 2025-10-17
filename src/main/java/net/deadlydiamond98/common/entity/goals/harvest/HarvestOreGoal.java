package net.deadlydiamond98.common.entity.goals.harvest;

import net.deadlydiamond98.common.entity.passive.BlockBotMiner;
import net.deadlydiamond98.common.misc.BlockBotsTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class HarvestOreGoal extends MoveToTargetPosGoal {

    private float lastPos = 0;

    public HarvestOreGoal(PathAwareEntity mob, double speed, int range) {
        super(mob, speed, range);
    }

    @Override
    public void stop() {
        super.stop();
        this.lastPos = 0;
    }

    @Override
    protected int getInterval(PathAwareEntity mob) {
        return 2;
    }

    public void tick() {
        super.tick();
        double distance = this.mob.squaredDistanceTo(this.targetPos.getX() + 0.5f, this.targetPos.getY() + 0.5f, this.targetPos.getZ() + 0.5f);
        this.mob.getNavigation().startMovingTo(this.targetPos.getX() + 0.5f, this.targetPos.getY(), this.targetPos.getZ() + 0.5f, 1);
        this.mob.getLookControl().lookAt(this.targetPos.getX() + 0.5f, this.targetPos.getY(), this.targetPos.getZ() + 0.5f);

        if (distance < Math.pow(this.mob.getWidth(), 2) + 3) {
            this.mob.getNavigation().stop();

            this.mob.getWorld().breakBlock(targetPos, true);
        } else {
            HitResult result = this.mob.raycast(0.75f, 0, false);

            if (result.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult) result;
                BlockState state = this.mob.getWorld().getBlockState(blockHitResult.getBlockPos());
                if (!state.isIn(BlockBotsTags.Blocks.MINER_BLACKLIST)) {
                    this.mob.getWorld().breakBlock(blockHitResult.getBlockPos(), true);
                }
            }

        }
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        Block block = ((BlockBotMiner) this.mob).getTargetBlock();

        return block != null && world.getBlockState(pos).isOf(block) && !(this.mob.getBlockPos().getY() > pos.getY());
    }
}
