package net.deadlydiamond98.common.entity.goals.attack;

import net.deadlydiamond98.common.entity.passive.base.OwnedBlockBotEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class BotMeleeAttackGoal extends MeleeAttackGoal {
    public BotMeleeAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
    }

    @Override
    public boolean canStart() {
        return super.canStart() && ((OwnedBlockBotEntity) this.mob).followState != OwnedBlockBotEntity.BehaviorState.STAYING;
    }
}
