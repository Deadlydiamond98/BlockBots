package net.deadlydiamond98.common.entity.passive.base;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class BlockBot extends BaseBlockBotEntity {
    public BlockBot(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1));
    }
}
