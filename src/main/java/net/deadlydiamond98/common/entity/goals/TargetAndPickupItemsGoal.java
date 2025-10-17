package net.deadlydiamond98.common.entity.goals;

import net.deadlydiamond98.common.entity.passive.base.BlockBotWithInventory;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;
import java.util.List;

public class TargetAndPickupItemsGoal<T extends BlockBotWithInventory> extends Goal {
    protected final T mob;
    public final double speed;
    protected int cooldown;
    protected int tryingTime;
    private int safeWaitingTime;
    protected ItemEntity targetItem;
    private boolean reached;
    private final int range;

    public TargetAndPickupItemsGoal(T mob, double speed, int range) {
        this.mob = mob;
        this.speed = speed;
        this.range = range;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.JUMP));
    }

    @Override
    public boolean canStart() {
        if (this.cooldown > 0) {
            --this.cooldown;
            return false;
        }
        this.cooldown = 40;
        return this.findTargetItem();
    }

    @Override
    public boolean shouldContinue() {
        return this.tryingTime >= -this.safeWaitingTime && this.tryingTime <= 1200 && this.targetItem != null && !this.targetItem.isRemoved();
    }

    @Override
    public void start() {
        this.startMovingToTarget();
        this.tryingTime = 0;
        this.safeWaitingTime = this.mob.getRandom().nextInt(this.mob.getRandom().nextInt(1200) + 1200) + 1200;
    }

    protected void startMovingToTarget() {
        if (this.targetItem != null) {
            this.mob.getNavigation().startMovingTo(this.targetItem, this.speed);
        }
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (this.targetItem != null) {
            if (!this.targetItem.isRemoved()) {
                this.reached = false;
                ++this.tryingTime;
                if (this.shouldResetPath()) {
                    this.mob.getNavigation().startMovingTo(this.targetItem, this.speed);
                }
            } else {
                this.reached = true;
                --this.tryingTime;
            }
        }
    }

    public boolean shouldResetPath() {
        return this.tryingTime % 40 == 0;
    }

    protected boolean findTargetItem() {
        List<ItemEntity> items = mob.getWorld().getEntitiesByClass(ItemEntity.class, mob.getBoundingBox().expand(range), item -> true);
        for (ItemEntity item : items) {
            if (this.mob.getInventory().canInsert(item.getStack())) {
                this.targetItem = item;
                return this.mob.getInventory().canInsert(this.targetItem.getStack()) && this.mob.attemptGather(this.targetItem.getStack());
            }
        }
        return false;
    }
}
