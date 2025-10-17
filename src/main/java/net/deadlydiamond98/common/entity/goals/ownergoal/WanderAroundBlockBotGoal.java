package net.deadlydiamond98.common.entity.goals.ownergoal;

import net.deadlydiamond98.common.entity.passive.base.OwnedBlockBotEntity;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class WanderAroundBlockBotGoal extends WanderAroundFarGoal {
    public WanderAroundBlockBotGoal(PathAwareEntity pathAwareEntity, double d) {
        super(pathAwareEntity, d);
    }

    @Override
    public boolean canStart() {
        return super.canStart() && ((OwnedBlockBotEntity) this.mob).followState != OwnedBlockBotEntity.BehaviorState.STAYING;
    }
}
