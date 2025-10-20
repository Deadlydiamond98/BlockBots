package net.deadlydiamond98.common.entity.passive;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.entity.goals.TargetAndPickupItemsGoal;
import net.deadlydiamond98.common.entity.goals.DepositToMarkedChest;
import net.deadlydiamond98.common.entity.hostile.FaultyBlockBotEntity;
import net.deadlydiamond98.common.entity.passive.base.OwnedBlockBotEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.world.World;

public class BlockBotCollector extends OwnedBlockBotEntity {
    public BlockBotCollector(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(3, new FleeEntityGoal<>(this, ZombieEntity.class, 6, 1, 1.2));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, FaultyBlockBotEntity.class, 6, 1, 1.2));

        this.goalSelector.add(3, new TargetAndPickupItemsGoal<>(this, 1, 20));
        this.goalSelector.add(2, new DepositToMarkedChest<>(this, 1, 25));
    }
}
