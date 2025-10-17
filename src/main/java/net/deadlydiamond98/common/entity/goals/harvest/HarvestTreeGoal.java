package net.deadlydiamond98.common.entity.goals.harvest;

import net.deadlydiamond98.common.entity.passive.base.BlockBotWithInventory;
import net.deadlydiamond98.common.misc.BlockBotsTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class HarvestTreeGoal<T extends BlockBotWithInventory> extends MoveToTargetPosGoal {

    private static final int MAX_LOGS = 150;
    private static final int MAX_BREAK_TIME = 100;
    private int timer = 0;
    private int logsBroken = 0;

    public HarvestTreeGoal(PathAwareEntity mob, double speed, int range) {
        super(mob, speed, range);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        this.timer = 0;
        this.logsBroken = 0;
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

        if (distance < Math.pow(this.mob.getWidth(), 2) + 3) {
            this.mob.getNavigation().stop();

            BlockState blockState = this.mob.getWorld().getBlockState(this.targetPos);

            if (this.timer++ > MAX_BREAK_TIME) {
                mineLogs(this.mob.getWorld(), this.getTargetPos());
                this.stop();
            } else if (this.mob.age % 20 == 0) {
                this.mob.getWorld().playSound(null, this.mob.getBlockPos(), blockState.getSoundGroup().getHitSound(), SoundCategory.BLOCKS);
            }
        }
    }

    private void mineLogs(World world, BlockPos pos) {
        if (this.logsBroken > MAX_LOGS) {
            return;
        } else if (world.getBlockState(pos).isIn(BlockTags.LOGS)) {
            world.breakBlock(pos, true);
            this.logsBroken++;
        }
        for (BlockPos nextPos : BlockPos.iterateOutwards(pos, 1, 1, 1)) {
            if (world.getBlockState(nextPos).isIn(BlockTags.LOGS)) {
                mineLogs(world, nextPos);
            }
        }
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState log = world.getBlockState(pos);
        BlockState belowLog = world.getBlockState(pos.down());
        return log.isIn(BlockTags.LOGS) && !belowLog.isIn(BlockBotsTags.Blocks.TREE_PART) && !belowLog.isAir();
    }
}
