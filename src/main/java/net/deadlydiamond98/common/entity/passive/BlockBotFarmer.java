package net.deadlydiamond98.common.entity.passive;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.entity.goals.harvest.HarvestCropsGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.world.World;

public class BlockBotFarmer extends BlockBotCollector {
    public BlockBotFarmer(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new HarvestCropsGoal<>(this, 1, 20));
    }

    @Override
    public boolean canDepositToChest() {
        for (ItemStack stack : this.getInventory().stacks) {
            if (stack.isIn(ItemTags.VILLAGER_PLANTABLE_SEEDS)) {
                continue;
            }
            return !stack.isEmpty();
        }
        return false;
    }

    @Override
    public boolean shouldHoldOntoStack(ItemStack mobStack) {
        return mobStack.isIn(ItemTags.VILLAGER_PLANTABLE_SEEDS);
    }

    @Override
    public ItemStack getMainHandStack() {
        return new ItemStack(Items.IRON_HOE);
    }
}
