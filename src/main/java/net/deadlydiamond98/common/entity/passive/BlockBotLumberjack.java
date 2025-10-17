package net.deadlydiamond98.common.entity.passive;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.entity.goals.harvest.HarvestTreeGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class BlockBotLumberjack extends BlockBotCollector {
    public BlockBotLumberjack(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new HarvestTreeGoal<>(this, 1, 20));
    }

    @Override
    public ItemStack getMainHandStack() {
        return new ItemStack(Items.IRON_AXE);
    }
}
