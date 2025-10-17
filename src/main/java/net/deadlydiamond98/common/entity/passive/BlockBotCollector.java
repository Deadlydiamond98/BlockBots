package net.deadlydiamond98.common.entity.passive;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.entity.goals.TargetAndPickupItemsGoal;
import net.deadlydiamond98.common.entity.goals.DepositToMarkedChest;
import net.deadlydiamond98.common.entity.passive.base.OwnedBlockBotEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class BlockBotCollector extends OwnedBlockBotEntity {
    public BlockBotCollector(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(3, new TargetAndPickupItemsGoal<>(this, 1, 20));
        this.goalSelector.add(2, new DepositToMarkedChest<>(this, 1, 25));
    }
}
